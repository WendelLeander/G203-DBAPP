package G203DBAPP;
import java.sql.*;

public class coach_management {

	public String coachId;
	public String firstName;
	public String lastName;
	public String middleInitial;
	public String birthday;
	public char gender;
	public String endDate;
	public String teamID;
	public String teamName;
	public String sport;
	


	
	public coach_management() {
		coachId			= "";
		firstName		= "";
		lastName		= "";
		middleInitial	= "";
		birthday		= "";
		gender			= ' ';
	}
	
	public int add_coach() {
		
		try {
			
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO coach VALUES (?,?,?,?,?,?)");
			pstmt.setString(1, coachId);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, middleInitial);
			pstmt.setString(5, birthday);
			pstmt.setString (6, String.valueOf(gender));
			System.out.println("SQL Statement Prepared");
			pstmt.executeUpdate();
			System.out.println("Record was created");
			pstmt.close();
			conn.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	
	public int update_coach() {
		
		try {
			
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE coach SET firstname=?, lastname=?, middleInitial=?, birthday=?, gender=? WHERE coachID=?");
			pstmt.setString(6, coachId);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, middleInitial);
			pstmt.setString(4, birthday);
			pstmt.setString (5, String.valueOf(gender));
			System.out.println("SQL Statement Prepared");
			pstmt.executeUpdate();
			System.out.println("Record was updated");
			pstmt.close();
			conn.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}


	public int delete_coach() {
		
		try {
			
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM coach WHERE coachID=?");
			pstmt.setString(1, coachId);
			System.out.println("SQL Statement Prepared");
			pstmt.executeUpdate();
			System.out.println("Record was deleted");
			pstmt.close();
			conn.close();
			return 1;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public int get_coach() {
		int recordcount = 0;
		try {
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			System.out.println("Connection to DB Successful");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM coach WHERE coachID=?");
			pstmt.setString(1, coachId);
			System.out.println("SQL Statement Prepared");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				recordcount++;
				firstName 	       = rs.getString("firstname");
				lastName 	       = rs.getString("lastname");
				middleInitial 	   = rs.getString("middleInitial");
				birthday           = rs.getString("birthday");
				gender	  		   = rs.getString("gender").charAt(0);
				
				

				System.out.println("Record was Retrieved");
			}
			pstmt.close();
			conn.close();
			return recordcount;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
public void get_team() {
		try {
			
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM coach_job_history WHERE coachID=? AND YEAR(endDate) = 9999");
			pstmt.setString(1, coachId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				teamID 	       = rs.getString("teamID");			
			}
			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
public int view_current_team() {
		int recordcount = 0;
		try {
			this.get_team();
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM teams WHERE teamID=?");
			pstmt.setString(1, teamID);
			System.out.println("SQL Statement Prepared");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				recordcount++;
				teamName 	       = rs.getString("teamName");
				sport 	      	   = rs.getString("sport");
				
				System.out.println("Record was Retrieved");
			}
			pstmt.close();
			conn.close();
			return recordcount;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
}
	