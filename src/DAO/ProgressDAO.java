package DAO;

import MODEL.Progress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgressDAO {
    // Database credentials and connection URL for MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/afrilingo";
    private static final String USER = "root"; // Change if needed
    private static final String PASSWORD = ""; // Set your XAMPP MySQL password

    private Connection conn;

    // Load MySQL JDBC Driver and establish connection
    public ProgressDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Error connecting to the database.");
        }
    }

    // Create
    public void createProgress(Progress progress) {
        String query = "INSERT INTO Progress (user_id, module_id, progress_status, completion_date, score, attempts) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, progress.getUserId());
            stmt.setInt(2, progress.getModuleId());
            stmt.setString(3, progress.getProgressStatus());
            stmt.setDate(4, Date.valueOf(progress.getCompletionDate())); // Use java.sql.Date for MySQL
            stmt.setInt(5, progress.getScore());
            stmt.setInt(6, progress.getAttempts());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new progress record was inserted successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Error inserting progress record.");
            e.printStackTrace();
        }
        // Do NOT close the connection here
    }

    // Read All
    public List<Progress> getAllProgress() {
        List<Progress> progressList = new ArrayList<>();
        String query = "SELECT * FROM Progress";

        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Progress progress = new Progress();
                progress.setProgressId(rs.getInt("progress_id"));
                progress.setUserId(rs.getInt("user_id"));
                progress.setModuleId(rs.getInt("module_id"));
                progress.setProgressStatus(rs.getString("progress_status"));
                progress.setCompletionDate(rs.getString("completion_date"));
                progress.setScore(rs.getInt("score"));
                progress.setAttempts(rs.getInt("attempts"));

                progressList.add(progress);
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving progress records.");
            e.printStackTrace();
        }
        // Do NOT close the connection here
        return progressList;
    }

    // Update
    public void updateProgress(Progress progress) {
        String query = "UPDATE Progress SET user_id = ?, module_id = ?, progress_status = ?, completion_date = ?, score = ?, attempts = ? WHERE progress_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, progress.getUserId());
            stmt.setInt(2, progress.getModuleId());
            stmt.setString(3, progress.getProgressStatus());
            stmt.setDate(4, Date.valueOf(progress.getCompletionDate())); // Use java.sql.Date
            stmt.setInt(5, progress.getScore());
            stmt.setInt(6, progress.getAttempts());
            stmt.setInt(7, progress.getProgressId());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Progress record updated successfully!");
            } else {
                System.out.println("No progress record found with the provided ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error updating progress record.");
            e.printStackTrace();
        }
        // Do NOT close the connection here
    }

    // Delete
    public void deleteProgress(int progressId) {
        String query = "DELETE FROM Progress WHERE progress_id = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, progressId);

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Progress record deleted successfully!");
            } else {
                System.out.println("No progress record found with the provided ID.");
            }

        } catch (SQLException e) {
            System.out.println("Error deleting progress record.");
            e.printStackTrace();
        }
        // Do NOT close the connection here
    }

    // Close connection only when necessary, such as after all database operations are completed
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
