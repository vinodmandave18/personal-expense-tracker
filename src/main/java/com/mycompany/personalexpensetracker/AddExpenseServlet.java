package com.mycompany.personalexpensetracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Time;
import java.util.List;

@WebServlet("/AddExpenseServlet")
public class AddExpenseServlet extends HttpServlet {

    private ExpenseDAO expenseDAO;

    @Override
    public void init() throws ServletException {
        expenseDAO = new ExpenseDAO();
    }

    // Display the add expense form
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("userlogin");
            return;
        }

        // Fetch categories from DB
        request.setAttribute("categories", new CategoryDAO().getAllCategories());

        request.getRequestDispatcher("addexpense.jsp").forward(request, response);
    }

    // Handle form submission
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("userlogin");
            return;
        }

        // ✅ Always set categories for redisplay in case of error
        request.setAttribute("categories", new CategoryDAO().getAllCategories());

        UserDTO user = (UserDTO) session.getAttribute("loggedInUser");
        String email = user.getEmail();

        try {
            // Read form parameters
            String categoryStr = request.getParameter("category");
            String amountStr = request.getParameter("amount");
            String date = request.getParameter("date");
            String time = request.getParameter("time");
            String notes = request.getParameter("notes");

            if (categoryStr == null || categoryStr.isEmpty()) {
                request.setAttribute("errorMessage", "Please select a category.");
                request.getRequestDispatcher("addexpense.jsp").forward(request, response);
                return;
            }

            int categoryId = Integer.parseInt(categoryStr);
            double amount = Double.parseDouble(amountStr);

            // Fix time format if only HH:mm
            if (time != null && time.length() == 5) time += ":00";

            String imagePath = ""; // placeholder; implement file upload later if needed

            boolean added = expenseDAO.addExpense(email, categoryId, amount,
                    java.sql.Date.valueOf(date), time, notes, imagePath);

            if (added) {
                session.setAttribute("successMessage", "Expense added successfully!");
                response.sendRedirect("ViewExpensesServlet");
            } else {
                request.setAttribute("errorMessage", "Failed to add expense.");
                request.getRequestDispatcher("addexpense.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid input: " + e.getMessage());
            request.getRequestDispatcher("addexpense.jsp").forward(request, response);
        }
    }
}
