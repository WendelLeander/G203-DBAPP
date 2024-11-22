package G203DBAPP;

import java.util.Scanner;

public class athlete_management_menu {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int menuSelection = 0;

        while (menuSelection != 0) {
            System.out.println("============================================");
            System.out.println("Athlete Management Menu");
            System.out.println("============================================");
            System.out.println("[1] Create a new Athlete Record");
            System.out.println("[2] Update an Athlete Record");
            System.out.println("[3] Delete an Athlete Record");
            System.out.println("[4] View an Athlete Record and Current Team");
            System.out.println("[5] View Athlete's Performance History");
            System.out.println("[0] Exit Athlete Management");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");
            menuSelection = Integer.parseInt(console.nextLine());

            athlete_management am = new athlete_management();

            switch (menuSelection) {
                case 1:
                    // Create a new Athlete Record
                    System.out.print("Enter Athlete ID: ");
                    am.athleteID = console.nextLine();
                    System.out.print("Enter First Name: ");
                    am.firstName = console.nextLine();
                    System.out.print("Enter Last Name: ");
                    am.lastName = console.nextLine();
                    System.out.print("Enter Middle Initial: ");
                    am.middleInitial = console.nextLine();
                    System.out.print("Enter Birthday (YYYY-MM-DD): ");
                    am.birthday = console.nextLine();
                    System.out.print("Enter Gender (M/F): ");
                    am.gender = console.nextLine();
                    System.out.print("Enter Team ID: ");
                    am.teamID = console.nextLine();
                    System.out.print("Enter Role (Player/Captain/Assistant Captain): ");
                    am.role = console.nextLine();
                    if (am.add_athlete() == 1) {
                        System.out.println("Athlete added successfully!");
                    } else {
                        System.out.println("Failed to add athlete.");
                    }
                    break;

                case 2:
                    // Update an Athlete Record
                    System.out.print("Enter Athlete ID to update: ");
                    am.athleteID = console.nextLine();
                    
                    // Ask user for updates
                    System.out.print("Update First Name (current: " + am.firstName + ")? (Leave blank to skip): ");
                    String firstName = console.nextLine();
                    if (!firstName.isEmpty()) {
                        am.firstName = firstName;
                    }

                    System.out.print("Update Last Name (current: " + am.lastName + ")? (Leave blank to skip): ");
                    String lastName = console.nextLine();
                    if (!lastName.isEmpty()) {
                        am.lastName = lastName;
                    }

                    System.out.print("Update Middle Initial (current: " + am.middleInitial + ")? (Leave blank to skip): ");
                    String middleInitial = console.nextLine();
                    if (!middleInitial.isEmpty()) {
                        am.middleInitial = middleInitial;
                    }

                    System.out.print("Update Birthday (current: " + am.birthday + ")? (Leave blank to skip): ");
                    String birthday = console.nextLine();
                    if (!birthday.isEmpty()) {
                        am.birthday = birthday;
                    }

                    System.out.print("Update Gender (current: " + am.gender + ")? (Leave blank to skip): ");
                    String gender = console.nextLine();
                    if (!gender.isEmpty()) {
                        am.gender = gender;
                    }

                    System.out.print("Update Team ID (current: " + am.teamID + ")? (Leave blank to skip): ");
                    String teamID = console.nextLine();
                    if (!teamID.isEmpty()) {
                        am.teamID = teamID;
                    }

                    System.out.print("Update Role (current: " + am.role + ")? (Leave blank to skip): ");
                    String role = console.nextLine();
                    if (!role.isEmpty()) {
                        am.role = role;
                    }

                    if (am.update_athlete() == 1) {
                        System.out.println("Athlete updated successfully!");
                    } else {
                        System.out.println("Failed to update athlete.");
                    }
                    break;

                case 3:
                    // Delete an Athlete Record
                    System.out.print("Enter Athlete ID to delete: ");
                    am.athleteID = console.nextLine();
                    if (am.delete_athlete() == 1) {
                        System.out.println("Athlete deleted successfully!");
                    } else {
                        System.out.println("Failed to delete athlete.");
                    }
                    break;

                case 4:
                    // View an Athlete Record and Current Team
                    System.out.print("Enter Athlete ID to view: ");
                    am.athleteID = console.nextLine();
                    am.view_athlete();
                    break;

                case 5:
                    // View Athlete's Performance History
                    System.out.print("Enter Athlete ID to view performance history: ");
                    String athleteID = console.nextLine();
                    
                    System.out.print("Enter Athlete Role (Player/Captain/Assistant Captain): ");
                    String role2 = console.nextLine();
                    
                    System.out.print("Enter Month (1-12): ");
                    int month = Integer.parseInt(console.nextLine());
                    
                    System.out.print("Enter Year (YYYY): ");
                    int year = Integer.parseInt(console.nextLine());
                    
                    athlete_peformance_report report = new athlete_peformance_report();
                    // Call the method and check if it returns a result
                    int result = report.view_performance(athleteID, role2, month, year);
                    if (result == 1) {
                        System.out.println("Performance history viewed successfully!");
                    } else {
                        System.out.println("Failed to view performance history.");
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
