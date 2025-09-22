package org.example.FinTech.repository;

import org.example.FinTech.entities.Transaction;
import org.example.FinTech.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository {


    public void addTransaction(Transaction tx) {
        String sql = "INSERT INTO transactions(account_id, amount, type) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, tx.getAccountId());
            stmt.setDouble(2, tx.getAmount());
            stmt.setString(3, tx.getType());

            stmt.executeUpdate();


            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                tx.setId(rs.getInt(1));
            }

            System.out.println("Transaction saved: " + tx);

        } catch (SQLException e) {
            System.err.println("Database error (addTransaction): " + e.getMessage());
        }
    }


    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY timestamp DESC";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("account_id"),
                        rs.getDouble("amount"),
                        rs.getString("type"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.err.println("Database error (getAllTransactions): " + e.getMessage());
        }
        return transactions;
    }


    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_id = ? ORDER BY timestamp DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("id"),
                        rs.getInt("account_id"),
                        rs.getDouble("amount"),
                        rs.getString("type"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                ));
            }

        } catch (SQLException e) {
            System.err.println("Database error (getTransactionsByAccountId): " + e.getMessage());
        }
        return transactions;
    }


    public Transaction getTransactionById(int id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Transaction(
                        rs.getInt("id"),
                        rs.getInt("account_id"),
                        rs.getDouble("amount"),
                        rs.getString("type"),
                        rs.getTimestamp("timestamp").toLocalDateTime()
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error (getTransactionById): " + e.getMessage());
        }
        return null;
    }


    public boolean deleteTransaction(int id) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error (deleteTransaction): " + e.getMessage());
        }
        return false;
    }
}
