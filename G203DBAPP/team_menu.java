package G203DBAPPS;

import java.util.Scanner;

public class team_menu {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int menuSelection = 0;
        boolean checker;
        while (menuSelection != 0) {
            System.out.println("============================================");
            System.out.println("Team Management Menu");
            System.out.println("============================================");
            System.out.println("[1] Create a new Team Record");
            System.out.println("[2] Update an Team Record");
            System.out.println("[3] Delete an Team Record");
            System.out.println("[4] View an Team Record and Current Team");
            System.out.println("[5] View Team's Performance History");
            System.out.println("[0] Exit Team Management");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");
            menuSelection = Integer.parseInt(console.nextLine());

            team am = new team();

            switch (menuSelection) {
                case 1:
                    // Create a new team Record
                    System.out.print("Enter Team ID: ");
                    am.teamId = console.nextLine();
                    checker = am.checkTeamIdExists(am.teamId);
                    if (checker != true) {
                    System.out.print("Enter team Name: ");
                    am.teamName = console.nextLine();
                    System.out.print("Enter team sport: ");
                    am.sport = console.nextLine();
                    if (am.addteam() == 1) {
                        System.out.println("Team added successfully!");
                    } else {
                        System.out.println("Failed to add Team.");
                    }
                    } else {
                    	System.out.println("Team already exists");
                    }
                   
                    break;

                case 2:
                    // Update a team Record
                    System.out.print("Enter Team ID to update: ");
                    am.teamId = console.nextLine();
                    
                    if (am.checkTeamIdExists(am.teamId)) {
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
                    System.out.print("Update Team games won (current: " + am.gamesWon + ")? (Leave blank to skip): ");
                    int gamesWon = console.nextInt();
                    if (!(gamesWon == 0)) {
                        am.gamesWon = gamesWon;
                    }
                    System.out.print("Update Team games lost (current: " + am.gamesLost + ")? (Leave blank to skip): ");
                    int gamesLost = console.nextInt();
                    if (!(gamesLost == 0)) {
                        am.gamesLost = gamesLost;
                    }

                    }

                    if (am.update_team() == 1) {
                        System.out.println("Team updated successfully!");
                    } else {
                        System.out.println("Failed to update Team.");
                    }
                    break;

                case 3:
                    // Delete an Team Record
                    System.out.print("Enter Team ID to delete: ");
                    am.teamId = console.nextLine();
                    if (am.checkTeamIdExists(am.teamId)) {
                    if (am.delete_team() == 1) {
                        System.out.println("Team deleted successfully!");
                    } else {
                        System.out.println("Failed to delete Team.");
                    }
                    } else {
                    	System.out.println("Team ID does not exist");
                    }
                    break;

                case 4:
                    // View an Team Record and Current Team
                    System.out.print("Enter Team ID to view: ");
                    am.teamId = console.nextLine();
                    checker = am.checkTeamIdExists(am.teamId);
                    if (checker != true) {
                    am.view_Team();
                    }else {
                    	System.out.println("Team ID does not exist");
                    }
                    break;

                case 5:
                    // View Team's Performance History
                    System.out.print("Enter Team ID to view performance history: ");
                    am.teamId = console.nextLine();
                    checker = am.checkTeamIdExists(am.teamId);
                    if (checker != true) {
                    // Call the method and check if it returns a result
                    int result = am.teamPerformanceHistory();
                    if (result == 1) {
                        System.out.println("Performance history viewed successfully!");
                    } else {
                        System.out.println("Failed to view performance history.");
                    }
                    }else {
                    	System.out.println("Team ID does not exist");
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