package com.jobportal.dao;

import com.jobportal.DBConnection;
import com.jobportal.model.JobTechStack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobTechStackDAO {

    // CREATE
    public void addTechnology(JobTechStack tech) {
        String sql = "INSERT INTO job_tech_stack " +
                "(tech_id, post_id, technology) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, tech.getTechId());
            ps.setInt(2, tech.getPostId());
            ps.setString(3, tech.getTechnology());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Technology added successfully." :
                    "Technology was not added.");

        } catch (SQLException e) {
            System.out.println("Could not add technology: " + e.getMessage());
        }
    }

    // READ ALL
    public void getAllTechnologies() {
        String sql = "SELECT tech_id, post_id, technology " +
                "FROM job_tech_stack ORDER BY tech_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "Tech ID: " + rs.getInt("tech_id") +
                        " | Post ID: " + rs.getInt("post_id") +
                        " | Technology: " + rs.getString("technology"));
            }

            if (!found) {
                System.out.println("No technologies found.");
            }

        } catch (SQLException e) {
            System.out.println("Could not read technologies: " + e.getMessage());
        }
    }

    // READ BY POST ID
    public void getTechnologiesByPostId(int postId) {
        String sql = "SELECT tech_id, post_id, technology " +
                "FROM job_tech_stack WHERE post_id = ? ORDER BY tech_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println(
                            "Tech ID: " + rs.getInt("tech_id") +
                            " | Post ID: " + rs.getInt("post_id") +
                            " | Technology: " + rs.getString("technology"));
                }

                if (!found) {
                    System.out.println("No technologies found for this post.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Could not read technologies: " + e.getMessage());
        }
    }

    // UPDATE
    public void updateTechnology(JobTechStack tech) {
        String sql = "UPDATE job_tech_stack SET technology = ? WHERE tech_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, tech.getTechnology());
            ps.setInt(2, tech.getTechId());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Technology updated successfully." :
                    "Technology not found.");

        } catch (SQLException e) {
            System.out.println("Could not update technology: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteTechnology(int techId) {
        String sql = "DELETE FROM job_tech_stack WHERE tech_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, techId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Technology deleted successfully." :
                    "Technology not found.");

        } catch (SQLException e) {
            System.out.println("Could not delete technology: " + e.getMessage());
        }
    }
}
