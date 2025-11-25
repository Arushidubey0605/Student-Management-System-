package org.example;

import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("----- Student Management System -----");

        LoginDao login = new LoginDao();

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (login.validateUser(username, password)) {
            System.out.println("Login Successful!");
            menu();
        } else {
            System.out.println("Invalid Login!");
        }
    }

    public static void menu() {

        StudentDao studentDao = new StudentDao();

        while (true) {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume extra newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Student Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine();

                    studentDao.addStudent(name, course);
                    break;

                case 2:
                    studentDao.viewStudents();
                    break;

                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    int id = sc.nextInt();
                    studentDao.deleteStudent(id);
                    break;

                case 4:
                    System.out.println("Thank you!");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
