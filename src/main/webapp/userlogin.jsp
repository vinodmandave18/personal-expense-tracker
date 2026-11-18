<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>User Login</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
      background: #0a0b0f;
      color: #fff;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .login-box {
      background: #1a1a1a;
      padding: 30px;
      border-radius: 10px;
      width: 300px;
      text-align: center;
      border: 1px solid #d2b48c;
    }

    .login-box h2 {
      margin-bottom: 20px;
      color: #d2b48c;
    }

    .login-box input {
      width: 100%;
      padding: 10px;
      margin: 10px 0;
      border: none;
      border-radius: 5px;
    }

    .login-box button {
      width: 100%;
      padding: 10px;
      background: #d2b48c;
      border: none;
      border-radius: 5px;
      color: #000;
      font-weight: bold;
      cursor: pointer;
    }

    .login-box button:hover {
      background: #b7955d;
    }

    .login-box a {
      display: block;
      margin-top: 10px;
      color: #d2b48c;
      font-size: 14px;
      text-decoration: none;
    }

    .login-box a:hover {
      text-decoration: underline;
    }

    .error-message {
      color: red;
      margin-bottom: 10px;
    }
  </style>
</head>
<body>
  <div class="login-box">
    <h2>User Login</h2>

    <% 
      String error = (String) request.getAttribute("loginError"); 
      if (error != null) { 
    %>
      <p class="error-message"><%= error %></p>
    <% 
      } 
    %>

    <form action="userlogin" method="POST">
      <input name="emailOrUsername" type="text" placeholder="Email or Username" required>
      <input name="password" type="password" placeholder="Password" required>
      <button type="submit">Login</button>
    </form>
  </div>
</body>
</html>
