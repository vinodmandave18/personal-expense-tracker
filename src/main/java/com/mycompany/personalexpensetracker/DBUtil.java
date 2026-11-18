
package com.mycompany.personalexpensetracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String JDBC_URL =
        "jdbc:mysql://localhost:3306/personal_expense_tracker?serverTimezone=UTC";

    private static final String JDBC_USER =
        System.getenv("DB_USERNAME");

    private static final String JDBC_PASS =
        System.getenv("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {

        if (JDBC_USER == null || JDBC_PASS == null) {
            throw new SQLException("Database credentials are not configured!");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found!", e);
        }

        return DriverManager.getConnection(
            JDBC_URL, JDBC_USER, JDBC_PASS
        );
    }
}
