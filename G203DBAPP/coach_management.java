package G203DBAPP;
import java.sql.*;

public class coach_management {

	public String coachId;
	public String firstName;
	public String lastName;
	public String coachingRole;
	public String hireDate;
	public int age;
	public char gender;
	
	public coach_management() {
		coachId			= "";
		firstName		= "";
		lastName		= "";
		coachingRole	= "";
		hireDate		= "";
		age				= 0;
		gender			= ' ';
	}
	
	public int add_coach() {
		
		try {
			
			Connection conn;
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO coaches VALUES (?,?,?,?,?,?,?)");
			pstmt.setString(1, coachId);
			pstmt.setString(2, firstName);
			pstmt.setString(3, lastName);
			pstmt.setString(4, coachingRole);
			pstmt.setString(5, hireDate);
			pstmt.setInt   (6, age);
			pstmt.setString (7, String.valueOf(gender));
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE coaches SET coachFirstName=?, coachLastName=?, coachingRole=?, hireDate=?, age=?, gender=? WHERE coachID=?");
			pstmt.setString(7, coachId);
			pstmt.setString(1, firstName);
			pstmt.setString(2, lastName);
			pstmt.setString(3, coachingRole);
			pstmt.setString(4, hireDate);
			pstmt.setInt   (5, age);
			pstmt.setString (6, String.valueOf(gender));
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM coaches WHERE coachID=?");
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
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=p@ssword");
			System.out.println("Connection to DB Successful");
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM coaches WHERE coachID=?");
			pstmt.setString(1, coachId);
			System.out.println("SQL Statement Prepared");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				recordcount++;
				firstName 	       = rs.getString("coachFirstName");
				lastName 	       = rs.getString("coachLastName");
				age 	   		   = rs.getInt("age");
				gender	  		   = rs.getString("gender").charAt(0);
				coachingRole       = rs.getString("coachingRole");
				hireDate	       = rs.getString("hireDate");

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
	