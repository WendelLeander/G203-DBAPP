package G203DBAPP;
import java.util.Scanner;

public class athlete_management_menu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        athlete_management am = new athlete_management();

        while (true) {
            System.out.println("\n------ Athlete Management Menu ------");
            System.out.println("1. Add New Athlete");
            System.out.println("2. Update Athlete Details");
            System.out.println("3. Delete Athlete");
            System.out.println("4. View Athlete Details");
            System.out.println("5. Track Athlete's Performance History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Athlete ID: ");
                    am.athleteId = scanner.nextLine();
                    System.out.println("Enter First Name: ");
                    am.firstName = scanner.nextLine();
                    System.out.println("Enter Last Name: ");
                    am.lastName = scanner.nextLine();
                    System.out.println("Enter Age: ");
                    am.age = scanner.nextInt();
                    System.out.println("Enter Gender (M/F): ");
                    am.gender = scanner.next().charAt(0);
                    scanner.nextLine();  
                    am.add_athlete();
                    break;

                case 2:
                    System.out.println("Enter Athlete ID to update: ");
                    am.athleteId = scanner.nextLine();
                    System.out.println("Enter new First Name: ");
                    am.firstName = scanner.nextLine();
                    System.out.println("Enter new Last Name: ");
                    am.lastName = scanner.nextLine();
                    System.out.println("Enter new Age: ");
                    am.age = scanner.nextInt();
                    System.out.println("Enter new Gender (M/F): ");
                    am.gender = scanner.next().charAt(0);
                    scanner.nextLine();  // Consume newline
                    am.update_athlete();
                    break;

                case 3:
                    System.out.println("Enter Athlete ID to delete: ");
                    am.athleteId = scanner.nextLine();
                    am.delete_athlete();
                    break;

                case 4:
                    System.out.println("Enter Athlete ID to view: ");
                    am.athleteId = scanner.nextLine();
                    am.view_athlete();
                    break;

                case 5:
                    System.out.println("Enter Athlete ID to track performance: ");
                    am.athleteId = scanner.nextLine();
                    am.track_performance_history();
                    break;

                case 6:
                    System.out.println("Exiting program...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

