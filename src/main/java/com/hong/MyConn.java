package com.hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyConn {

    Connection connection;
    Statement st;
    ResultSet rs;

    MyConn() {
        String url = "jdbc:mysql://localhost:13306/hong?useSSL=false&serverTimezone=UTC";
        String ID = "root";
        String PW = "root";
        try {
            connection = DriverManager.getConnection(url, ID, PW);
            st = connection.createStatement();
        } catch (SQLException SQLex) {
            System.out.println("SQLException: " + SQLex.getMessage());
            System.out.println("SQLState: " + SQLex.getSQLState());
        }
    }
}

