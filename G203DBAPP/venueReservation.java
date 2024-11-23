package G203DBAPPS;
import java.sql.*;

public class venueReservation {
	public String reserveID;
	public String date;
	public String venueID;
	public int startTime;
	public int endTime;
	public String coachID;
	public String venueName;
	public String location;
	public String borrowID;
	public String resourceID;
	public String BorrowDate;
	public String ReturnDate;
	public venueReservation() {
		String reserveID = "";
		String date = "";
		String venueID = "";
		int startTime = 0;
		int endTime = 0;
		String coachID = "";
		String venueName= "";
		String location = "";
		String resourceID = "";
		String BorrowDate = "";
		String ReturnDate = "";
		
	}
	
	public int createReservation() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
	        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO venuereservation (reserveID, date, venueID, startTime, endTime, coachID) VALUES (?, ?, ?, ?, ?, ?)");
	        pstmt.setString(1, reserveID);
            pstmt.setString(2, date);
            pstmt.setString(3, venueID);
            pstmt.setInt(4, startTime);
            pstmt.setInt(5, endTime);
            pstmt.setString(6, coachID);
            
            pstmt.executeUpdate();
            System.out.println("Reservation record was created");
            
            pstmt.close();
            conn.close();
            return 1;
            
		}catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public int updateReservation() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE venuereservation SET date=?, venueID=?, startTime=?,endTime=?,coachID=? WHERE reserveID=?");
            
            pstmt.setString(1, date);
            pstmt.setString(2, venueID);
            pstmt.setInt(3, startTime);
            pstmt.setInt(4, endTime);
            pstmt.setString(5, coachID);
            pstmt.setString(6, reserveID);
            
            pstmt.executeUpdate();
            System.out.println("Reservation record was updated");
            pstmt.close();
            conn.close();
            return 1;
		}catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public int deleteReservation() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM venuereservation WHERE reserveID=?");
            pstmt.setString(1, reserveID);
            pstmt.executeUpdate();
            System.out.println("Reservation record was deleted");
            pstmt.close();
            conn.close();
            return 1;
			
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public int createEquipmentBorrow() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
	        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO venuereservation (borrowID, reserveID, BorrowDate, ReturnDate, coachID) VALUES (?, ?, ?, ?, ?)");
	        pstmt.setString(1, borrowID);
            pstmt.setString(2, resourceID);
            pstmt.setString(3, BorrowDate);
            pstmt.setString(4, ReturnDate);
            pstmt.setString(5, coachID);
            pstmt.executeUpdate();
            System.out.println("Equipment Borrow record was Created");
            pstmt.close();
            conn.close();
			
			return 1;   
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public int updateEquipmentBorrow() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
			PreparedStatement pstmt = conn.prepareStatement("UPDATE venuereservation  resourceID=?, borrowDate=?, ReturnDate=?, coachID=? WHERE reserveID=?");
            
			
            pstmt.setString(1, resourceID);
            pstmt.setString(2, BorrowDate);
            pstmt.setString(3, ReturnDate);
            pstmt.setString(4, coachID);
            pstmt.setString(5, borrowID);
            pstmt.executeUpdate();
            System.out.println("Equipment Borrow record was Created");
            pstmt.close();
            conn.close();
            return 1;
            
			
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public int deleteEquipmentBorrow() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM borrowrecord WHERE borrowID=?");
            pstmt.setString(1, borrowID);
            pstmt.executeUpdate();
            System.out.println("Equipment borrow record was deleted");
            pstmt.close();
            conn.close();
            return 1;
			
		}
		catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
		
	}

}
