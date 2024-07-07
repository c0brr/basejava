package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {

    public static <T> T execute(ConnectionFactory connectionFactory, String query, ABlockOfCode<T> aBlockOfCode) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            return aBlockOfCode.execute(ps);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }

}
