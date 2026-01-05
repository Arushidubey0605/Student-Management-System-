package controller;

import dao.DatabaseConnection;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String user = req.getParameter("username");
        String pass = req.getParameter("password");

        try (Connection con = DatabaseConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                "SELECT role FROM users WHERE username=? AND password=?");
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                HttpSession session = req.getSession();
                session.setAttribute("role", rs.getString("role"));
                res.sendRedirect("dashboard.jsp");
            } else {
                res.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
