package G203DBAPP;

import java.sql.*;

public class athlete_peformance_report {

    public String performanceDate;  // Used to store the performance date (e.g., "1990-01-30")
    public int recordcount;

    public athlete_peformance_report() {
        // Default constructor
    }

    // Helper method to establish a database connection
    private Connection connectToDatabase() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=your_password");
    }

    // Method to generate the performance report for a specific date
    public int generate_performance_report() {
        recordcount = 0;
        try (Connection conn = connectToDatabase()) {

            // SQL query to retrieve performance data for the specified date
            String query = "SELECT PerformanceID, athleteID, role, date, score " +
                           "FROM performance " +
                           "WHERE date = ? " +
                           "ORDER BY athleteID, date;";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, performanceDate);  // Set the performanceDate

                // Executing the query and displaying the results
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("Performance History for " + performanceDate);
                    System.out.printf("%-15s %-10s %-20s %-15s %-20s\n", "Performance ID", "Athlete ID", "Role", "Date", "Performance Score");

                    // Check if any data is retrieved
                    if (!rs.next()) {
                        System.out.println("No performance data found for the specified date.");
                    } else {
                        // Display the retrieved data
                        do {
                            recordcount++;
                            System.out.printf("%-15s %-10s %-20s %-15s %-20s\n", 
                                    rs.getString("PerformanceID"),
                                    rs.getString("athleteID"),
                                    rs.getString("role"),
                                    rs.getDate("date"),  // Using getDate() to retrieve the date
                                    rs.getInt("score"));
                        } while (rs.next());

                        System.out.println("End of Report");
                    }
                }
            }

            return recordcount; // Return the count of records fetched
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or fetching performance report: " + e.getMessage());
            return 0; // Failure
        }
    }

    // Method to view the performance history for a specific athlete based on the criteria
    public int view_performance(String athleteID, String role, String date) {
        recordcount = 0;
        this.performanceDate = date;  // Set performanceDate from input parameter

        try (Connection conn = connectToDatabase()) {

            // SQL query to filter by athleteID, role, and performanceDate
            String query = "SELECT PerformanceID, athleteID, role, date, score " +
                           "FROM performance " +
                           "WHERE athleteID = ? " +
                           "AND role = ? " +
                           "AND date = ? " +
                           "ORDER BY date;";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, athleteID);  // Setting the athleteID parameter
                pstmt.setString(2, role);        // Setting the role parameter
                pstmt.setString(3, performanceDate);  // Setting the performanceDate parameter

                // Executing the query
                try (ResultSet rs = pstmt.executeQuery()) {
                    System.out.println("Performance History for Athlete ID: " + athleteID + " (" + role + ") on " + performanceDate);
                    System.out.printf("%-15s %-10s %-20s %-15s %-20s\n", "Performance ID", "Athlete ID", "Role", "Date", "Performance Score");

                    // Check if any data is retrieved
                    if (!rs.next()) {
                        System.out.println("No performance records found for the given criteria.");
                    } else {
                        // Display the retrieved data
                        do {
                            recordcount++;
                            System.out.printf("%-15s %-10s %-20s %-15s %-20s\n", 
                                    rs.getString("PerformanceID"),
                                    rs.getString("athleteID"),
                                    rs.getString("role"),
                                    rs.getDate("date"),  // Using getDate() to retrieve the date
                                    rs.getInt("score"));
                        } while (rs.next());

                        System.out.println("End of Report");
                    }
                }
            }

            return 1; // Success
        } catch (SQLException e) {
            System.out.println("Error connecting to the database or fetching performance history: " + e.getMessage());
            return 0; // Failure
        }
    }
}
