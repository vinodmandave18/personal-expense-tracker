<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Admin Dashboard</title>
    <style>
        /* Reset some default */
        * {
            box-sizing: border-box;
        }
        body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: linear-gradient(135deg, #eceff1 0%, #f4f7f9 100%);
            color: #34495e;
            display: flex;
            min-height: 100vh;
        }

        /* Sidebar */
        .sidebar {
            position: fixed;
            width: 220px;
            height: 100%;
            background-color: #2f3c4f;
            padding: 30px 20px;
            color: #fff;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .sidebar h2 {
            font-size: 22px;
            color: #1abc9c;
            margin-bottom: 50px;
            font-weight: 700;
            letter-spacing: 1.1px;
        }
        .sidebar ul {
            list-style: none;
            padding: 0;
            margin: 0;
            flex-grow: 1;
        }
        .sidebar ul li {
            margin-bottom: 18px;
        }
        .sidebar ul li a,
        .sidebar ul li {
            color: #bdc3c7;
            text-decoration: none;
            font-weight: 600;
            font-size: 16px;
            display: block;
            padding: 10px 12px;
            border-radius: 6px;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .sidebar ul li:hover,
        .sidebar ul li a:hover {
            background-color: #1abc9c;
            color: white;
            cursor: pointer;
        }
        .logout-btn {
            display: block;
            background-color: #e74c3c;
            color: white;
            text-align: center;
            padding: 12px 0;
            border-radius: 8px;
            font-weight: 700;
            text-decoration: none;
            transition: background-color 0.3s ease;
            margin-top: auto;
        }
        .logout-btn:hover {
            background-color: #c0392b;
        }

        /* Main content */
        .main {
            margin-left: 220px;
            padding: 40px 50px;
            flex-grow: 1;
        }
        .main h1 {
            font-size: 36px;
            margin-bottom: 35px;
            color: #2c3e50;
            font-weight: 800;
            letter-spacing: 0.03em;
        }

        /* Cards grid */
        .cards {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 25px;
        }
        .card {
            background: white;
            border-radius: 15px;
            box-shadow: 0 6px 18px rgba(0, 0, 0, 0.1);
            padding: 30px 25px;
            transition: transform 0.25s ease, box-shadow 0.25s ease;
            cursor: default;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            min-height: 130px;
        }
        .card:hover {
            transform: translateY(-6px);
            box-shadow: 0 12px 30px rgba(26, 188, 156, 0.3);
        }

        /* Card titles */
        .card.today { border-left: 6px solid #3498db; }
        .card.yesterday { border-left: 6px solid #f39c12; }
        .card.week { border-left: 6px solid #1abc9c; }
        .card.month { border-left: 6px solid #e74c3c; }
        .card.year { border-left: 6px solid #9b59b6; }
        .card.total { border-left: 6px solid #2ecc71; }
        .card.categories { border-left: 6px solid #34495e; }
        .card.users { border-left: 6px solid #16a085; }

        .card span {
            font-size: 36px;
            font-weight: 900;
            color: #2c3e50;
            margin-top: 15px;
            user-select: none;
        }

        .card:first-child {
            user-select: none;
        }
    </style>
</head>
<body>

    <aside class="sidebar">
        <h2>DAILY EXPENSE<br>TRACKER ADMIN</h2>
        <ul>
            <li><a href="#">Dashboard</a></li>
            <li><a href="userlist">Registered Users</a></li>
        </ul>
        <a href="${pageContext.request.contextPath}/admin/logout" class="logout-btn">Logout</a>
    </aside>

    <main class="main">
        <h1>Dashboard</h1>

        <div class="cards">
            <div class="card today">
                Today's Expense:
                <span>${todayExpense}</span>
            </div>
            <div class="card yesterday">
                Yesterday's Expense:
                <span>${yesterdayExpense}</span>
            </div>
            <div class="card week">
                Last 7 Days Expense:
                <span>${last7DaysExpense}</span>
            </div>
            <div class="card month">
                Last 30 Days Expense:
                <span>${last30DaysExpense}</span>
            </div>
            <div class="card year">
                Current Year Expenses:
                <span>${yearExpense}</span>
            </div>
            <div class="card total">
                Total Expenses:
                <span>${totalExpense}</span>
            </div>
            <div class="card categories">
                Total Listed Categories:
                <span>${totalCategories}</span>
            </div>
            <div class="card users">
                Total Registered Users:
                <span>${totalUsers}</span>
            </div>
        </div>
    </main>

</body>
</html>
