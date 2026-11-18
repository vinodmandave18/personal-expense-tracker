package com.mycompany.personalexpensetracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/EditExpenseServlet")
public class EditExpenseServlet extends HttpServlet {

    private ExpenseDAO expenseDAO;

    @Override
    public void init() throws ServletException {
        expenseDAO = new ExpenseDAO();
    }

    // -----------------------------
    // Load Edit Form
    // -----------------------------
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            response.sendRedirect("ViewExpensesServlet");
            return;
        }

        int expenseId;
        try {
            expenseId = Integer.parseInt(idParam);
        } catch (NumberFormatException e) {
            response.sendRedirect("ViewExpensesServlet");
            return;
        }

        ExpenseDTO expense = expenseDAO.getExpenseById(expenseId);
        if (expense == null) {
            response.sendRedirect("ViewExpensesServlet");
            return;
        }

        List<Category> categories = expenseDAO.getAllCategories();
        request.setAttribute("expense", expense);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("editexpense.jsp").forward(request, response);
    }

    // -----------------------------
    // Handle Update Submission
    // -----------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String categoryParam = request.getParameter("category");
        String amountParam = request.getParameter("amount");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String notes = request.getParameter("notes");

        if (idParam == null || categoryParam == null || amountParam == null ||
            idParam.isEmpty() || categoryParam.isEmpty() || amountParam.isEmpty() ||
            date == null || date.isEmpty() || time == null || time.isEmpty()) {

            request.setAttribute("errorMessage", "Please fill in all required fields.");
            doGet(request, response);
            return;
        }

        int expenseId, categoryId;
        double amount;
        try {
            expenseId = Integer.parseInt(idParam);
            categoryId = Integer.parseInt(categoryParam);
            amount = Double.parseDouble(amountParam);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid number format.");
            doGet(request, response);
            return;
        }

        boolean updated = expenseDAO.updateExpense(expenseId, categoryId, amount, date, time, notes);

        if (updated) {
            HttpSession session = request.getSession();
            session.setAttribute("successMessage", "Expense updated successfully!");
            response.sendRedirect("ViewExpensesServlet");
        } else {
            request.setAttribute("errorMessage", "Failed to update expense.");
            doGet(request, response);
        }
    }
}


