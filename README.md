🎓 Student Management System
Java Web Application using Servlets & JDBC

🚀 A secure and efficient Student Management System developed using Java Web Technologies, following MVC architecture and implementing Role-Based Access Control (RBAC) for enhanced security.

📌 Project Overview

The Student Management System is a web-based application designed to manage student records digitally.
It allows authenticated users to add, view, and manage student data while ensuring that unauthorized access is restricted using a Servlet Filter.

This project is built as an academic Java Web project and is deployed on Apache Tomcat.

🧠 Key Highlights

✅ Java Web-based application
✅ Follows MVC Architecture
✅ Secure authentication system
✅ Role-Based Access Control (RBAC)
✅ Database connectivity using JDBC
✅ Clean and modular code structure


🛠️ Technologies Used

☕ Java (JDK 8+)
🌐 Servlets
🖥️ JSP
🔗 JDBC
🗄️ MySQL
🚀 Apache Tomcat
🧰 IntelliJ IDEA (Enterprise Java)

🏗️ Architecture Used
📐 MVC Architecture

Model: JDBC, DAO classes, MySQL Database
View: JSP Pages
Controller: Servlets

Ensures separation of concerns and easy maintenance

🔐 Security – Role-Based Access Control (RBAC)

🔒 Security is implemented using a Servlet Filter:

User role is stored in the HTTP Session
Filter checks the role before accessing secured resources
Unauthorized users are redirected to the login page
Prevents direct URL access

StudentManagementSystem
│
├── src/main/java
│   ├── controller
│   │   ├── LoginServlet.java
│   │   └── StudentServlet.java
│   │
│   ├── dao
│   │   ├── DatabaseConnection.java
│   │   └── StudentDAO.java
│   │
│   └── filter
│       └── AuthFilter.java
│
├── src/main/webapp
│   ├── login.jsp
│   ├── dashboard.jsp
│   ├── students.jsp
│   │
│   └── WEB-INF
│       └── web.xml

🗄️ Database Design
👤 Users Table
Field       	Description
username	    User login name
password     	User password
role	        User role (ADMIN / STUDENT)
🎓 Students Table
Field	        Description
id	            Student ID
name        	Student name
email       	Student email
course      	Course enrolled

✨ Features

🔑 User Authentication (Login System)
➕ Add Student Details
👀 View Student Records
🔐 Role-based Access Control
🧩 Modular & reusable code

▶️ How to Run the Project

Import the project into Eclipse as a Dynamic Web Project
Configure Apache Tomcat Server
Add MySQL Connector JAR to build path
Create database using provided SQL script
Run project on server

Open in browser:
http://localhost:8080/StudentManagementSystem/login.jsp

🎯 Academic Purpose

Designed for Java Web Development coursework
Demonstrates real-world usage of:

Servlets
JDBC
Filters
MVC Pattern
Suitable for Review / Viva / Practical Exams

🏁 Conclusion

The Student Management System is a secure, scalable, and well-structured Java Web Application.
It demonstrates core concepts of Java Web Development, Database Connectivity, and Web Security.

🤝 Contributors
👨‍💻 Team Details

Team Name: Encoders

Arushi Dubey
Aksa Malik
Shrashti 

📝 License

This project is open-source and free to use for educational purposes.
