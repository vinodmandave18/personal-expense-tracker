
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <style>
        body {
            background: #0a0b0f;
            color: #fff;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        form {
            background: #1a1a1a;
            padding: 30px;
            border-radius: 10px;
            border: 1px solid #d2b48c;
            width: 300px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 10px;
            margin: 10px 0;
            border-radius: 5px;
            border: none;
        }
        button {
            width: 100%;
            padding: 10px;
            background: #d2b48c;
            border: none;
            border-radius: 5px;
            font-weight: bold;
            color: #000;
            cursor: pointer;
        }
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<form action="AdminLoginServlet" method="post">
    <h2>Admin Login</h2>
    
    <% String error = request.getParameter("error");
       if (error != null) { %>
        <div class="error"><%= error %></div>
    <% } %>
    
    <input type="text" name="admin_id" placeholder="Admin ID" required />
    <input type="text" name="admin_name" placeholder="Admin Name" required />
    <input type="password" name="password" placeholder="Password" required />
    <button type="submit">Login</button>
</form>

</body>
</html>
