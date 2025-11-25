📘 Student Management System – Java (Console Based)

A simple yet structured Student Management System built using Core Java, JDBC, and MySQL.
This project allows users to Add, View, and Delete student records stored in a MySQL database.


🚀 Features

✔ Secure Login System

Username & Password authentication

Login validation using JDBC


✔ Student Operations

Add Student → Stores name and course

View Students → Displays all students

Delete Student → Remove a student by ID


✔ Database Connectivity

Uses JDBC (PreparedStatement & Connection)

Protects from SQL Injection

Well-structured code with DAO (Data Access Object) layers


✔ User-Friendly Console Interface

Clean menu options

Beginner-friendly & easy to use



---

🛠 Tech Stack Used

Component	Technology

Language	Java (Core Java)
Database	MySQL / MariaDB
Connectivity	JDBC
Tool/IDE	IntelliJ IDEA / VS Code / Eclipse
Build Tool	Maven (optional)



---

📁 Project Structure

Student-Management-System
│
├── src
│   ├── main
│   │   ├── java
│   │   │   ├── student
│   │   │   │   ├── AppMain.java
│   │   │   │   ├── dao
│   │   │   │   │   ├── StudentDAO.java
│   │   │   │   │   ├── StudentDAOImpl.java
│   │   │   │   ├── db
│   │   │   │   │   ├── DatabaseConnection.java
│   │   │   │   └── models
│   │   │   │       └── Student.java
│   │   └── resources
│
├── README.md
└── pom.xml  (if Maven project)



🗄️ Database Setup

1️⃣ Create a Database

Open MySQL CLI / Workbench / phpMyAdmin:

CREATE DATABASE student_db;
USE student_db;

2️⃣ Create Students Table

CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    course VARCHAR(100)
);

3️⃣ Update Database Credentials

In DatabaseConnection.java:

private static final String URL = "jdbc:mysql://localhost:3306/student_db";
private static final String USER = "root";
private static final String PASS = "your_mysql_password";



▶ How to Run the Project

Step 1: Clone the Repository

git clone https://github.com/Arushidubey0605/Student-Management-System-.git

Step 2: Open Project in IntelliJ / VSCode / Eclipse

Step 3: Add MySQL JDBC Connector

If using Maven, add it inside pom.xml:

<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.0.33</version>
</dependency>

If not using Maven → Download connector .jar & add to project libraries.

Step 4: Run Main File

Run:

AppMain.java


📸 Project Output (Console UI)

----- Student Management System -----

Enter Username: admin
Enter Password: 1234

Login Successful!

----- MENU -----
1. Add Student
2. View Students
3. Delete Student
4. Exit


📦 Future Enhancements

Here are planned updates for Review 2 or Review 3:

Convert console project to Servlet + JSP

Add Update Student feature

Add Search by ID / Name

Add Bootstrap Admin UI

Add Signup + Login with encryption

Deploy on Tomcat Server


🤝 Contributors

Team Name: Encoders

Arushi Dubey
Aksa Malik
Shrashti 

📝 License

This project is open-source and free to use for educational purposes.
