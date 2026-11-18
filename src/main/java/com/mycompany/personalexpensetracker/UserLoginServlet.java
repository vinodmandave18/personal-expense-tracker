package com.mycompany.personalexpensetracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/userlogin")
public class UserLoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String emailOrUsername = request.getParameter("emailOrUsername");
        String password = request.getParameter("password");

        // Get full UserDTO from DB
        UserDTO user = userDAO.getUserByEmailOrUsernameAndPassword(emailOrUsername, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user); // Store full user in session

            // ✅ Role-based redirect
            if ("admin".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("admin_dashboard.jsp");
            } else {
                response.sendRedirect("welcome.jsp");
            }
        } else {
            request.setAttribute("loginError", "Invalid username/email or password");
            request.getRequestDispatcher("userlogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("userlogin.jsp").forward(request, response);
    }
}
