package G203DBAPP;

import java.sql.*;

public class athlete_management {
    public String athleteID;
    public String firstName;
    public String lastName;
    public String middleInitial;
    public String birthday;
    public String gender;
    public String teamID;
    public String role;

    // Performance fields
    public String performanceID;
    public String date;
    public int score;  // Changed score to integer

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
        score = 0;  // Default score set to 0
    }

    // Add a new athlete along with team data
    public int add_athlete() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC",  // Changed to mydb
            "user", "password")) {

            // Insert athlete data
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO athletes (athleteID, firstName, lastName, middleInitial, birthday, gender) VALUES (?,?,?,?,?,?)"
            );
            pstmt.setString(1, athleteID);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, middleInitial);
            pstmt.setString(5, birthday);
            pstmt.setString(6, gender);
            pstmt.executeUpdate();

            // Insert athlete-team relation
            pstmt = conn.prepareStatement(
                "INSERT INTO `athlete-team` (athleteID, teamID, role) VALUES (?, ?, ?)"
            );
            pstmt.setString(1, athleteID);
            pstmt.setString(2, teamID);
            pstmt.setString(3, role);
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Update existing athlete and team data with conditional updates
    public int update_athlete() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC",  // Changed to mydb
            "user", "password")) {

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
            if (!first) { 
                updateQuery.deleteCharAt(updateQuery.length() - 2); // Remove last comma
            }
            updateQuery.append("WHERE athleteID=?");

            // Prepare the statement
            PreparedStatement pstmt = conn.prepareStatement(updateQuery.toString());

            // Set the parameters for the query
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
            pstmt.setString(index, athleteID); // Set athleteID at the end

            // Execute the update query
            pstmt.executeUpdate();

            // Update athlete-team relation conditionally
            StringBuilder updateTeamQuery = new StringBuilder("UPDATE `athlete-team` SET ");
            boolean teamFirst = true;
            if (!teamID.isEmpty()) {
                updateTeamQuery.append("teamID=?, ");
                teamFirst = false;
            }
            if (!role.isEmpty()) {
                updateTeamQuery.append("role=?, ");
                teamFirst = false;
            }
            if (!teamFirst) { 
                updateTeamQuery.deleteCharAt(updateTeamQuery.length() - 2); // Remove last comma
            }
            updateTeamQuery.append("WHERE athleteID=?");

            // Prepare the statement
            pstmt = conn.prepareStatement(updateTeamQuery.toString());

            // Set the parameters for the query
            int teamIndex = 1;
            if (!teamID.isEmpty()) {
                pstmt.setString(teamIndex++, teamID);
            }
            if (!role.isEmpty()) {
                pstmt.setString(teamIndex++, role);
            }
            pstmt.setString(teamIndex, athleteID); // Set athleteID at the end

            // Execute the update query
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Delete athlete and their associated team data
    public int delete_athlete() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC",  // Changed to mydb
            "user", "password")) {

            // Delete from athlete-team table
            PreparedStatement pstmt = conn.prepareStatement(
                "DELETE FROM `athlete-team` WHERE athleteID=?"
            );
            pstmt.setString(1, athleteID);
            pstmt.executeUpdate();

            // Delete from athletes table
            pstmt = conn.prepareStatement("DELETE FROM athletes WHERE athleteID=?");
            pstmt.setString(1, athleteID);
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // View an athlete's details along with their team and performance data
    public void view_athlete() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC",  // Changed to mydb
            "user", "password")) {

            // Select athlete data along with team role
            PreparedStatement pstmt = conn.prepareStatement(
                "SELECT a.*, t.teamID, t.role FROM athletes a JOIN `athlete-team` t ON a.athleteID = t.athleteID WHERE a.athleteID=?"
            );
            pstmt.setString(1, athleteID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Athlete ID: " + rs.getString("athleteID"));
                System.out.println("Name: " + rs.getString("firstName") + " " + rs.getString("lastName"));
                System.out.println("Team ID: " + rs.getString("teamID"));
                System.out.println("Role: " + rs.getString("role"));
            }

            // Fetch performance data for the athlete
            pstmt = conn.prepareStatement(
                "SELECT performanceID, date, score FROM performance WHERE athleteID=?"
            );
            pstmt.setString(1, athleteID);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.println("Performance ID: " + rs.getString("performanceID"));
                System.out.println("Date: " + rs.getString("date"));
                System.out.println("Score: " + rs.getInt("score"));  // Changed to int
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Add performance for an athlete
    public int add_performance() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC",  // Changed to mydb
            "user", "password")) {

            // Insert performance data
            PreparedStatement pstmt = conn.prepareStatement(
                "INSERT INTO performance (performanceID, athleteID, date, score) VALUES (?,?,?,?)"
            );
            pstmt.setString(1, performanceID);
            pstmt.setString(2, athleteID);
            pstmt.setString(3, date);
            pstmt.setInt(4, score);  // Changed to int
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Update performance data
    public int update_performance() {
        try (Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC",  // Changed to mydb
            "user", "password")) {

            // Update performance data
            PreparedStatement pstmt = conn.prepareStatement(
                "UPDATE performance SET date=?, score=? WHERE performanceID=?"
            );
            pstmt.setString(1, date);
            pstmt.setInt(2, score);  // Changed to int
            pstmt.setString(3, performanceID);
            pstmt.executeUpdate();

            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
