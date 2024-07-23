package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.*;
import ru.javawebinar.basejava.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

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
                    try (PreparedStatement ps = conn.prepareStatement("""
                                                                          INSERT INTO resume (uuid, full_name)
                                                                          VALUES (?,?)
                                                                          """)) {
                        ps.setString(1, resume.getUuid());
                        ps.setString(2, resume.getFullName());
                        ps.execute();
                    }
                    insertContacts(resume, conn);
                    insertSections(resume, conn);
                    return null;
                }
        );
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("""
                                DELETE FROM resume
                                 WHERE uuid = ?
                                """, ps -> {
            ps.setString(1, uuid);
            if (!ps.execute()) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.transactionalExecute(conn -> {
            Resume resume;
            try (PreparedStatement ps = conn.prepareStatement("""
                                                                     SELECT * FROM resume r
                                                                  LEFT JOIN contact c
                                                                         ON r.uuid = c.resume_uuid
                                                                      WHERE r.uuid = ?
                                                                  """)) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    putCheckedContact(rs, resume.getContacts());
                } while (rs.next());
            }
            try (PreparedStatement ps = conn.prepareStatement("""
                                                                     SELECT * FROM resume r
                                                                  LEFT JOIN section s
                                                                         ON r.uuid = s.resume_uuid
                                                                      WHERE r.uuid = ?
                                                                  """)) {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    putCheckedSection(rs, resume.getSections());
                }
            }
            return resume;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(conn -> {
            try (PreparedStatement ps = conn.prepareStatement("""
                    UPDATE resume
                       SET full_name = ?
                     WHERE uuid = ?
                    """)) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, resume.getUuid());
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(resume.getUuid());
                }
            }
            deleteAttributes(resume, conn, """
                                               DELETE FROM contact
                                                WHERE resume_uuid = ?
                                               """);
            insertContacts(resume, conn);
            deleteAttributes(resume, conn, """
                                               DELETE FROM section
                                                WHERE resume_uuid = ?
                                               """);
            insertSections(resume, conn);
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
        return sqlHelper.transactionalExecute(conn -> {
            Map<String, Resume> map = new LinkedHashMap<>();
            try (PreparedStatement ps = conn.prepareStatement("""
                                                                  SELECT * FROM resume
                                                                   ORDER BY full_name, uuid
                                                                  """)) {
                ResultSet rsResumes = ps.executeQuery();
                while (rsResumes.next()) {
                    String uuid = rsResumes.getString("uuid");
                    map.put(uuid, new Resume(uuid, rsResumes.getString("full_name")));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("""
                    SELECT * FROM contact
                    """)) {
                ResultSet rsContacts = ps.executeQuery();
                while (rsContacts.next()) {
                    map.get(rsContacts.getString("resume_uuid")).getContacts()
                            .put(ContactType.valueOf(rsContacts.getString("type")),
                                    rsContacts.getString("value"));
                }
            }
            try (PreparedStatement ps = conn.prepareStatement("""
                    SELECT * FROM section
                    """)) {
                ResultSet rsSections = ps.executeQuery();
                while (rsSections.next()) {
                    String sectionType = rsSections.getString("type");
                    map.get(rsSections.getString("resume_uuid")).getSections()
                            .put(SectionType.valueOf(sectionType), switch (sectionType) {
                                case "PERSONAL", "OBJECTIVE" -> new TextSection(rsSections.getString("content"));
                                case "ACHIEVEMENT", "QUALIFICATIONS" -> new ListSection(Arrays.stream(rsSections
                                        .getString("content")
                                        .split("\n"))
                                        .toList());
                                default -> throw new IllegalStateException("Unexpected sectionType: " + sectionType);
                            });
                }
            }
            return map.values()
                    .stream()
                    .toList();
        });
    }

    private void insertContacts(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("""
                                                              INSERT INTO contact (resume_uuid, type, value)
                                                              VALUES (?,?,?)
                                                              """)) {
            for (Map.Entry<ContactType, String> entry : resume.getContacts().entrySet()) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, entry.getKey().name());
                ps.setString(3, entry.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void insertSections(Resume resume, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("""
                                                              INSERT INTO section (resume_uuid, type, content)
                                                              VALUES (?, ?, ?)
                                                              """)) {
            for (Map.Entry<SectionType, AbstractSection> entry : resume.getSections().entrySet()) {
                ps.setString(1, resume.getUuid());
                String sectionType = entry.getKey().name();
                ps.setString(2, sectionType);
                StringBuilder sb = new StringBuilder();
                ps.setString(3, switch (sectionType) {
                    case "PERSONAL", "OBJECTIVE" -> ((TextSection) resume.getSections()
                            .get(SectionType.valueOf(sectionType))).getContent();
                    case "ACHIEVEMENT", "QUALIFICATIONS" -> {
                        ((ListSection) resume.getSections().get(SectionType.valueOf(sectionType)))
                                .getContentList().forEach(string -> sb.append(string).append('\n'));
                        yield sb.toString();
                    }
                    default -> throw new IllegalStateException("Unexpected sectionType: " + sectionType);
                });
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }

    private void deleteAttributes(Resume resume, Connection conn, String sql) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, resume.getUuid());
            ps.execute();
        }
    }

    private void putCheckedContact(ResultSet rs, Map<ContactType, String> contacts) throws SQLException {
        String type = rs.getString("type");
        String value = rs.getString("value");
        if (type != null && value != null) {
            contacts.put(ContactType.valueOf(type), value);
        }
    }

    private void putCheckedSection(ResultSet rs, Map<SectionType, AbstractSection> sections) throws SQLException {
        String sectionType = rs.getString("type");
        String content = rs.getString("content");
        if (sectionType != null && content != null) {
            sections.put(SectionType.valueOf(sectionType), switch (sectionType) {
                case "PERSONAL", "OBJECTIVE" -> new TextSection(content);
                case "ACHIEVEMENT", "QUALIFICATIONS" -> new ListSection(Arrays.stream(content.split("\n")).toList());
                default -> throw new IllegalStateException("Unexpected sectionType: " + sectionType);
            });
        }
    }
}