package com.jobportal.dao;

import com.jobportal.DBConnection;
import com.jobportal.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    // CREATE
    public void addUser(User user) {
        String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, user.getId());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "User added successfully." : "User was not added.");

        } catch (SQLException e) {
            System.out.println("Could not add user: " + e.getMessage());
        }
    }

    // READ ALL
    public void getAllUsers() {
        String sql = "SELECT id, username, password FROM users ORDER BY id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        "ID: " + rs.getInt("id") +
                        " | Username: " + rs.getString("username") +
                        " | Password: " + rs.getString("password"));
            }

            if (!found) {
                System.out.println("No users found.");
            }

        } catch (SQLException e) {
            System.out.println("Could not read users: " + e.getMessage());
        }
    }

    // READ BY ID
    public User getUserById(int id) {
        String sql = "SELECT id, username, password FROM users WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User user = new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password")
                    );
                    System.out.println(user);
                    return user;
                }
            }

            System.out.println("User not found.");
            return null;

        } catch (SQLException e) {
            System.out.println("Could not find user: " + e.getMessage());
            return null;
        }
    }

    // UPDATE
    public void updateUser(User user) {
        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getId());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "User updated successfully." : "User not found.");

        } catch (SQLException e) {
            System.out.println("Could not update user: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "User deleted successfully." :
                    "User not found. If the user has job posts, delete those first.");

        } catch (SQLException e) {
            System.out.println("Could not delete user: " + e.getMessage());
            System.out.println("Hint: delete the user's job posts and their technologies first.");
        }
    }
}
