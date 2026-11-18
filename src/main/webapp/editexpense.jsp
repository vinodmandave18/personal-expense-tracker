<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.personalexpensetracker.ExpenseDTO" %>
<%@ page import="com.mycompany.personalexpensetracker.Category" %>
<%@ page import="java.util.List" %>

<%
    ExpenseDTO expense = (ExpenseDTO) request.getAttribute("expense");
    List<Category> categories = (List<Category>) request.getAttribute("categories");

    if (expense == null || categories == null) {
        response.sendRedirect("ViewExpensesServlet");
        return;
    }

    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Edit Expense</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<div class="container mt-5">
    <h2>Edit Expense</h2>

    <% if (errorMessage != null) { %>
        <div class="alert alert-danger"><%= errorMessage %></div>
    <% } %>

    <form action="EditExpenseServlet" method="post">
        <input type="hidden" name="id" value="<%= expense.getExpenseId() %>">

        <div class="mb-3">
            <label>Category</label>
            <select name="category" class="form-control" required>
                <% for (Category cat : categories) { %>
                    <option value="<%= cat.getId() %>" 
                        <%= cat.getId() == expense.getCategoryId() ? "selected" : "" %>>
                        <%= cat.getName() %>
                    </option>
                <% } %>
            </select>
        </div>

        <div class="mb-3">
            <label>Amount</label>
            <input type="number" step="0.01" name="amount" class="form-control" value="<%= expense.getAmount() %>" required>
        </div>

        <div class="mb-3">
            <label>Date</label>
            <input type="date" name="date" class="form-control"
                   value="<%= expense.getDate() != null ? new java.text.SimpleDateFormat("yyyy-MM-dd").format(expense.getDate()) : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Time</label>
            <input type="time" name="time" class="form-control"
                   value="<%= expense.getTime() != null ? expense.getTime().toString() : "" %>" required>
        </div>

        <div class="mb-3">
            <label>Notes</label>
            <input type="text" name="notes" class="form-control" value="<%= expense.getNotes() != null ? expense.getNotes() : "" %>">
        </div>

        <button type="submit" class="btn btn-success">Update Expense</button>
        <a href="ViewExpensesServlet" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
