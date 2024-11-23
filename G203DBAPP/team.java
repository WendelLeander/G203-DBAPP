package G203DBAPPS;
import java.sql.*;

public class team {

    public String teamId;
    public String teamName;
    public String sport;
    public String getRoster = """
    SELECT CONCAT(a.firstname,a.lastname) AS fullname
    FROM athlete a
    JOIN teams t ON a.teamID = t.teamID
    WHERE t.teamID=?
    """;
    public String fullname;
    public int gamesWon;
    public int gamesLost;
    public String teamID1;
    public String teamID2;
    public String team1;
    public String team2;
    public String team1N;
    public String team2N;
    public int team1S;
    public int team2S;
    public team() {
        teamId = "";
        teamName = "";
        sport = "";
        fullname = "";
        gamesWon = 0;
        gamesLost = 0;
        team1 = "";
        team2 = "";
        team1N = "";
        team2N = "";
        team1S = 0;
        team2S = 0;
    }
    

    public int addteam() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO teams (teamID, teamName, sport) VALUES (?, ?, ?)");
            pstmt.setString(1, teamId);
            pstmt.setString(2, teamName);
            pstmt.setString(3, sport);
            pstmt.executeUpdate();
            System.out.println("Team record was created");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int update_team() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("UPDATE athletes SET teamName=?, sport=? WHERE teamID=?");
            
            pstmt.setString(1, teamName);
            pstmt.setString(2, sport);
            pstmt.setString(3, teamId);
            
            pstmt.executeUpdate();
            System.out.println("team record was updated");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int delete_team() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM teams WHERE teamID=?");
            pstmt.setString(1, teamId);
            pstmt.executeUpdate();
            System.out.println("Team record was deleted");
            pstmt.close();
            conn.close();
            return 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int view_Team() {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM teams WHERE teamID=?");
            pstmt.setString(1, teamId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                teamName = rs.getString("teamName");
                sport = rs.getString("sport");
                gamesWon = rs.getInt("gamesWon");
                gamesLost = rs.getInt("gamesLost");

                System.out.println("Team Information:");
                System.out.println("ID: " + teamId);
                System.out.println("Name: " + teamName );
                System.out.println("Sport: " + sport);
                System.out.println("Games Won: " + gamesWon);
                System.out.println("Games Lost: " + gamesLost);

                // Fetching current roster 
                PreparedStatement pstmt2 = conn.prepareStatement(getRoster);
                pstmt2.setString(1, teamId);
                ResultSet rs2 = pstmt2.executeQuery();
                int counter = 0;
                while (rs2.next()) {
                    fullname = rs2.getString("fullname");
                    System.out.println("Player #"+ counter + " : " + fullname);
                    counter++;
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
    public int teamPerformanceHistory() {
    	try {
	    	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
	        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Match_History WHERE teamID1=? OR teamID2=?");
	        pstmt.setString(1, teamId);
	        pstmt.setString(2, teamId);
	        ResultSet rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	        	
                team1 = rs.getString("teamID1");
                team2 = rs.getString("teamID2");
                team1S = rs.getInt("team1Score");
                team2S = rs.getInt("team2Score");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT teamName FROM teams WHERE teamID=?");
                pstmt2.setString(1, team1);
                ResultSet rs2 = pstmt2.executeQuery();
                team1N = rs2.getString("teamName");
                PreparedStatement pstmt3 = conn.prepareStatement("SELECT teamName FROM teams WHERE teamID=?");
                pstmt3.setString(1, team2);
                ResultSet rs3 = pstmt3.executeQuery();
                team2N = rs3.getString("teamName");
                System.out.println("Match Information:");
                System.out.println("Team1: " + team1N);
                System.out.println("Team2: " + team2N );
                System.out.println("Team1 Score: " + team1S);
                System.out.println("Team2 Score: " + team2S);
                pstmt.close();
                conn.close();
	        	}
	        return 1;
    		}
    	catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
        
    	
    

    
    }
    public boolean checkTeamIdExists(String teamId) {
        try {Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM teams WHERE teamID = ?");
            stmt.setString(1, teamId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count > 0
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return false;
    }
    
}