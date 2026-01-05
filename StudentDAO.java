package dao;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    public static void addStudent(String name, String email, String course) {
        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO students(name,email,course) VALUES(?,?,?)");
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, course);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getAllStudents() {
        List<String[]> list = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            ResultSet rs = con.createStatement()
                    .executeQuery("SELECT * FROM students");
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("course")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
