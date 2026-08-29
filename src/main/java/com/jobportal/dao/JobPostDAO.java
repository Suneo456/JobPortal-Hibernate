package com.jobportal.dao;

import com.jobportal.DBConnection;
import com.jobportal.model.JobPost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobPostDAO {

    // CREATE
    public void addJobPost(JobPost job) {
        String sql = "INSERT INTO job_post " +
                "(post_id, user_id, post_profile, post_desc, req_experience) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, job.getPostId());
            ps.setInt(2, job.getUserId());
            ps.setString(3, job.getPostProfile());
            ps.setString(4, job.getPostDesc());

            if (job.getReqExperience() == null) {
                ps.setNull(5, java.sql.Types.INTEGER);
            } else {
                ps.setInt(5, job.getReqExperience());
            }

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Job post added successfully." :
                    "Job post was not added.");

        } catch (SQLException e) {
            System.out.println("Could not add job post: " + e.getMessage());
        }
    }

    // READ ALL
    public void getAllJobPosts() {
        String sql = "SELECT post_id, user_id, post_profile, post_desc, req_experience " +
                "FROM job_post ORDER BY post_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                        "Post ID: " + rs.getInt("post_id") +
                        " | User ID: " + rs.getInt("user_id") +
                        " | Profile: " + rs.getString("post_profile") +
                        " | Description: " + rs.getString("post_desc") +
                        " | Experience: " + rs.getObject("req_experience"));
            }

            if (!found) {
                System.out.println("No job posts found.");
            }

        } catch (SQLException e) {
            System.out.println("Could not read job posts: " + e.getMessage());
        }
    }

    // READ BY ID
    public JobPost getJobPostById(int postId) {
        String sql = "SELECT post_id, user_id, post_profile, post_desc, req_experience " +
                "FROM job_post WHERE post_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    JobPost job = new JobPost(
                            rs.getInt("post_id"),
                            rs.getInt("user_id"),
                            rs.getString("post_profile"),
                            rs.getString("post_desc"),
                            (Integer) rs.getObject("req_experience")
                    );
                    System.out.println(job);
                    return job;
                }
            }

            System.out.println("Job post not found.");
            return null;

        } catch (SQLException e) {
            System.out.println("Could not find job post: " + e.getMessage());
            return null;
        }
    }

    // UPDATE
    public void updateJobPost(JobPost job) {
        String sql = "UPDATE job_post SET post_profile = ?, " +
                "post_desc = ?, req_experience = ? WHERE post_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, job.getPostProfile());
            ps.setString(2, job.getPostDesc());

            if (job.getReqExperience() == null) {
                ps.setNull(3, java.sql.Types.INTEGER);
            } else {
                ps.setInt(3, job.getReqExperience());
            }

            ps.setInt(4, job.getPostId());

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Job post updated successfully." :
                    "Job post not found.");

        } catch (SQLException e) {
            System.out.println("Could not update job post: " + e.getMessage());
        }
    }

    // DELETE
    public void deleteJobPost(int postId) {
        String sql = "DELETE FROM job_post WHERE post_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, postId);

            int rows = ps.executeUpdate();
            System.out.println(rows > 0 ? "Job post deleted successfully." :
                    "Job post not found.");

        } catch (SQLException e) {
            System.out.println("Could not delete job post: " + e.getMessage());
            System.out.println("Hint: delete technologies belonging to this post first.");
        }
    }
}
