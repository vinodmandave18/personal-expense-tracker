package com.mycompany.personalexpensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private Connection conn;

    public AdminDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean validateAdmin(String id, String name, String password) throws SQLException {
        String sql = "SELECT * FROM admin_table WHERE admin_id = ? AND admin_name = ? AND password = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, password);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // valid if any row found
            }
        }
    }
}
