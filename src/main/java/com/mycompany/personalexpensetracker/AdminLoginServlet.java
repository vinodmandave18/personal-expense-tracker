package com.mycompany.personalexpensetracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {

    private AdminDAO adminDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection conn = DBUtil.getConnection(); // ✅ Use your DBUtil here
            adminDAO = new AdminDAO(conn);
        } catch (SQLException e) {
            throw new ServletException("Database connection failed", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String adminId = request.getParameter("admin_id");
        String adminName = request.getParameter("admin_name");
        String password = request.getParameter("password");

        try {
            boolean valid = adminDAO.validateAdmin(adminId, adminName, password);
            if (valid) {
                HttpSession session = request.getSession();
                session.setAttribute("admin_logged_in", true);
                session.setAttribute("admin_name", adminName);
                response.sendRedirect("admin/dashboard"); // go to dashboard servlet
            } else {
                response.sendRedirect("adminlogin.jsp?error=Invalid credentials");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("adminlogin.jsp?error=Database error");
        }
    }
}
