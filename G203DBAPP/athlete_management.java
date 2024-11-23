package G203DBAPP;

import java.sql.*;

public class athlete_management {
    public String athleteID;
    public String firstName;
    public String lastName;
    public String middleInitial;
    public String birthday;
    public String gender;
    public String teamID;  // Now part of athlete table
    public String role;

    // Performance fields
    public String performanceID;
    public String date;
    public int score;

    public athlete_management() {
        athleteID = "";
        firstName = "";
        lastName = "";
        middleInitial = "";
        birthday = "";
        gender = "";
        teamID = "";
        role = "";
        performanceID = "";
        date = "";
        score = 0;
    }

    // Add a new athlete (teamID is directly in the athlete table)
    public void add_athlete() {
        // Define the connection details
        String url = "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC";
        String user = "root"; // Your MySQL username
        String password = "your_password"; // Your MySQL password

        // SQL query to insert new athlete into the database
        String query = "INSERT INTO athletes (athleteID, firstName, lastName, middleInitial, birthday, gender, teamID, role) "
                     + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Set the parameters for the SQL query
            pstmt.setString(1, this.athleteID);
            pstmt.setString(2, this.firstName);
            pstmt.setString(3, this.lastName);
            pstmt.setString(4, this.middleInitial);
            pstmt.setString(5, this.birthday);
            pstmt.setString(6, this.gender);
            pstmt.setString(7, this.teamID);
            pstmt.setString(8, this.role);

            // Execute the query to insert the new athlete
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Athlete successfully added to the database!");
            } else {
                System.out.println("Failed to add athlete to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding athlete to the database: " + e.getMessage());
        }
    }

    // Update existing athlete data, including teamID directly in athlete table
    public int update_athlete() {
        try {
        	Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC", 
                    "root", "your_password");

            // Check if athlete exists
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT * FROM athletes WHERE athleteID=?"
            );
            checkStmt.setString(1, athleteID);
            ResultSet rs = checkStmt.executeQuery();
            if (!rs.next()) {
                System.out.println("Athlete with ID " + athleteID + " does not exist.");
                return 0;
            }

            // Update athlete data conditionally
            StringBuilder updateQuery = new StringBuilder("UPDATE athletes SET ");
            boolean first = true;
            if (!firstName.isEmpty()) {
                updateQuery.append("firstName=?, ");
                first = false;
            }
            if (!lastName.isEmpty()) {
                updateQuery.append("lastName=?, ");
                first = false;
            }
            if (!middleInitial.isEmpty()) {
                updateQuery.append("middleInitial=?, ");
                first = false;
            }
            if (!birthday.isEmpty()) {
                updateQuery.append("birthday=?, ");
                first = false;
            }
            if (!gender.isEmpty()) {
                updateQuery.append("gender=?, ");
                first = false;
            }
            if (!teamID.isEmpty()) {  // Update teamID if provided
                updateQuery.append("teamID=?, ");
                first = false;
            }
            if (!first) {
                updateQuery.deleteCharAt(updateQuery.length() - 2); // Remove last comma
            }
            updateQuery.append("WHERE athleteID=?");

            PreparedStatement pstmt = conn.prepareStatement(updateQuery.toString());
            int index = 1;
            if (!firstName.isEmpty()) {
                pstmt.setString(index++, firstName);
            }
            if (!lastName.isEmpty()) {
                pstmt.setString(index++, lastName);
            }
            if (!middleInitial.isEmpty()) {
                pstmt.setString(index++, middleInitial);
            }
            if (!birthday.isEmpty()) {
                pstmt.setString(index++, birthday);
            }
            if (!gender.isEmpty()) {
                pstmt.setString(index++, gender);
            }
            if (!teamID.isEmpty()) {
                pstmt.setString(index++, teamID);  // Set teamID here
            }
            pstmt.setString(index, athleteID);
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Delete athlete and their associated team data (teamID is in athlete table now)
    public int delete_athlete() {
    	try {
    	    // Establish connection to the database
    	    Connection conn = DriverManager.getConnection(
    	            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC", 
    	            "root", "your_password");

    	    // Check if athlete exists
    	    PreparedStatement checkStmt = conn.prepareStatement(
    	        "SELECT * FROM athletes WHERE athleteID=?"
    	    );
    	    checkStmt.setString(1, athleteID);
    	    ResultSet rs = checkStmt.executeQuery();
    	    if (!rs.next()) {
    	        System.out.println("Athlete with ID " + athleteID + " does not exist.");
    	        return 0; // Athlete not found
    	    }

    	    // Delete related performance records for the athlete
    	    PreparedStatement deletePerformanceStmt = conn.prepareStatement(
    	        "DELETE FROM performance WHERE athleteID=?"
    	    );
    	    deletePerformanceStmt.setString(1, athleteID);
    	    deletePerformanceStmt.executeUpdate();
    	    System.out.println("Performance records deleted successfully.");

    	    // Delete the athlete from the athlete table
    	    PreparedStatement deleteAthleteStmt = conn.prepareStatement(
    	        "DELETE FROM athletes WHERE athleteID=?"
    	    );
    	    deleteAthleteStmt.setString(1, athleteID);
    	    deleteAthleteStmt.executeUpdate();
    	    System.out.println("Athlete deleted successfully.");

    	    return 1; // Success
    	} catch (Exception e) {
    	    System.out.println(e.getMessage());
    	    return 0; // Failure
    	}
    }

    // View an athlete's details including teamID from athlete table
    public int view_athlete(String athleteID) {
        try {
            // Establish connection to the database
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC", 
                    "root", "your_password");

            // Check if athlete exists
            PreparedStatement checkStmt = conn.prepareStatement(
                "SELECT * FROM athletes WHERE athleteID=?"
            );
            checkStmt.setString(1, athleteID);
            ResultSet rs = checkStmt.executeQuery();

            // If no record is found for the given athleteID
            if (!rs.next()) {
                System.out.println("Athlete with ID " + athleteID + " not found.");
                return 0; // Return 0 if athlete doesn't exist
            }

            // Display athlete details
            System.out.println("Athlete details for ID: " + athleteID);
            System.out.println("First Name: " + rs.getString("firstName"));
            System.out.println("Last Name: " + rs.getString("lastName"));
            System.out.println("Middle Initial: " + rs.getString("middleInitial"));
            System.out.println("Birthday: " + rs.getDate("birthday"));
            System.out.println("Gender: " + rs.getString("gender"));
            System.out.println("Team ID: " + rs.getString("teamID"));
            System.out.println("Role: " + rs.getString("role"));

            return 1; // Success if athlete details are found
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return 0; // Failure
        }
    }

    // Add performance for an athlete (unchanged)
    public int add_performance() {
        try {
        	Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC", 
                    "root", "your_password");
            // Insert performance data
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO performance (performanceID, athleteID, date, score) VALUES (?,?,?,?)"
            );
            pstmt.setString(1, performanceID);
            pstmt.setString(2, athleteID);
            pstmt.setString(3, date);
            pstmt.setInt(4, score);
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Update performance data (unchanged)
    public int update_performance() {
        try {
        	Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC", 
                    "root", "your_password");
            // Update performance data for the athlete
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE performance SET date=?, score=? WHERE performanceID=?"
            );
            pstmt.setString(1, date);
            pstmt.setInt(2, score);
            pstmt.setString(3, performanceID);
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
