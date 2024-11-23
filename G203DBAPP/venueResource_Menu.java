package G203DBAPPS;

import java.util.Scanner;

public class venueResource_Menu {
	public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int menuSelection = 0;
        boolean checker;
        while (menuSelection != 0) {
            System.out.println("====================================================");
            System.out.println("Venue and Resource Reservation Menu");
            System.out.println("====================================================");
            System.out.println("[1] Create a new Venue Reservation Record");
            System.out.println("[2] Update a Venue Reservation Record");
            System.out.println("[3] Delete a Venue Reservation Record");
            System.out.println("[4] View a Venue Reservation Record");
            System.out.println("[5] Create a new Equipment Reservation Record");
            System.out.println("[6] Update an Equipment Reservation Record");
            System.out.println("[7] Delete an Equipment Reservation Record");
            System.out.println("[8] View an Equipment Reservation Record");
            System.out.println("[0] Exit Venue and Resource Reservation Management");
            System.out.println("====================================================");
            System.out.print("Enter your choice: ");
            menuSelection = Integer.parseInt(console.nextLine());

            venueReservation am = new venueReservation();

            switch (menuSelection) {
                case 1:
                    // Create a new Venue Reservation Record
                    System.out.print("Reservation  ID: ");
                    am.reserveID = console.nextLine();
                    checker = am.checkreserveIdExists(am.reserveID);
                    if (checker != true) {
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    am.date = console.nextLine();
                    System.out.print("Enter team Venue ID: ");
                    am.venueID = console.nextLine();
                    System.out.print("Enter Start Time: ");
                    am.startTime = console.nextInt();
                    System.out.print("Enter End Time: ");
                    am.endTime = console.nextInt();
                    System.out.print("Enter Coach ID: ");
                    am.coachID = console.nextLine();
                    if (am.createReservation() == 1) {
                        System.out.println("Reservation added successfully!");
                    } else {
                        System.out.println("Failed to add Team.");
                    }
                    } else {
                    	System.out.println("Reservation already exists");
                    }
                   
                    break;

                case 2:
                    // Update a team Record
                    System.out.print("Enter reserve ID to update: ");
                    am.reserveID = console.nextLine();
                    checker = am.checkreserveIdExists(am.reserveID);
                    if (checker == true) {
                    // Ask user for updates
                    	System.out.print("Update date of reservation (current: " + am.date + ")? (Leave blank to skip): ");
                    	String date = console.nextLine();
                    	if (!date.isEmpty()) {
                        am.date = date;
                    	}

                    	System.out.print("Update venueID (current: " + am.venueID + ")? (Leave blank to skip): ");
                    	String venueID = console.nextLine();
                    	if (!venueID.isEmpty()) {
                        am.venueID = venueID;
                    	}
	                    System.out.print("Update startTime (current: " + am.startTime + ")? (Leave blank to skip): ");
	                    int startTime = console.nextInt();
	                    if (!(startTime == 0)) {
	                        am.startTime = startTime;
	                    }
	                    System.out.print("Update endTime (current: " + am.endTime + ")? (Leave blank to skip): ");
	                    int endTime = console.nextInt();
	                    if (!(endTime == 0)) {
	                        am.endTime = endTime;
	                    }
	                    System.out.print("Update coachID (current: " + am.coachID + ")? (Leave blank to skip): ");
	                    String coachID = console.nextLine();
	                    if (!coachID.isEmpty()) {
	                        am.coachID = coachID;
	                    }

                   

	                    if (am.updateReservation() == 1) {
	                        System.out.println("Team updated successfully!");
	                    } else {
	                        System.out.println("Failed to update Team.");
	                    }
                    }
	                    
	                    else {
	                    	System.out.println("Reserve ID does not exist");
	                    }
                    break;

                case 3:
                    // Delete an Team Record
                    System.out.print("Enter Reservation ID to delete: ");
                    am.reserveID = console.nextLine();
                    checker = am.checkreserveIdExists(am.reserveID);
                    if (checker == true) {
	                    if (am.deleteReservation() == 1) {
	                        System.out.println("Reservation deleted successfully!");
	                    } else {
	                        System.out.println("Failed to delete Team.");
	                    }
                    }
                    else {
                    	System.out.println("Reserve ID does not exist");
                    }
                    break;

                case 4:
                    // View an Team Record and Current Team
                    System.out.print("Enter Team ID to view: ");
                    am.reserveID = console.nextLine();
                    checker = am.checkreserveIdExists(am.reserveID);
                    if (checker == true) {
                    am.viewReservation();
                    }else {
                    	System.out.println("Reserve ID does not exist");
                    }
                    break;

                case 5:
                	// Create a new Equipment Reservation Record
                    System.out.print("Reservation  ID: ");
                    am.borrowID = console.nextLine();
                    checker = am.checkreserveIdExists(am.borrowID);
                    if (checker != true) {
                    System.out.print("ResourceID: ");
                    am.date = console.nextLine();
                    System.out.print("Enter team Venue ID: ");
                    am.venueID = console.nextLine();
                    System.out.print("Enter Start Time: ");
                    am.startTime = console.nextInt();
                    System.out.print("Enter End Time: ");
                    am.endTime = console.nextInt();
                    System.out.print("Enter Coach ID: ");
                    am.coachID = console.nextLine();
                    if (am.createReservation() == 1) {
                        System.out.println("Reservation added successfully!");
                    } else {
                        System.out.println("Failed to add Team.");
                    }
                    } else {
                    	System.out.println("Reservation already exists");
                    }
                    break;

                case 0:
                    // Exit
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid selection! Try again.");
            }
        }
        console.close();
    }
}
