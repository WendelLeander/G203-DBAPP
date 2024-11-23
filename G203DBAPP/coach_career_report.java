package G203DBAPP;
import java.sql.*;
import java.util.Scanner;

public class coach_career_report {
	
	public String coachId;
	public int recordcount;
	
	public coach_career_report() {
	}

	public int generate_careerreport () {
		recordcount = 0;
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			System.out.println("Connection to DB Successful");
			PreparedStatement pstmt = conn.prepareStatement("SELECT 	 t.teamName, \r\n"
					                                      + "			 ch.role, \r\n"
					                                      + "			 ch.startDate, \r\n"
					                                      + "			 ch.endDate, \r\n"
					                                      + "			 TIMESTAMPDIFF(YEAR, ch.startDate, ch.endDate) as yearsofservice\r\n"
														  + "FROM		 coach_job_history ch 	JOIN teams t ON ch.teamId=t.teamId\r\n"
														  + "WHERE		 ch.coachId = ? \r\n");

			pstmt.setString(1, coachId);
			System.out.println("SQL Statement Prepared");
			ResultSet rs = pstmt.executeQuery();
			
			System.out.println("=======================================================");
			System.out.println("            COACH CAREER REPORT                		  ");
			System.out.println("=======================================================");
			
			while (rs.next()) {
				recordcount++;
				System.out.println(""
						+ "Team Name            : " +rs.getString("teamName") + 
						"\nRole                 : " + rs.getString("role") + 
						"\nDate Joined          : " + rs.getString("startDate") + 
						"\nDate Left            : " + rs.getString("endDate") + 
						"\nYears of Service     : " + rs.getInt("yearsofservice") + "\n\n");
			}
			System.out.println("=======================================================");
			System.out.println("                END OF REPORT                		  ");
			System.out.println("=======================================================");
			pstmt.close();
			conn.close();
			return recordcount;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public static void main (String args[]) {
		
		coach_career_report rp = new coach_career_report();
		Scanner console   	  = new Scanner(System.in);
		rp.coachId = console.nextLine();
		rp.generate_careerreport();
	}
}

