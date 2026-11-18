package com.mycompany.personalexpensetracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewExpensesServlet")
public class ViewExpensesServlet extends HttpServlet {

    private ExpenseDAO expenseDAO;

    @Override
    public void init() throws ServletException {
        expenseDAO = new ExpenseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            response.sendRedirect("userlogin");
            return;
        }

        UserDTO user = (UserDTO) session.getAttribute("loggedInUser");
        String email = user.getEmail();

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        List<ExpenseDTO> expenses;
        double totalAmount;

        if (startDate != null && endDate != null && !startDate.isEmpty() && !endDate.isEmpty()) {
            expenses = expenseDAO.getExpensesByEmailAndDateRange(email, startDate, endDate);
            totalAmount = expenseDAO.getTotalAmountByDateRange(email, startDate, endDate);
        } else {
            expenses = expenseDAO.getExpensesByEmailAndDateRange(email, "1970-01-01", "2100-12-31");
            totalAmount = expenseDAO.getTotalAmountByDateRange(email, "1970-01-01", "2100-12-31");
        }

        request.setAttribute("expenses", expenses);
        request.setAttribute("totalAmount", totalAmount);
        request.setAttribute("startDate", startDate);
        request.setAttribute("endDate", endDate);

        // Pass success message if available
        String successMessage = (String) session.getAttribute("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
            session.removeAttribute("successMessage");
        }

        request.getRequestDispatcher("viewexpenses.jsp").forward(request, response);
    }
}
