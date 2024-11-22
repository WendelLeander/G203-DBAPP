package G203DBAPPS;

import java.util.Scanner;

public class team_menu {

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

            team am = new team();

            switch (menuSelection) {
                case 1:
                    // Create a new team Record
                    System.out.print("Enter Athlete ID: ");
                    am.teamId = console.nextLine();
                    //if (am.checkTeamIdExists())
                    System.out.print("Enter team Name: ");
                    am.teamName = console.nextLine();
                    System.out.print("Enter team sport: ");
                    am.sport = console.nextLine();
                    if (am.addteam() == 1) {
                        System.out.println("Athlete added successfully!");
                    } else {
                        System.out.println("Failed to add athlete.");
                    }
                    break;

                case 2:
                    // Update a team Record
                    System.out.print("Enter Athlete ID to update: ");
                    am.teamId = console.nextLine();
                    
                    // Ask user for updates
                    System.out.print("Update Team Name (current: " + am.teamName + ")? (Leave blank to skip): ");
                    String teamName = console.nextLine();
                    if (!teamName.isEmpty()) {
                        am.teamName = teamName;
                    }

                    System.out.print("Update Team sport (current: " + am.sport + ")? (Leave blank to skip): ");
                    String sport = console.nextLine();
                    if (!sport.isEmpty()) {
                        am.sport = sport;
                    }
                    System.out.print("Update Team sport (current: " + am.sport + ")? (Leave blank to skip): ");
                    int gamesWon = console.nextInt();
                    if (!sport.isEmpty()) {
                        am.gamesWon = gamesWon;
                    }
                    System.out.print("Update Team sport (current: " + am.sport + ")? (Leave blank to skip): ");
                    int gamesLost = console.nextInt();
                    if (!sport.isEmpty()) {
                        am.gamesLost = gamesLost;
                    }

                    

                    if (am.update_team() == 1) {
                        System.out.println("Team updated successfully!");
                    } else {
                        System.out.println("Failed to update athlete.");
                    }
                    break;

                case 3:
                    // Delete an Athlete Record
                    System.out.print("Enter Team ID to delete: ");
                    am.teamId = console.nextLine();
                    if (am.delete_team() == 1) {
                        System.out.println("Athlete deleted successfully!");
                    } else {
                        System.out.println("Failed to delete athlete.");
                    }
                    break;

                case 4:
                    // View an Athlete Record and Current Team
                    System.out.print("Enter Team ID to view: ");
                    am.teamId = console.nextLine();
                    am.view_Team();
                    break;

                case 5:
                    // View Athlete's Performance History
                    System.out.print("Enter Team ID to view performance history: ");
                    am.teamId = console.nextLine();
                    // Call the method and check if it returns a result
                    int result = am.teamPerformanceHistory();
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