package controller;

import dao.StudentDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class StudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        StudentDAO.addStudent(
            req.getParameter("name"),
            req.getParameter("email"),
            req.getParameter("course")
        );
        res.sendRedirect("students.jsp");
    }
}
