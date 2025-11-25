import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentDataManagement {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("----- Student Management System -----");

        Login login = new Login();

        System.out.print("Enter Username: ");
        String user = sc.nextLine();

        System.out.print("Enter Password: ");
        String pass = sc.nextLine();

        if (login.validateUser(user, pass)) {
            System.out.println("Login Successful!");
            menu();
        } else {
            System.out.println("Invalid Login!");
        }
    }

    public static void menu() {
        while (true) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void addStudent() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            sc.nextLine();
            System.out.print("Enter Student Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            String query = "INSERT INTO students(name, course) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setString(1, name);
            pst.setString(2, course);

            pst.executeUpdate();
            System.out.println("Student Added Successfully!");

            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewStudents() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            String query = "SELECT * FROM students";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            System.out.println("\nID   Name      Course");
            System.out.println("---------------------------");

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "   " + rs.getString("name") + "   " + rs.getString("course"));
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent() {
        try (Connection conn = DatabaseConnection.getConnection()) {

            System.out.print("Enter Student ID to delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM students WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("Student Deleted Successfully!");

            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
