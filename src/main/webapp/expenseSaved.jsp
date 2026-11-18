<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Expense Saved</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            position: relative;
            background-color: #f8f9fa;
        }

        .message {
            font-size: 1.5em;
            color: green;
            text-align: center;
            margin-bottom: 20px;
        }

        .btn {
            padding: 10px 20px;
            font-size: 1em;
            cursor: pointer;
            border: none;
            border-radius: 4px;
            color: white;
        }

        .btn-save {
            background-color: #28a745;
            position: fixed;
            bottom: 20px;
            right: 20px;
        }

        .btn-back {
            background-color: #007bff;
            position: fixed;
            bottom: 20px;
            left: 20px;
        }
    </style>
</head>
<body>

    <div class="message">
        ✅ Expense saved successfully!
    </div>

    <!-- Back button bottom-left -->
    <form action="welcome.jsp" method="get">
        <button type="submit" class="btn btn-back">⬅ Back</button>
    </form>

    <!-- Save Expenses button bottom-right -->
    <form action="AddExpenseServlet" method="get">
        <button type="submit" class="btn btn-save">➕ Save Expenses</button>
    </form>

</body>
</html>
