package org.example.FinTech.repository;

import org.example.FinTech.entities.User;
import org.example.FinTech.utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserRepositoryDB {

    private static final Logger logger = Logger.getLogger(UserRepositoryDB.class.getName());


    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"),
                rs.getDouble("balance")
        );
    }


    public void addUser(User user) {
        String sql = "INSERT INTO users(name, email, balance) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setDouble(3, user.getBalance());
            stmt.executeUpdate();

            // Get the generated ID
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }

            logger.info("User added: " + user);

        } catch (SQLException e) {
            logger.severe("Database error in addUser: " + e.getMessage());
        }
    }


    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            logger.severe("Database error in getAllUsers: " + e.getMessage());
        }
        return users;
    }


    public Optional<User> getUserById(int id) {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(mapResultSetToUser(rs));
            }

        } catch (SQLException e) {
            logger.severe("Database error in getUserById: " + e.getMessage());
        }
        return Optional.empty();
    }


    public void updateUser(User user) {
        String sql = "UPDATE users SET name=?, email=?, balance=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getEmail());
            stmt.setDouble(3, user.getBalance());
            stmt.setInt(4, user.getId());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("User updated: " + user);
            }

        } catch (SQLException e) {
            logger.severe("Database error in updateUser: " + e.getMessage());
        }
    }


    public boolean deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                logger.info("User deleted with id=" + id);
                return true;
            }

        } catch (SQLException e) {
            logger.severe("Database error in deleteUser: " + e.getMessage());
        }
        return false;
    }
}
