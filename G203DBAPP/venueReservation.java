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
	public String name;
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
		String name = "";
		
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
	public int viewReservation() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM venuereservation WHERE reserveID=?");
            pstmt.setString(1, reserveID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                reserveID = rs.getString("reserveID");
                date = rs.getString("date");
                venueID = rs.getString("venueID");
                startTime = rs.getInt("startTime");
                endTime = rs.getInt("endTime");
                coachID = rs.getString("coachID");
                Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT venueName FROM venues WHERE venueID=?");
                pstmt2.setString(1, venueID);
                ResultSet rs2 = pstmt.executeQuery();
                venueName = rs2.getString("venueName");
                pstmt2.close();
                conn2.close();
                System.out.println("Team Information:");
                System.out.println("reserveID: " + reserveID);
                System.out.println("date: " + date );
                System.out.println("venue: " + venueName);
                System.out.println("startTime: " + startTime);
                System.out.println("endTime: " + endTime);
                System.out.println("coachID: "+ coachID);
            }
            pstmt.close();
            conn.close();
            return 1;
		}catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public int createEquipmentBorrow() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO borrowrecord (borrowID, date, resourceID, startTime, endTime, coachID) VALUES (?, ?, ?, ?, ?, ?)");
	        pstmt.setString(1, borrowID);
            pstmt.setString(2, date);
            pstmt.setString(3, resourceID);
            pstmt.setInt(4, startTime);
            pstmt.setInt(5, endTime);
            pstmt.setString(6, coachID);
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
			PreparedStatement pstmt = conn.prepareStatement("UPDATE borrowrecord SET date=?, resourceID=?, startTime=?,endTime=?,coachID=? WHERE borrowID=?");
            
            pstmt.setString(1, date);
            pstmt.setString(2, resourceID);
            pstmt.setInt(3, startTime);
            pstmt.setInt(4, endTime);
            pstmt.setString(5, coachID);
            pstmt.setString(6, borrowID);
            pstmt.executeUpdate();
            System.out.println("Equipment Borrow record was updated");
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
	public int viewEquipmentBorrow() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM borrowrecord WHERE borrowID=?");
            pstmt.setString(1, borrowID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                borrowID = rs.getString("borrowID");
                resourceID	 = rs.getString("resourceID");
                date = rs.getString("date");
                startTime = rs.getInt("startTime");
                endTime = rs.getInt("endTime");
                coachID = rs.getString("coachID");
                Connection conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
                PreparedStatement pstmt2 = conn.prepareStatement("SELECT name FROM resources WHERE resourceID=?");
                pstmt2.setString(1, resourceID);
                ResultSet rs2 = pstmt.executeQuery();
                name = rs2.getString("name");
                pstmt2.close();
                conn2.close();
                System.out.println("Team Information:");
                System.out.println("borrowID: " + borrowID);
                System.out.println("name: " + name);
                System.out.println("date: "+ date);
                System.out.println("startTime: " + startTime);
                System.out.println("endTime: " + endTime);
                System.out.println("coachID: " + coachID);
            }
            pstmt.close();
            conn.close();
            return 1;
		}catch (Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
	}
	public boolean checkreserveIdExists(String reserveID) {
        try {Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM venuereservation WHERE reserveID = ?");
            stmt.setString(1, reserveID);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Returns true if count > 0
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return false;
    }
	public boolean checkborrowIdExists(String borrowID) {
        try {Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbathletes?useTimezone=true&serverTimezone=UTC&user=root&password=1123_Jeru");
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM borrowrecord WHERE borrowID = ?");
            stmt.setString(1, borrowID);
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
