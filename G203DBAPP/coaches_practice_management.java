package G203DBAPP;

import java.sql.*;
import java.util.Scanner;

public class coaches_practice_management {

    // Attributes
    public String reserveID;
    public String venueID;
    public String practiceDate;
    public String startTime;
    public String endTime;
    public String coachID;

    // Default Constructor
    public coaches_practice_management() {
        reserveID = "";
        venueID = "";
        practiceDate = "";
        startTime = "";
        endTime = "";
        coachID = "";
    }

    // Helper method to establish a database connection
    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=your_password");
    }

    // Check if venue is available for a new reservation
    public boolean isVenueAvailable() {
        String sql = "SELECT * FROM venuereservation WHERE venueID = ? AND date = ? AND reserveID != ? AND " +
                     "(? < endTime AND ? > startTime)"; // Overlap condition: start < end AND end > start

        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, venueID);      // Check for the same venue
            pstmt.setString(2, practiceDate); // Check for the same date
            pstmt.setString(3, reserveID);    // Exclude the current session
            pstmt.setString(4, endTime);      // Check new end time
            pstmt.setString(5, startTime);    // Check new start time

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Error: The venue is already reserved at this time by another coach.");
                return false; // Conflict detected
            }
            return true; // No conflict found
        } catch (SQLException e) {
            System.out.println("Error during venue availability check: " + e.getMessage());
            return false;
        }
    }

    // Check if an update is allowed based on overlapping reservations
    private boolean isUpdateAllowed(String venueID, String practiceDate, String startTime, String endTime, String reserveID) {
        String sql = "SELECT * FROM venuereservation WHERE venueID = ? AND date = ? AND reserveID != ? AND " +
                     "(? < endTime AND ? > startTime)"; // Overlap condition

        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, venueID);
            pstmt.setString(2, practiceDate);
            pstmt.setString(3, reserveID);
            pstmt.setString(4, endTime + ":00"); // New end time
            pstmt.setString(5, startTime + ":00"); // New start time

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return false; // Conflict detected
            }
            return true; // No conflict found
        } catch (SQLException e) {
            System.out.println("Error during overlap check: " + e.getMessage());
            return false; // Assume conflict on error
        }
    }

    // Add a practice session
    public int addPractice() {
        if (!isVenueAvailable()) {
            return 0; // Venue not available
        }

        String sql = "INSERT INTO venuereservation (reserveID, venueID, date, startTime, endTime, coachID) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reserveID);
            pstmt.setString(2, venueID);
            pstmt.setString(3, practiceDate);
            pstmt.setString(4, startTime + ":00");
            pstmt.setString(5, endTime + ":00");
            pstmt.setString(6, coachID);

            pstmt.executeUpdate();
            System.out.println("Practice session added successfully.");
            return 1;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // View a practice session
    public int viewPractice() {
        String sql = "SELECT * FROM venuereservation WHERE reserveID = ?";

        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reserveID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("Practice ID    : " + rs.getString("reserveID"));
                System.out.println("Venue ID       : " + rs.getString("venueID"));
                System.out.println("Practice Date  : " + rs.getString("date"));
                System.out.println("Start Time     : " + rs.getString("startTime"));
                System.out.println("End Time       : " + rs.getString("endTime"));
                System.out.println("Coach ID       : " + rs.getString("coachID"));
                return 1;
            } else {
                System.out.println("No practice session found with the given ID.");
                return 0;
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Update a practice session
    public int updatePracticeInteractive(Scanner scanner) {
        // Check if the reserveID exists before proceeding with the update
        if (!reserveIDExists(reserveID)) {
            System.out.println("Update failed: The reserveID does not exist.");
            return 0;  // Exit early if reserveID does not exist
        }

        String sql = "UPDATE venuereservation SET ";
        StringBuilder updates = new StringBuilder();
        boolean checkOverlap = false;  // Flag to indicate whether we need to check for overlap

        System.out.println("Leave fields blank to keep them unchanged.");

        System.out.print("Enter new Venue ID: ");
        String newVenueID = scanner.nextLine();
        if (!newVenueID.isEmpty()) {
            updates.append("venueID = ?, ");
            venueID = newVenueID;
        }

        System.out.print("Enter new Practice Date (YYYY-MM-DD): ");
        String newPracticeDate = scanner.nextLine();
        if (!newPracticeDate.isEmpty()) {
            updates.append("date = ?, ");
            practiceDate = newPracticeDate;
        }

        System.out.print("Enter new Start Time (HH:mm): ");
        String newStartTime = scanner.nextLine();
        if (!newStartTime.isEmpty()) {
            updates.append("startTime = ?, ");
            startTime = newStartTime + ":00";
            checkOverlap = true;  // We need to check for overlap if the start time is updated
        }

        System.out.print("Enter new End Time (HH:mm): ");
        String newEndTime = scanner.nextLine();
        if (!newEndTime.isEmpty()) {
            updates.append("endTime = ?, ");
            endTime = newEndTime + ":00";
            checkOverlap = true;  // We need to check for overlap if the end time is updated
        }

        // If no updates are specified, return early
        if (updates.length() == 0) {
            System.out.println("No updates specified.");
            return 0;
        }

        // Remove trailing comma and space
        updates.delete(updates.length() - 2, updates.length());
        sql += updates + " WHERE reserveID = ?";

        // If there is a change in start time or end time, check for overlap
        if (checkOverlap) {
            // Only check for overlap when relevant fields (start/end time) are updated
            if (!isUpdateAllowed(
                    newVenueID.isEmpty() ? venueID : newVenueID,
                    newPracticeDate.isEmpty() ? practiceDate : newPracticeDate,
                    newStartTime.isEmpty() ? startTime : newStartTime,
                    newEndTime.isEmpty() ? endTime : newEndTime,
                    reserveID)) {

                System.out.println("Update failed: Time overlap with another coach's reservation.");
                return 0;  // Exit early if there is an overlap
            }
        }

        // Execute the update statement
        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            int index = 1;

            if (!newVenueID.isEmpty()) pstmt.setString(index++, venueID);
            if (!newPracticeDate.isEmpty()) pstmt.setString(index++, practiceDate);
            if (!newStartTime.isEmpty()) pstmt.setString(index++, startTime);
            if (!newEndTime.isEmpty()) pstmt.setString(index++, endTime);

            pstmt.setString(index, reserveID);  // Set the reserveID for the WHERE clause

            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Practice session updated successfully." : "Failed to update practice session.");
            return rows;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }

    // Method to check if a reserveID exists in the database
    private boolean reserveIDExists(String reserveID) {
        String sql = "SELECT 1 FROM venuereservation WHERE reserveID = ?";
        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, reserveID);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();  // If a row is returned, the reserveID exists
        } catch (SQLException e) {
            System.out.println("Error during reserveID check: " + e.getMessage());
            return false;
        }
    }


    // Delete a practice session
    public int deletePractice() {
        String sql = "DELETE FROM venuereservation WHERE reserveID = ?";
        try (Connection conn = connectToDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, reserveID);
            int rows = pstmt.executeUpdate();
            System.out.println(rows > 0 ? "Practice session deleted successfully." : "No practice session found to delete.");
            return rows;

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return 0;
        }
    }
}
