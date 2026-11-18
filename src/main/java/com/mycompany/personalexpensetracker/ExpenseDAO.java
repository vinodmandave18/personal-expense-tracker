package com.mycompany.personalexpensetracker;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {

    // -----------------------------
    // ADD EXPENSE
    // -----------------------------
    public boolean addExpense(String userEmail, int categoryId,
                              double amount, java.sql.Date date, String time,
                              String notes, String imagePath) {
        try (Connection conn = DBUtil.getConnection()) {
            String userSql = "SELECT user_id FROM users WHERE email = ?";
            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setString(1, userEmail);
                try (ResultSet rs = userStmt.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("User not found for email: " + userEmail);
                        return false;
                    }
                    int userId = rs.getInt("user_id");

                    String insertSQL = "INSERT INTO expenses (user_id, category_id, amount, expense_date, expense_time, notes, image_path) " +
                                       "VALUES (?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                        stmt.setInt(1, userId);
                        stmt.setInt(2, categoryId);
                        stmt.setBigDecimal(3, BigDecimal.valueOf(amount));
                        stmt.setDate(4, date);
                        stmt.setTime(5, Time.valueOf(time));
                        stmt.setString(6, notes);
                        stmt.setString(7, imagePath);
                        return stmt.executeUpdate() > 0;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // -----------------------------
    // GET ALL EXPENSES (WITH USER DETAILS)
    // -----------------------------
    public List<ExpenseDTO> getAllExpensesWithUserDetails() {
        List<ExpenseDTO> expenses = new ArrayList<>();
        String query = "SELECT e.id AS expense_id, e.category_id, u.username, u.email, u.currency, " +
                       "c.name AS category_name, e.amount, e.expense_date, e.expense_time, e.notes " +
                       "FROM expenses e " +
                       "JOIN users u ON e.user_id = u.user_id " +
                       "JOIN categories c ON e.category_id = c.category_id " +
                       "ORDER BY e.expense_date DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ExpenseDTO dto = new ExpenseDTO();
                dto.setExpenseId(rs.getInt("expense_id"));
                dto.setCategoryId(rs.getInt("category_id"));
                dto.setUsername(rs.getString("username"));
                dto.setEmail(rs.getString("email"));
                dto.setCurrency(rs.getString("currency"));
                dto.setCategory(rs.getString("category_name"));
                dto.setAmount(rs.getBigDecimal("amount"));
                dto.setDate(rs.getDate("expense_date"));
                dto.setTime(rs.getTime("expense_time"));
                dto.setNotes(rs.getString("notes"));
                expenses.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // -----------------------------
    // GET EXPENSES BY EMAIL & DATE RANGE
    // -----------------------------
    public List<ExpenseDTO> getExpensesByEmailAndDateRange(String email, String startDate, String endDate) {
        List<ExpenseDTO> expenses = new ArrayList<>();
        String sql = "SELECT e.id AS expense_id, e.category_id, e.amount, e.expense_date, e.expense_time, " +
                     "e.notes, c.name AS category, u.currency " +
                     "FROM expenses e " +
                     "JOIN users u ON e.user_id = u.user_id " +
                     "JOIN categories c ON e.category_id = c.category_id " +
                     "WHERE u.email = ? AND e.expense_date BETWEEN ? AND ? " +
                     "ORDER BY e.expense_date DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setDate(2, java.sql.Date.valueOf(startDate));
            stmt.setDate(3, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ExpenseDTO expense = new ExpenseDTO();
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setCategoryId(rs.getInt("category_id"));
                    expense.setAmount(rs.getBigDecimal("amount"));
                    expense.setDate(rs.getDate("expense_date"));
                    expense.setTime(rs.getTime("expense_time"));
                    expense.setCategory(rs.getString("category"));
                    expense.setNotes(rs.getString("notes"));
                    expense.setCurrency(rs.getString("currency"));
                    expenses.add(expense);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // -----------------------------
    // GET TOTAL AMOUNT BY DATE RANGE
    // -----------------------------
    public double getTotalAmountByDateRange(String email, String startDate, String endDate) {
        double total = 0;
        String sql = "SELECT SUM(e.amount) AS total FROM expenses e " +
                     "JOIN users u ON e.user_id = u.user_id " +
                     "WHERE u.email = ? AND e.expense_date BETWEEN ? AND ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            stmt.setDate(2, java.sql.Date.valueOf(startDate));
            stmt.setDate(3, java.sql.Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = rs.getDouble("total");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return total;
    }

    // -----------------------------
    // GET EXPENSE BY ID
    // -----------------------------
    public ExpenseDTO getExpenseById(int expenseId) {
        String sql = "SELECT e.id AS expense_id, e.category_id, e.amount, e.expense_date, e.expense_time, " +
                     "e.notes, c.name AS category, u.username, u.email, u.currency " +
                     "FROM expenses e " +
                     "JOIN users u ON e.user_id = u.user_id " +
                     "JOIN categories c ON e.category_id = c.category_id " +
                     "WHERE e.id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expenseId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ExpenseDTO expense = new ExpenseDTO();
                    expense.setExpenseId(rs.getInt("expense_id"));
                    expense.setCategoryId(rs.getInt("category_id"));
                    expense.setAmount(rs.getBigDecimal("amount"));
                    expense.setDate(rs.getDate("expense_date"));
                    expense.setTime(rs.getTime("expense_time"));
                    expense.setCategory(rs.getString("category"));
                    expense.setNotes(rs.getString("notes"));
                    expense.setUsername(rs.getString("username"));
                    expense.setEmail(rs.getString("email"));
                    expense.setCurrency(rs.getString("currency"));
                    return expense;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // -----------------------------
    // GET ALL CATEGORIES
    // -----------------------------
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }

    // -----------------------------
    // DELETE EXPENSE BY ID
    // -----------------------------
    public boolean deleteExpenseById(int expenseId) {
        String sql = "DELETE FROM expenses WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, expenseId);
            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // -----------------------------
    // UPDATE EXPENSE
    // -----------------------------
    public boolean updateExpense(int expenseId, int categoryId, double amount,
                                 String date, String time, String notes) {
        String sql = "UPDATE expenses SET category_id=?, amount=?, expense_date=?, expense_time=?, notes=? WHERE id=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryId);
            stmt.setBigDecimal(2, BigDecimal.valueOf(amount));
            stmt.setDate(3, java.sql.Date.valueOf(date));
            stmt.setTime(4, java.sql.Time.valueOf(time));
            stmt.setString(5, notes);
            stmt.setInt(6, expenseId);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // -----------------------------
    // ADMIN DASHBOARD METHODS
    // -----------------------------
    public int getTodayExpense() {
        return getSingleInt("SELECT SUM(amount) AS total FROM expenses WHERE expense_date = CURRENT_DATE");
    }

    public int getYesterdayExpense() {
        return getSingleInt("SELECT SUM(amount) AS total FROM expenses WHERE expense_date = CURRENT_DATE - INTERVAL 1 DAY");
    }

    public int getLast7DaysExpense() {
        return getSingleInt("SELECT SUM(amount) AS total FROM expenses WHERE expense_date >= CURRENT_DATE - INTERVAL 7 DAY");
    }

    public int getLast30DaysExpense() {
        return getSingleInt("SELECT SUM(amount) AS total FROM expenses WHERE expense_date >= CURRENT_DATE - INTERVAL 30 DAY");
    }

    public int getYearlyExpense() {
        return getSingleInt("SELECT SUM(amount) AS total FROM expenses WHERE YEAR(expense_date) = YEAR(CURRENT_DATE)");
    }

    public int getTotalExpense() {
        return getSingleInt("SELECT SUM(amount) AS total FROM expenses");
    }

    public int getTotalCategories() {
        return getSingleInt("SELECT COUNT(*) AS total FROM categories");
    }

    public int getTotalUsers() {
        return getSingleInt("SELECT COUNT(*) AS total FROM users");
    }

    // -----------------------------
    // HELPER METHOD TO FETCH SINGLE INT
    // -----------------------------
    private int getSingleInt(String sql) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
