package G203DBAPP;

import java.io.*;
import java.util.Scanner;

public class athlete_management_menu {

    public athlete_management_menu() {
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
            System.out.println("Athlete ID           : "); a.athleteID = console.nextLine();
            System.out.println("First Name           : "); a.firstName = console.nextLine();
            System.out.println("Last Name            : "); a.lastName = console.nextLine();
            System.out.println("Middle Initial       : "); a.middleInitial = console.nextLine();
            System.out.println("Birthday             : "); a.birthday = console.nextLine();
            System.out.println("Gender               : "); a.gender = console.nextLine();
            System.out.println("Team Name            : "); a.teamID = console.nextLine(); // Getting team name instead of ID
            System.out.println("Role in the Team     : "); a.role = console.nextLine();

            a.add_athlete();

        } else if (menuselection == 2) {
            // Updating an Athlete Record
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete ID to update: ");
            a.athleteID = console.nextLine();

            if (a.view_athlete() == 0) {
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
                System.out.println("Team ID              : " + a.teamID);
                System.out.println("Role in Team         : " + a.role);

                System.out.println("Which field do you want to update?");
                System.out.println("[1] First Name");
                System.out.println("[2] Last Name");
                System.out.println("[3] Middle Initial");
                System.out.println("[4] Birthday");
                System.out.println("[5] Gender");
                System.out.println("[6] Team Name");
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
                        System.out.println("Enter new Birthday: ");
                        a.birthday = console.nextLine();
                        break;
                    case 5:
                        System.out.println("Enter new Gender: ");
                        a.gender = console.nextLine();
                        break;
                    case 6:
                        System.out.println("Enter new Team Name: ");
                        a.teamID = console.nextLine(); // Update team name
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

            a.view_athlete(); // Shows athlete details and their team

        } else if (menuselection == 5) {
            // View Athlete's Performance History
        	athlete_peformance_report p = new athlete_peformance_report(); // Corrected class name

            System.out.println("Enter athlete ID to view performance history: ");
            String athleteID = console.nextLine();
            System.out.println("Enter Year: ");
            int year = Integer.parseInt(console.nextLine());
            System.out.println("Enter Month: ");
            int month = Integer.parseInt(console.nextLine());

            p.view_performance(athleteID, "", month, year); // Pass athleteID and other filters as needed
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
