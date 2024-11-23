package G203DBAPP;
import java.sql.*;

public class exercise_management {
	public String assignmentID;
    public String athleteID;
    public String exerciseID;
    public String assignedDate;
    public String scheduledDate;
    public String completedDate;
    public int setsAssigned;
    public int repsAssigned;
    public float weightAssigned;
    public String notes;
    public String status;
    
    public exercise_management() {
        assignmentID = "";
        athleteID = "";
        exerciseID = "";
        assignedDate = "";
        scheduledDate = "";
        completedDate = "";
        setsAssigned = 0;
        repsAssigned = 0;
        weightAssigned = 0;
        notes = "";
        status = "";
    }

    public int addExerciseRecord() {
        try {
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "Tyrone", "Easymoneysniper062123!!");

            System.out.println("Connection to DB Successful");
            String sql = "INSERT INTO exercise_record VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, assignmentID);
            pstmt.setString(2, athleteID);
            pstmt.setString(3, exerciseID);
            pstmt.setString(4, assignedDate);
            pstmt.setString(5, scheduledDate);
            pstmt.setString(6, completedDate);
            pstmt.setInt(7, setsAssigned);
            pstmt.setInt(8, repsAssigned);
            pstmt.setFloat(9, weightAssigned);
            pstmt.setString(10, notes);
            pstmt.setString(11, status);
            pstmt.executeUpdate();
            System.out.println("Exercise Record Added");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int updateExerciseRecord() {
        try {
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "Tyrone", "Easymoneysniper062123!!");

            System.out.println("Connection to DB Successful");
            String sql = "UPDATE exercise_record SET athleteID=?, exerciseID=?, assignedDate=?, scheduledDate=?, completedDate=?, sets_assigned=?, reps_assigned=?, weight_assigned=?, notes=?, status=? WHERE assignmentID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, athleteID);
            pstmt.setString(2, exerciseID);
            pstmt.setString(3, assignedDate);
            pstmt.setString(4, scheduledDate);
            pstmt.setString(5, completedDate);
            pstmt.setInt(6, setsAssigned);
            pstmt.setInt(7, repsAssigned);
            pstmt.setFloat(8, weightAssigned);
            pstmt.setString(9, notes);
            pstmt.setString(10, status);
            pstmt.setString(11, assignmentID);
            pstmt.executeUpdate();
            System.out.println("Exercise Record Updated");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int deleteExerciseRecord() {
        try {
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "Tyrone", "Easymoneysniper062123!!");

            System.out.println("Connection to DB Successful");
            String sql = "DELETE FROM exercise_record WHERE assignmentID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, assignmentID);
            pstmt.executeUpdate();
            System.out.println("Exercise Record Deleted");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
    public int getExerciseRecord() {
        int recordCount = 0;
        try {
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "Tyrone", "Easymoneysniper062123!!");


            System.out.println("Connection to DB Successful");
            String sql = "SELECT * FROM exercise_record WHERE assignmentID=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, assignmentID);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                recordCount++;
                athleteID = rs.getString("athleteID");
                exerciseID = rs.getString("exerciseID");
                assignedDate = rs.getString("assignedDate");
                scheduledDate = rs.getString("scheduledDate");
                completedDate = rs.getString("completedDate");
                setsAssigned = rs.getInt("sets_assigned");
                repsAssigned = rs.getInt("reps_assigned");
                weightAssigned = rs.getFloat("weight_assigned");
                notes = rs.getString("notes");
                status = rs.getString("status");
                System.out.println("Exercise Record Retrieved");
            }
            pstmt.close();
            conn.close();
            return recordCount;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
    
}
