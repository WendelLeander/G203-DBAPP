package G203DBAPP;
import java.sql.*;

public class athlete_management {

    public String athleteId;
    public String firstName;
    public String lastName;
    public int age;
    public char gender;
    public String teamName;

    public athlete_management() {
        athleteId = "";
        firstName = "";
        lastName = "";
        age = 0;
        gender = ' ';
        teamName = "";
    }

    public int add_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO athletes (athleteID, firstName, lastName, age, gender) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, athleteId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setInt(4, age);
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

    public int update_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE athletes SET firstName=?, lastName=?, age=?, gender=? WHERE athleteID=?");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setInt(3, age);
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

    public int view_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM athletes WHERE athleteID=?");
            pstmt.setString(1, athleteId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                age = rs.getInt("age");
                gender = rs.getString("gender").charAt(0);

                System.out.println("Athlete Information:");
                System.out.println("ID: " + athleteId);
                System.out.println("Name: " + firstName + " " + lastName);
                System.out.println("Age: " + age);
                System.out.println("Gender: " + gender);

                // Fetching the current team of the athlete
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT teamName FROM athlete_teams WHERE athleteID=?");
                pstmt2.setString(1, athleteId);
                ResultSet rs2 = pstmt2.executeQuery();
                while (rs2.next()) {
                    teamName = rs2.getString("teamName");
                    System.out.println("Current Team: " + teamName);
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

    public int track_performance_history() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM performance_history WHERE athleteID=?");
            pstmt.setString(1, athleteId);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Performance History for Athlete ID: " + athleteId);

            while (rs.next()) {
                String performanceDate = rs.getString("performanceDate");
                String performanceMetric = rs.getString("performanceMetric");
                System.out.println("Date: " + performanceDate + " | Metric: " + performanceMetric);
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
