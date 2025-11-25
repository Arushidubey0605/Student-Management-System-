package student.management.dao;

import student.management.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDAO {
    public boolean validateUser(String username, String password) throws Exception {
        String sql = "SELECT username FROM login WHERE username = ? AND pass = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, username);
            pst.setString(2, password);
            try (ResultSet rs = pst.executeQuery()) {
                return rs.next();
            }
        }
    }
}
