package G203DBAPP;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class coach_job_history_management {

	
	public String coachId;
	public String startDate;
	public String endDate;
	public String teamID;
	public String role;
	
	public coach_job_history_management(String coachID) {
		this.coachId    = coachID;
		startDate		= "";
		endDate		    = "9999/01/01";
		teamID	        = "";
		role			= "";
	}

	public int generate_history() {
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO coach_job_history VALUES (?,?,?,?,?)");
			pstmt.setString(1, coachId);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			pstmt.setString(4, teamID);
			pstmt.setString(5, role);
			pstmt.executeUpdate();
			System.out.println("Job History Record was created");
			pstmt.close();
			conn.close();
			return 1;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	

}
