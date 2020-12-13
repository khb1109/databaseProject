package com.hong.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JdbcTemplate {

    private final String url;
    private final String id;
    private final String password;

    public void update(String sql) {
        executeUpdate(sql, new Object[] {});
    }

    private void executeUpdate(String sql, Object[] parameters) {
        try (PreparedStatement preparedStatement = createsPreparedStatement(sql, new Object[] {})) {
            preparedStatement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    private <T> T execute(String sql, Object[] parameters, RowMapper<T> rowMapper) {
        try (
            PreparedStatement preparedStatement = createsPreparedStatement(sql, parameters);
            ResultSet resultSet = preparedStatement.executeQuery(sql)
        ) {
            return rowMapper.mapping(resultSet, resultSet.getRow());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public <T> T update(String sql, Object[] parameters, RowMapper<T> rowMapper) {
        return execute(sql, parameters, rowMapper);
    }

    private PreparedStatement createsPreparedStatement(String sql, Object[] parameters) {
        try (
            Connection connection = DriverManager.getConnection(url, id, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ) {
            for (int i = 0; i < parameters.length; i++) {
                preparedStatement.setObject(i + 1, parameters[i]);
            }
            return preparedStatement;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}

