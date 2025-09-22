package org.example.FinTech.repository;

import org.example.FinTech.entities.Account;
import org.example.FinTech.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository {


    public void addAccount(Account acc) {
        String sql = "INSERT INTO accounts(user_id, account_type, balance) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, acc.getUserId());
            stmt.setString(2, acc.getAccountType());
            stmt.setDouble(3, acc.getBalance());

            stmt.executeUpdate();

            // Get the generated ID and set it back into the Account object
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                acc.setId(rs.getInt(1));
            }

            System.out.println("Account added: " + acc);

        } catch (SQLException e) {
            System.err.println("Database error (addAccount): " + e.getMessage());
        }
    }


    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                accounts.add(new Account(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")
                ));
            }

        } catch (SQLException e) {
            System.err.println("Database error (getAllAccounts): " + e.getMessage());
        }
        return accounts;
    }


    public Account getAccountById(int id) {
        String sql = "SELECT * FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("account_type"),
                        rs.getDouble("balance")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error (getAccountById): " + e.getMessage());
        }
        return null;
    }


    public boolean updateAccount(Account acc) {
        String sql = "UPDATE accounts SET user_id=?, account_type=?, balance=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, acc.getUserId());
            stmt.setString(2, acc.getAccountType());
            stmt.setDouble(3, acc.getBalance());
            stmt.setInt(4, acc.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error (updateAccount): " + e.getMessage());
        }
        return false;
    }

    public boolean deleteAccount(int id) {
        String sql = "DELETE FROM accounts WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error (deleteAccount): " + e.getMessage());
        }
        return false;
    }

    public boolean updateBalance(int accountId, double amountChange) {
        String sql = "UPDATE accounts SET balance = balance + ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, amountChange);
            stmt.setInt(2, accountId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Database error (updateBalance): " + e.getMessage());
        }
        return false;
    }


    public double getAccountBalance(int accountId) {
        String sql = "SELECT balance FROM accounts WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }

        } catch (SQLException e) {
            System.err.println("Database error (getAccountBalance): " + e.getMessage());
        }
        return 0.0;
    }
}
