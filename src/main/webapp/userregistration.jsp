<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>User Registration</title>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap');

    body {
      font-family: 'Inter', sans-serif;
      background: #f8f9fa;
      margin: 0;
      padding: 2rem;
      display: flex;
      justify-content: center;
    }

    .container {
      background: #fff;
      border-radius: 12px;
      box-shadow: 0 8px 24px rgb(0 0 0 / 0.1);
      max-width: 600px;
      width: 100%;
      padding: 2.5rem 3rem;
    }

    h1 {
      font-weight: 600;
      font-size: 1.5rem;
      margin-bottom: 1.5rem;
      display: flex;
      align-items: center;
      gap: 0.5rem;
    }

    h1 span {
      font-size: 1.8rem;
    }

    label {
      display: block;
      margin-bottom: 0.5rem;
      font-weight: 500;
      font-size: 0.9rem;
      color: #333;
    }

    input, select {
      width: 100%;
      padding: 0.7rem 1rem;
      margin-bottom: 1.5rem;
      border: 1px solid #ddd;
      border-radius: 8px;
      font-size: 1rem;
      transition: border-color 0.3s;
    }

    input:focus, select:focus {
      outline: none;
      border-color: #2563eb;
      box-shadow: 0 0 6px rgba(37, 99, 235, 0.3);
    }

    button {
      width: 100%;
      background-color: #2563eb;
      color: white;
      font-weight: 600;
      padding: 0.75rem;
      border: none;
      border-radius: 10px;
      font-size: 1.1rem;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    button:hover {
      background-color: #1e40af;
    }

    .error-message {
      background-color: #f8d7da;
      color: #842029;
      border: 1px solid #f5c2c7;
      padding: 1rem;
      margin-bottom: 1.5rem;
      border-radius: 8px;
    }

    .success-message {
      background-color: #d1e7dd;
      color: #0f5132;
      border: 1px solid #badbcc;
      padding: 1rem;
      margin-bottom: 1.5rem;
      border-radius: 8px;
    }
  </style>

  <%-- ✅ Always redirect to index.html in 3 seconds if message is shown --%>
  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String successMessage = (String) request.getAttribute("successMessage");
    if (errorMessage != null || successMessage != null) {
  %>
    <meta http-equiv="refresh" content="3;URL=index.jsp" />
  <%
    }
  %>
</head>
<body>

<div class="container">
  <h1><span>📝</span> User Registration</h1>

  <% if (errorMessage != null) { %>
    <div class="error-message"><%= errorMessage %><br>You will be redirected in 3 seconds...</div>
  <% } else if (successMessage != null) { %>
    <div class="success-message"><%= successMessage %><br>You will be redirected in 3 seconds...</div>
  <% } %>

  <form action="register" method="POST">
    <label for="username">Username</label>
    <input id="username" name="username" type="text" placeholder="Enter your username" required />

    <label for="email">Email</label>
    <input id="email" name="email" type="email" placeholder="Enter your email" required />

    <label for="password">Password</label>
    <input id="password" name="password" type="password" placeholder="Enter your password" required />

    <label for="currency">Preferred Currency</label>
    <select id="currency" name="currency" required>
      <option value="" disabled selected>Select your currency</option>
      <option value="INR">INR (₹)</option>
      <option value="USD">USD ($)</option>
      <option value="EUR">EUR (€)</option>
      <option value="GBP">GBP (£)</option>
      <option value="JPY">JPY (¥)</option>
    </select>

    <button type="submit">Register</button>
  </form>
</div>

</body>
</html>
