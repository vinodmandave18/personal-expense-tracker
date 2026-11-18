package com.mycompany.personalexpensetracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/DeleteExpenseServlet")
public class DeleteExpenseServlet extends HttpServlet {

    private ExpenseDAO expenseDAO;

    @Override
    public void init() throws ServletException {
        expenseDAO = new ExpenseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        HttpSession session = request.getSession();

        if (idParam != null && !idParam.isEmpty()) {
            try {
                int expenseId = Integer.parseInt(idParam);

                boolean deleted = expenseDAO.deleteExpenseById(expenseId);
                if (deleted) {
                    session.setAttribute("successMessage", "Expense deleted successfully!");
                } else {
                    session.setAttribute("successMessage", "Failed to delete expense. Please try again.");
                }

            } catch (NumberFormatException e) {
                session.setAttribute("successMessage", "Invalid expense ID.");
            }
        } else {
            session.setAttribute("successMessage", "No expense selected to delete.");
        }

        response.sendRedirect("ViewExpensesServlet");
    }
}
