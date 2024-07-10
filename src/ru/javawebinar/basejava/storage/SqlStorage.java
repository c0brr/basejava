package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.ContactType;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;
    private static final int UUID_LENGTH = 36;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT(uuid) FROM resume", ps -> {
            ResultSet rs = ps.executeQuery();
            rs.next();
            return rs.getInt(1);
        });
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) " +
                                                                  "VALUES (?,?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            try (PreparedStatement ps =
                         conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) " +
                                                   "VALUES (?,?,?)")) {
                insertContacts(resume, ps);
            }
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume " +
                                " WHERE uuid = ?", ps -> {
            ps.setString(1, uuid);
            if (!ps.execute()) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("   SELECT * FROM resume r " +
                                       "LEFT JOIN contact c " +
                                       "       ON r.uuid = c.resume_uuid " +
                                       "    WHERE r.uuid = ?", ps -> {
            ps.setString(1, uuid);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            Resume resume = new Resume(uuid, rs.getString("full_name"));
            do {
                String value = rs.getString("value");
                String type = rs.getString("type");
                if (value != null && type != null) {
                    resume.getContacts().put(ContactType.valueOf(type), value);
                }
            } while (rs.next());
            return resume;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("UPDATE resume " +
                                                                  "   SET full_name = ? " +
                                                                  " WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact " +
                                                                  " WHERE resume_uuid = ?")) {
                ps.setString(1, resume.getUuid());
                ps.execute();
            }
            try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) " +
                                                                  "VALUES (?, ?, ?)")) {
                insertContacts(resume, ps);
            }
            return null;
        });
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", ps -> {
            ps.execute();
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("   SELECT * FROM resume r " +
                                       "LEFT JOIN contact c " +
                                       "       ON r.uuid = c.resume_uuid", ps -> {
            Map<String, Map<ContactType, String>> map = new TreeMap<>();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fullNameUuid = rs.getString("full_name") + rs.getString("uuid");
                String type = rs.getString("type");
                String value = rs.getString("value");
                if (!map.containsKey(fullNameUuid)) {
                    map.put(fullNameUuid, new EnumMap<>(ContactType.class));
                }
                if (type != null && value != null) {
                    map.get(fullNameUuid).put(ContactType.valueOf(type), value);
                }
            }

            List<Resume> list = new ArrayList<>();
            for (Map.Entry<String, Map<ContactType, String>> entry : map.entrySet()) {
                String fullNameUuid = entry.getKey();
                int length = fullNameUuid.length();
                String fullName = fullNameUuid.substring(0, length - UUID_LENGTH);
                String uuid = fullNameUuid.substring(length - UUID_LENGTH);
                Resume resume = new Resume(uuid, fullName);
                resume.getContacts().putAll(entry.getValue());
                list.add(resume);
            }
            return list;
        });
    }

    private void insertContacts(Resume resume, PreparedStatement ps) throws SQLException {
        for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
            ps.setString(1, resume.getUuid());
            ps.setString(2, entry.getKey().name());
            ps.setString(3, entry.getValue());
            ps.addBatch();
        }
        ps.executeBatch();
    }
}