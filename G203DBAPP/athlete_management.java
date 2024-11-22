package G203DBAPP;
import java.sql.*;

public class athlete_management {

    public String athleteId;
    public String firstName;
    public String lastName;
    public String birthday;
    public char gender;
    public String teamName;

    // Constructor
    public athlete_management() {
        athleteId = "";
        firstName = "";
        lastName = "";
        birthday = "";
        gender = ' ';
        teamName = "";
    }

    // Add new athlete to the database
    public int add_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO athletes (athleteID, firstName, lastName, birthday, gender) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, athleteId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, birthday); // Storing birthday
            pstmt.setString(5, String.valueOf(gender));
            pstmt.executeUpdate();
            System.out.println("Athlete record was created");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Update existing athlete in the database
    public int update_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE athletes SET firstName=?, lastName=?, birthday=?, gender=? WHERE athleteID=?");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, birthday); // Storing updated birthday
            pstmt.setString(4, String.valueOf(gender));
            pstmt.setString(5, athleteId);
            pstmt.executeUpdate();
            System.out.println("Athlete record was updated");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // Delete athlete record from the database
    public int delete_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM athletes WHERE athleteID=?");
            pstmt.setString(1, athleteId);
            pstmt.executeUpdate();
            System.out.println("Athlete record was deleted");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    // View athlete record along with their current team
    public int view_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM athletes WHERE athleteID=?");
            pstmt.setString(1, athleteId);
            ResultSet rs = pstmt.executeQuery();

            // Fetching athlete details
            while (rs.next()) {
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                birthday = rs.getString("birthday"); 
                gender = rs.getString("gender").charAt(0);

                System.out.println("Athlete Information:");
                System.out.println("ID: " + athleteId);
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Birthday: " + birthday); 
                System.out.println("Gender: " + gender);

                // Fetching the current team of the athlete
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT teamName FROM athlete_teams WHERE athleteID=?");
                pstmt2.setString(1, athleteId);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    teamName = rs2.getString("teamName");
                    System.out.println("Current Team: " + teamName);
                } else {
                    System.out.println("No current team assigned.");
                }
                rs2.close();
            }

            pstmt.close();
            conn.close();
            return 1;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
