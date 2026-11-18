<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.mycompany.personalexpensetracker.Category" %>
<%@ page import="java.util.List" %>
<%@ page session="true" %>

<%
    // Protect page: only logged-in users
    if (session == null || session.getAttribute("loggedInUser") == null) {
        response.sendRedirect("userlogin");
        return;
    }

    List<Category> categories = (List<Category>) request.getAttribute("categories");
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Expense</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <h3 class="text-center mb-4">💳 Add Expense</h3>
    
    <div class="card shadow p-4">

        <% if (errorMessage != null) { %>
            <div class="alert alert-danger"><%= errorMessage %></div>
        <% } %>

        <form action="AddExpenseServlet" method="POST" novalidate>

            <!-- Category Dropdown -->
            <div class="mb-3">
                <label class="form-label" for="category">Category</label>
                <select name="category" class="form-select">
    <option value="">-- Select Category --</option>
    <% if (categories != null) {
        for (Category c : categories) { %>
            <option value="<%= c.getId() %>"><%= c.getName() %></option>
    <%  }
    } %>
</select>
            </div>

            <!-- Amount -->
            <div class="mb-3">
                <label class="form-label" for="amount">Amount</label>
                <input type="number" step="0.01" min="0" name="amount" class="form-control" required>
            </div>

            <!-- Date -->
            <div class="mb-3">
                <label class="form-label" for="date">Date</label>
                <input type="date" name="date" class="form-control" required>
            </div>

            <!-- Time -->
            <div class="mb-3">
                <label class="form-label" for="time">Time</label>
                <input type="time" name="time" class="form-control" required>
            </div>

            <!-- Notes -->
            <div class="mb-3">
                <label class="form-label" for="notes">Notes</label>
                <textarea name="notes" class="form-control" rows="3"></textarea>
            </div>

           

            <!-- Buttons -->
            <div class="d-flex justify-content-between">
                <a href="welcome.jsp" class="btn btn-secondary">← Back</a>
                <button type="submit" class="btn btn-success">Save Expense</button>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>



