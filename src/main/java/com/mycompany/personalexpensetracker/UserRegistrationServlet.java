package com.mycompany.personalexpensetracker;

import java.io.IOException; 
import javax.servlet.ServletException; 
import javax.servlet.annotation.WebServlet; 
import javax.servlet.http.HttpServlet; 
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class UserRegistrationServlet extends HttpServlet { 
    @Override protected void doPost(HttpServletRequest request, 
            HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8"); 
        response.setContentType("text/html;charset=UTF-8"); 
        String username = request.getParameter("username"); 
        String email = request.getParameter("email"); 
        String password = request.getParameter("password");
        String currency = request.getParameter("currency");
        // ✅ Field validation
        if (username == null || email == null || password == null ||
        currency == null || 
               username.isEmpty() || email.isEmpty() || password.isEmpty() || 
                currency.isEmpty()){
         request.setAttribute("errorMessage", "All fields are required. Please fill out the form.");
    request.getRequestDispatcher("userregistration.jsp").forward(request, response);
    return;
        }
        UserDAO userDAO = new UserDAO();
        // ✅ Check if email already registered
        if (userDAO.isEmailRegistered(email)) {
            request.setAttribute("errorMessage", "You are already registered with this email.");
         request.getRequestDispatcher("userregistration.jsp").forward(request, response); 
         return; 
        }
        // ✅ Check if username already taken 
        if (userDAO.isUsernameRegistered(username)) {
            request.setAttribute("errorMessage", "This username is already taken.");
         request.getRequestDispatcher("userregistration.jsp").forward(request, response);
         return;
        }
        // ✅ Call DAO to register (password will be hashed in DAO)
        boolean success = userDAO.registerUser(username, email, password, currency);
        if (success) { 
            request.setAttribute("successMessage", "Registration completed successfully!");
        }
        else { 
            request.setAttribute("errorMessage", "Registration failed. Please try again.");
        }
         
request.getRequestDispatcher("userregistration.jsp").forward(request, response); }
}
        