<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.personalexpensetracker.UserDTO" %>
<%
    // ✅ Get the user object from session
    UserDTO loggedInUser = (UserDTO) session.getAttribute("loggedInUser");

    if (loggedInUser == null) {
        response.sendRedirect("userlogin.jsp");
        return;
    }

    String userName = loggedInUser.getName(); // or getUsername()
    String userEmail = loggedInUser.getEmail(); // if needed
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome – ExpenseTrackerApp</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
        .welcome-container {
            text-align: center;
            margin-top: 50px;
        }
        .feature-box {
            padding: 20px;
            border-radius: 10px;
            border: 1px solid #ccc;
            background-color: #f8f9fa;
            margin: 15px;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light px-4">
        <a class="navbar-brand" href="#">ExpenseTrackerApp</a>
        <div class="ms-auto">
            <a class="nav-link d-inline" href="welcome.jsp">Home</a>
            <a class="nav-link d-inline" href="privacy.jsp">Privacy</a>
        </div>
        <div class="ms-auto">
    <span class="navbar-text me-3 text-muted">Welcome, <%= userName %>!</span>
    
    <a class="nav-link d-inline text-danger" href="logout.jsp">Logout</a>
</div>
    </nav>

    <div class="container welcome-container">
        <h1 class="display-5 text-primary fw-bold">Welcome to ExpenseTrackerApp</h1>
        <p class="text-muted">Track your daily expenses efficiently and stay on top of your budget.</p>
        <div class="mt-4">
            <a href="ViewExpensesServlet" class="btn btn-outline-primary">View Expenses</a>
            <a href="AddExpenseServlet" class="btn btn-success">Add New Expense</a>
        </div>

        <hr class="my-5" />

        <div class="row justify-content-center">
            <div class="col-md-3 feature-box">
                <h5><strong>Track Spending</strong></h5>
                <p>Easily manage and categorize your daily expenses.</p>
            </div>
            <div class="col-md-3 feature-box">
                <h5><strong>View Reports</strong></h5>
                <p>Get summaries and insights for better budgeting.</p>
            </div>
            <div class="col-md-3 feature-box">
                <h5><strong>Stay Organized</strong></h5>
                <p>Keep notes and timestamps for every transaction.</p>
            </div>
        </div>
    </div>

    <footer class="text-center mt-5">
        <p>&copy; 2025 – ExpenseTrackerApp – <a href="privacy.jsp">Privacy</a></p>
    </footer>
</body>
</html>
y>
</html>
