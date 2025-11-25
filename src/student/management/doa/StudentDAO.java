package student.management.doa;

import student.management.model.Student;
import student.management.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements student.management.dao.DAOInterface<Student> {

    @Override
    public void insert(Student s) throws Exception {
        String sql = "INSERT INTO studentdatamgmt(FirstName,Surname,Age,DateOfBirth,Gender,contactNo,YearOfJoin,Department,class,FatherName,MotherName) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pst.setString(1, s.getFirstName());
            pst.setString(2, s.getSurname());
            pst.setInt(3, s.getAge());
            pst.setString(4, s.getDateOfBirth());
            pst.setString(5, String.valueOf(s.getGender()));
            pst.setLong(6, s.getContactNo());
            pst.setInt(7, s.getYearOfJoin());
            pst.setString(8, s.getDepartment());
            pst.setString(9, s.getClassName());
            pst.setString(10, s.getFatherName());
            pst.setString(11, s.getMotherName());
            pst.executeUpdate();
            try (ResultSet keys = pst.getGeneratedKeys()) {
                if (keys.next()) s.setId(keys.getInt(1));
            }
        }
    }

    @Override
    public List<Student> getAll() throws Exception {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM studentdatamgmt";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Student s = new Student(
                        rs.getInt("Id"),
                        rs.getString("FirstName"),
                        rs.getString("Surname"),
                        rs.getInt("Age"),
                        rs.getString("DateOfBirth"),
                        rs.getString("Gender").charAt(0),
                        rs.getLong("contactNo"),
                        rs.getInt("YearOfJoin"),
                        rs.getString("Department"),
                        rs.getString("class"),
                        rs.getString("FatherName"),
                        rs.getString("MotherName")
                );
                list.add(s);
            }
        }
        return list;
    }

    @Override
    public boolean deleteById(int id) throws Exception {
        String sql = "DELETE FROM studentdatamgmt WHERE Id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            return pst.executeUpdate() > 0;
        }
    }

    @Override
    public boolean update(Student s) throws Exception {
        String sql = "UPDATE studentdatamgmt SET FirstName=?, Surname=?, Age=?, DateOfBirth=?, Gender=?, contactNo=?, YearOfJoin=?, Department=?, class=?, FatherName=?, MotherName=? WHERE Id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, s.getFirstName());
            pst.setString(2, s.getSurname());
            pst.setInt(3, s.getAge());
            pst.setString(4, s.getDateOfBirth());
            pst.setString(5, String.valueOf(s.getGender()));
            pst.setLong(6, s.getContactNo());
            pst.setInt(7, s.getYearOfJoin());
            pst.setString(8, s.getDepartment());
            pst.setString(9, s.getClassName());
            pst.setString(10, s.getFatherName());
            pst.setString(11, s.getMotherName());
            pst.setInt(12, s.getId());
            return pst.executeUpdate() > 0;
        }
    }
}