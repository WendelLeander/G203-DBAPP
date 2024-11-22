// 11-22-2024: 4:32 PM
package G203DBAPP;
import java.sql.*;

public class athlete_management {

    public String athleteId;
    public String firstName;
    public String lastName;
    public String birthday;
    public char gender;
    public String teamName;

    public athlete_management() {
        athleteId = "";
        firstName = "";
        lastName = "";
        birthday = "";
        gender = ' ';
        teamName = "";
    }

    public int add_athlete() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO athletes (athleteID, firstName, lastName, birthday, gender) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, athleteId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, birthday);
            pstmt.setString(5, String.valueOf(gender));
            pstmt.executeUpdate();
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
            PreparedStatement pstmt = conn.prepareStatement("UPDATE athletes SET firstName=?, lastName=?, birthday=?, gender=? WHERE athleteID=?");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, birthday);
            pstmt.setString(4, String.valueOf(gender));
            pstmt.setString(5, athleteId);
            pstmt.executeUpdate();
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
                birthday = rs.getString("birthday");
                gender = rs.getString("gender").charAt(0);

                PreparedStatement pstmt2 = conn.prepareStatement("SELECT teamName FROM athlete_teams WHERE athleteID=?");
                pstmt2.setString(1, athleteId);
                ResultSet rs2 = pstmt2.executeQuery();
                if (rs2.next()) {
                    teamName = rs2.getString("teamName");
                } else {
                    teamName = "No current team assigned.";
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

    public int view_performance_history() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM performance WHERE athleteID=?");
            pstmt.setString(1, athleteId);
            ResultSet rs = pstmt.executeQuery();

            boolean hasPerformance = false;
            while (rs.next()) {
                hasPerformance = true;
                String date = rs.getString("date");
                String performance = rs.getString("performance");
                System.out.println("Date: " + date);
                System.out.println("Performance: " + performance);
            }

            if (!hasPerformance) {
                System.out.println("No performance records found.");
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
