package com.mycompany.personalexpensetracker;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/admin/logout") // This is fine even without a physical 'admin' folder
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Invalidate the session if exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Redirect to root index.jsp (not /admin/index.jsp!)
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
