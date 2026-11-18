package com.mycompany.personalexpensetracker;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/admindashboard.jsp")
public class AdminDashboardServlet extends HttpServlet {

    private ExpenseDAO expenseDAO;

    @Override
    public void init() throws ServletException {
        expenseDAO = new ExpenseDAO(); // Initialize DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false); // don't create new session

        // Check if admin is logged in
        if (session == null || session.getAttribute("admin_logged_in") == null) {
            // Not logged in, redirect to admin login page
            response.sendRedirect(request.getContextPath() + "/adminlogin.jsp");
            return;
        }

        // Now admin is logged in, fetch dashboard data
        int todayExpense = expenseDAO.getTodayExpense();
        int yesterdayExpense = expenseDAO.getYesterdayExpense();
        int last7DaysExpense = expenseDAO.getLast7DaysExpense();
        int last30DaysExpense = expenseDAO.getLast30DaysExpense();
        int yearExpense = expenseDAO.getYearlyExpense();
        int totalExpense = expenseDAO.getTotalExpense();
        int totalCategories = expenseDAO.getTotalCategories();
        int totalUsers = expenseDAO.getTotalUsers();

        // Set data in request scope
        request.setAttribute("todayExpense", todayExpense);
        request.setAttribute("yesterdayExpense", yesterdayExpense);
        request.setAttribute("last7DaysExpense", last7DaysExpense);
        request.setAttribute("last30DaysExpense", last30DaysExpense);
        request.setAttribute("yearExpense", yearExpense);
        request.setAttribute("totalExpense", totalExpense);
        request.setAttribute("totalCategories", totalCategories);
        request.setAttribute("totalUsers", totalUsers);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("/admindashboard.jsp");
        dispatcher.forward(request, response);
    }
}
