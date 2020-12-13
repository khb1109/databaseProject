package com.hong.db;

import java.sql.ResultSet;

@FunctionalInterface
public interface RowMapper<T> {
    T mapping(ResultSet resultSet, int var);
}
