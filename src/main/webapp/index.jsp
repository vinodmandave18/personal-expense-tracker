<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<title>Welcome to Expense Tracker</title>
<link href="https://fonts.googleapis.com/css2?family=Poppins:wght@600;700&display=swap" rel="stylesheet">

<style>
  body, html {
    margin: 0;
    height: 100%;
    font-family: 'Poppins', sans-serif;
    background: linear-gradient(135deg, #74ebd5, #ACB6E5);
    display: flex;
    justify-content: center;
    align-items: center;
    color: #333;
  }

  .menu-container {
    background: #fff;
    border-radius: 24px;
    padding: 3.5rem 3rem;
    width: 480px;
    box-shadow: 0 10px 30px rgba(0,0,0,0.2);
    text-align: center;
    transition: transform 0.3s ease, box-shadow 0.3s ease;
  }

  .menu-container:hover {
    transform: translateY(-6px);
    box-shadow: 0 18px 45px rgba(0,0,0,0.3);
  }

  .menu-container h2 {
    font-weight: 700;
    font-size: 2.4rem;
    margin-bottom: 2.5rem;
    color: #111;
  }

  .btn-menu {
    display: block;
    margin: 1rem auto;
    padding: 16px 0;
    width: 100%;
    font-weight: 700;
    font-size: 1.3rem;
    border-radius: 18px;
    border: none;
    cursor: pointer;
    text-decoration: none;
    transition: 0.3s;
    box-shadow: 0 6px 16px rgba(0,0,0,0.1);
  }

  .btn-menu:hover {
    transform: scale(1.05);
    box-shadow: 0 8px 20px rgba(0,0,0,0.2);
  }

  .btn-admin {
    background: #111;
    color: #fff;
  }

  .btn-user-login {
    background: #3b82f6;
    color: #fff;
  }

  .btn-user-register {
    background: #22c55e;
    color: #fff;
  }

  .btn-admin::before {
    content: "🔑 ";
  }
  .btn-user-login::before {
    content: "👤 ";
  }
  .btn-user-register::before {
    content: "📝 ";
  }

  @media (max-width: 480px) {
    .menu-container {
      width: 90vw;
      padding: 2.5rem 1.5rem;
    }
    .menu-container h2 {
      font-size: 1.8rem;
      margin-bottom: 2rem;
    }
    .btn-menu {
      font-size: 1.1rem;
      padding: 14px 0;
    }
  }
</style>
</head>
<body>

<div class="menu-container" role="main" aria-label="Welcome menu">
  <h2>Welcome to Personal Expenses Tracker</h2>
  <a href="adminlogin.jsp" class="btn-menu btn-admin">Admin Login</a>
  <a href="userlogin.jsp" class="btn-menu btn-user-login">User Login</a>
  <a href="userregistration.jsp" class="btn-menu btn-user-register">User Registration</a>
</div>

</body>
</html>
