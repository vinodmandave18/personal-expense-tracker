<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.personalexpensetracker.ExpenseDTO" %>
<%@ page session="true" %>

<%
    if (session == null || session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("userlogin");
        return;
    }

    String successMessage = (String) request.getAttribute("successMessage");
    List<ExpenseDTO> expenses = (List<ExpenseDTO>) request.getAttribute("expenses");
    if (expenses == null) {
        expenses = new java.util.ArrayList<>();
    }

    String startDate = (String) request.getAttribute("startDate");
    String endDate = (String) request.getAttribute("endDate");
    Double totalAmount = (Double) request.getAttribute("totalAmount");
    if (totalAmount == null) {
        totalAmount = 0.0;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>View Expenses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<div class="container mt-5">

    <% if (successMessage != null) { %>
        <div class="alert alert-success alert-dismissible fade show text-center" role="alert">
            <%= successMessage %>
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
    <% } %>

    <h2 class="mb-4 text-center">💼 Expense List</h2>

    <!-- Filter Form -->
    <form action="ViewExpensesServlet" method="get" class="row g-3 mb-4">
        <div class="col-md-3">
            <label for="startDate" class="form-label">Start Date</label>
            <input type="date" class="form-control" name="startDate" id="startDate"
                   value="<%= startDate != null ? startDate : "" %>">
        </div>
        <div class="col-md-3">
            <label for="endDate" class="form-label">End Date</label>
            <input type="date" class="form-control" name="endDate" id="endDate"
                   value="<%= endDate != null ? endDate : "" %>">
        </div>
        <div class="col-md-3 align-self-end">
            <button type="submit" class="btn btn-primary">Filter</button>
        </div>
    </form>

    <!-- Show total amount -->
    <h5 class="<%= totalAmount > 0 ? "text-success" : "text-muted" %>">
        Total for selected period: ₹ <%= String.format("%.2f", totalAmount) %>
    </h5>

    <!-- Expenses Table -->
    <table class="table table-bordered table-striped mt-3">
        <thead class="table-dark text-center">
        <tr>
            <th>Category</th>
            <th>Amount</th>
            <th>Date</th>
            <th>Time</th>
            <th>Note</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <% if (expenses.isEmpty()) { %>
            <tr>
                <td colspan="6" class="text-center">No expenses found.</td>
            </tr>
        <% } else {
            for (ExpenseDTO expense : expenses) { %>
            <tr>
                <td><%= expense.getCategory() %></td>
                <td>₹ <%= String.format("%.2f", expense.getAmount().doubleValue()) %></td>
                <td><%= new java.text.SimpleDateFormat("MM/dd/yyyy").format(expense.getDate()) %></td>
                <td><%= expense.getTime() != null ? new java.text.SimpleDateFormat("HH:mm:ss").format(expense.getTime()) : "-" %></td>
                <td><%= expense.getNotes() != null ? expense.getNotes() : "No notes provided" %></td>
                <td>
                    <a href="EditExpenseServlet?id=<%= expense.getExpenseId() %>" class="btn btn-sm btn-primary">Edit</a>
                    <a href="DeleteExpenseServlet?id=<%= expense.getExpenseId() %>" 
                       class="btn btn-sm btn-danger" 
                       onclick="return confirm('Delete this expense?');">Delete</a>
                </td>
            </tr>
        <% } } %>
        </tbody>
    </table>

    <div class="d-flex justify-content-between mt-3">
        <a href="welcome.jsp" class="btn btn-secondary">← Back</a>
        <a href="AddExpenseServlet" class="btn btn-success">+ Add Expense</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
