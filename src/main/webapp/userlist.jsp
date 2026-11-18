<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.personalexpensetracker.User" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.TimeZone" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registered Users</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: 'Poppins', sans-serif;
            background-color: #f5f7fa;
            color: #333;
        }

        .container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            font-size: 32px;
            color: #2c3e50;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 16px;
        }

        th, td {
            padding: 14px 20px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #2c3e50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        form {
            margin: 0;
        }

        input[type="submit"] {
            padding: 8px 14px;
            border: none;
            background-color: #e74c3c;
            color: white;
            border-radius: 6px;
            cursor: pointer;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #c0392b;
        }

        @media (max-width: 768px) {
            .container {
                margin: 20px;
                padding: 10px;
            }

            table, th, td {
                font-size: 14px;
            }

            h2 {
                font-size: 24px;
            }
        }
    </style>
</head>
<body>

<div class="container">
    <h2>Registered Users</h2>
    <table>
        <tr>
            <th>User ID</th>
            <th>Username</th>
            <th>Last Login</th>
            <th>Action</th>
        </tr>

        <%
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Set your timezone here

            List<User> users = (List<User>) request.getAttribute("userList");
            if (users != null && !users.isEmpty()) {
                for (User user : users) {
        %>
        <tr>
            <td><%= user.getUserId() %></td>
            <td><%= user.getUsername() %></td>
            <td>
                <%
                    if (user.getLastLogin() != null) {
                        out.print(sdf.format(user.getLastLogin()));
                    } else {
                        out.print("Never Logged In");
                    }
                %>
            </td>
            <td>
                <form action="deleteuser" method="post" onsubmit="return confirm('Are you sure you want to remove this user?');">
                    <input type="hidden" name="user_id" value="<%= user.getUserId() %>">
                    <input type="submit" value="Remove">
                </form>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="4" style="text-align: center; color: #999;">No users found.</td>
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>
