package G203DBAPP;

import java.io.*;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.*;

public class athlete_management_menu {

    public athlete_management_menu() {
    }

    // Utility method to validate the date format (YYYY-MM-DD)
    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true; // date is valid
        } catch (ParseException e) {
            return false; // invalid date
        }
    }

    // Utility method to validate gender input (M or F)
    public static boolean isValidGender(String gender) {
        return gender.equalsIgnoreCase("M") || gender.equalsIgnoreCase("F");
    }

    // Utility method to check if an input string is a valid integer
    public static boolean isValidInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to check if the athlete ID already exists in the database
    public static boolean isAthleteIDExists(String athleteID) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb?useTimezone=true&serverTimezone=UTC&user=root&password=your_password")) {
            String query = "SELECT COUNT(*) FROM athlete WHERE athleteID = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, athleteID);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt(1) > 0; // Returns true if the athleteID exists
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error checking athlete ID: " + e.getMessage());
        }
        return false; // Returns false if not found
    }

    public int menu() {
        int menuselection = 0;
        Scanner console = new Scanner(System.in);

        System.out.println("  ");
        System.out.println("  ");
        System.out.println("=======================================================");
        System.out.println("    Athlete Management Menu                           ");
        System.out.println("-------------------------------------------------------");
        System.out.println("[1] Create a new Athlete Record");
        System.out.println("[2] Update an Athlete Record");
        System.out.println("[3] Delete an Athlete Record");
        System.out.println("[4] View an Athlete Record and Current Team");
        System.out.println("[5] View Athlete's Performance History");
        System.out.println("[0] Exit Athlete Management");
        System.out.println("=======================================================");

        System.out.println("Enter Selected Function: ");
        menuselection = Integer.parseInt(console.nextLine());

        if (menuselection == 1) {
            // Adding a new Athlete Record
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete information");
            System.out.println("Athlete ID           : "); 
            String athleteID = console.nextLine();

            // Check if the athleteID already exists
            if (isAthleteIDExists(athleteID)) {
                System.out.println("Error: Athlete ID already exists. Please choose a different Athlete ID.");
                return menuselection; // Exit the method and return to the menu
            }

            a.athleteID = athleteID; // Only assign if ID doesn't exist
            System.out.println("First Name           : "); a.firstName = console.nextLine();
            System.out.println("Last Name            : "); a.lastName = console.nextLine();
            System.out.println("Middle Initial       : "); a.middleInitial = console.nextLine();
            
            // Validate Birthday (YYYY-MM-DD)
            String birthday;
            while (true) {
                System.out.println("Birthday (YYYY-MM-DD) : ");
                birthday = console.nextLine();
                if (isValidDate(birthday)) {
                    a.birthday = birthday;
                    break;
                } else {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            // Validate Gender (M or F)
            String gender;
            while (true) {
                System.out.println("Gender (M/F)          : ");
                gender = console.nextLine();
                if (isValidGender(gender)) {
                    a.gender = gender;
                    break;
                } else {
                    System.out.println("Invalid input. Gender must be 'M' or 'F'.");
                }
            }

            System.out.println("Team ID              : "); a.teamID = console.nextLine(); // Directly getting team ID
            System.out.println("Role in the Team     : "); a.role = console.nextLine();

            a.add_athlete();

        } else if (menuselection == 2) {
            // Updating an Athlete Record
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete ID to update: ");
            a.athleteID = console.nextLine();

            if (a.view_athlete(a.athleteID) == 0) {  // Pass athleteID to view_athlete method
                System.out.println("That athlete does not exist in the records.");
            } else {
                System.out.println("Current Athlete information:");
                System.out.println("--------------------------------------------------");
                System.out.println("Athlete ID           : " + a.athleteID);
                System.out.println("First Name           : " + a.firstName);
                System.out.println("Last Name            : " + a.lastName);
                System.out.println("Middle Initial       : " + a.middleInitial);
                System.out.println("Birthday             : " + a.birthday);
                System.out.println("Gender               : " + a.gender);
                System.out.println("Team ID              : " + a.teamID); // Show teamID here
                System.out.println("Role in Team         : " + a.role);

                System.out.println("Which field do you want to update?");
                System.out.println("[1] First Name");
                System.out.println("[2] Last Name");
                System.out.println("[3] Middle Initial");
                System.out.println("[4] Birthday");
                System.out.println("[5] Gender");
                System.out.println("[6] Team ID");
                System.out.println("[7] Role in Team");
                System.out.println("[0] Cancel");

                int updateChoice = Integer.parseInt(console.nextLine());

                switch (updateChoice) {
                    case 1:
                        System.out.println("Enter new First Name: ");
                        a.firstName = console.nextLine();
                        break;
                    case 2:
                        System.out.println("Enter new Last Name: ");
                        a.lastName = console.nextLine();
                        break;
                    case 3:
                        System.out.println("Enter new Middle Initial: ");
                        a.middleInitial = console.nextLine();
                        break;
                    case 4:
                        // Validate Birthday
                        String updatedBirthday;
                        while (true) {
                            System.out.println("Enter new Birthday (YYYY-MM-DD): ");
                            updatedBirthday = console.nextLine();
                            if (isValidDate(updatedBirthday)) {
                                a.birthday = updatedBirthday;
                                break;
                            } else {
                                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                            }
                        }
                        break;
                    case 5:
                        // Validate Gender
                        String updatedGender;
                        while (true) {
                            System.out.println("Enter new Gender (M/F): ");
                            updatedGender = console.nextLine();
                            if (isValidGender(updatedGender)) {
                                a.gender = updatedGender;
                                break;
                            } else {
                                System.out.println("Invalid input. Gender must be 'M' or 'F'.");
                            }
                        }
                        break;
                    case 6:
                        System.out.println("Enter new Team ID: ");
                        a.teamID = console.nextLine(); // Update team ID
                        break;
                    case 7:
                        System.out.println("Enter new Role in Team: ");
                        a.role = console.nextLine();
                        break;
                    case 0:
                        System.out.println("Update canceled.");
                        return menuselection;
                    default:
                        System.out.println("Invalid option.");
                        return menuselection;
                }

                // After updating the desired field(s), update the athlete record
                a.update_athlete();
            }

        } else if (menuselection == 3) {
            // Deleting an Athlete Record
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete ID to delete: ");
            a.athleteID = console.nextLine();

            a.delete_athlete();

        } else if (menuselection == 4) {
            // View an Athlete Record and Current Team
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete ID to view: ");
            a.athleteID = console.nextLine();

            a.view_athlete(a.athleteID); // Pass athleteID to view_athlete method

        } else if (menuselection == 5) {
            // View Athlete's Performance History
            athlete_peformance_report p = new athlete_peformance_report();

            System.out.println("Enter athlete ID to view performance history: ");
            String athleteID = console.nextLine().trim();

            // Check if the athlete ID exists in the database
            if (!isAthleteIDExists(athleteID)) { 
                System.out.println("Error: Athlete ID does not exist. Please enter a valid Athlete ID.");
                return menuselection; // Return to the menu
            }

            System.out.println("Enter Role: ");
            String role = console.nextLine().trim();

            String date;
            while (true) {
                System.out.println("Enter Date (YYYY-MM-DD): ");
                date = console.nextLine().trim();
                if (isValidDate(date)) {
                    break;
                } else {
                    System.out.println("Invalid date format. Please use YYYY-MM-DD.");
                }
            }

            // View performance report
            p.view_performance(athleteID, role, date); // Ensure method accepts the role and date parameters
        }

        return menuselection;
    }

    public static void main(String[] args) {
        // Create an instance of the menu and run it
        athlete_management_menu menu = new athlete_management_menu();
        int choice;

        do {
            choice = menu.menu(); // Display menu and get user choice
        } while (choice != 0); // Exit the program when the user selects 0 (Exit)
        
        System.out.println("Exiting Athlete Management Program.");
    }
}
