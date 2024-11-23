package G203DBAPP;

import java.util.Scanner;

public class coaches_practice_management_menu {

    // Method to handle valid choice input (for menu selection)
    private static int getValidChoice(Scanner scanner) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine()); // Changed nextInt() to nextLine() for consistency
                if (choice >= 0 && choice <= 4) {
                    return choice;
                } else {
                    System.out.println("Invalid choice! Please enter a number between 0 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    // Method for adding a practice session
    private static void addPracticeSession(Scanner scanner, coaches_practice_management management) {
        System.out.println("Enter Reserve ID: ");
        management.reserveID = scanner.nextLine();  // Updated to reserveID
        System.out.println("Enter Venue ID: ");
        management.venueID = scanner.nextLine();    // Venue ID
        System.out.println("Enter Practice Date (YYYY-MM-DD): ");
        management.practiceDate = getValidDate(scanner);  // Valid date input
        System.out.println("Enter Start Time (HH:mm): ");
        management.startTime = getValidTime(scanner);  // Valid time input
        System.out.println("Enter End Time (HH:mm): ");
        management.endTime = getValidTime(scanner);    // Valid time input
        System.out.println("Enter Coach ID: ");
        management.coachID = scanner.nextLine();    // Coach ID

        // Ensure that all required fields are set before checking availability
        if (!management.isVenueAvailable()) {
            System.out.println("Venue is already reserved at this time.");
            return;  // Exit the method if the venue is unavailable
        }

        int result = management.addPractice();
        if (result == 0) {
            System.out.println("Error adding practice session.");
        }
    }

    // Method for viewing a practice session
    private static void viewPracticeSession(Scanner scanner, coaches_practice_management management) {
        System.out.println("Enter Reserve ID to view: ");
        management.reserveID = scanner.nextLine();
        management.viewPractice();
    }

    // Method for updating a practice session
    private static void updatePracticeSession(Scanner scanner, coaches_practice_management management) {
        System.out.println("Enter Reserve ID to update: ");
        management.reserveID = scanner.nextLine();
        management.updatePracticeInteractive(scanner);
    }

    // Method for deleting a practice session
    private static void deletePracticeSession(Scanner scanner, coaches_practice_management management) {
        System.out.println("Enter Reserve ID to delete: ");
        management.reserveID = scanner.nextLine();
        management.deletePractice();
    }

    // Method to validate date input
    private static String getValidDate(Scanner scanner) {
        while (true) {
            String dateInput = scanner.nextLine();
            if (dateInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return dateInput;
            } else {
                System.out.println("Invalid date format! Please enter date in YYYY-MM-DD format.");
            }
        }
    }

    // Method to validate time input (in HH:mm format)
    private static String getValidTime(Scanner scanner) {
        while (true) {
            String timeInput = scanner.nextLine();
            if (timeInput.matches("\\d{2}:\\d{2}")) {
                return timeInput;
            } else {
                System.out.println("Invalid time format! Please enter time in HH:mm format.");
            }
        }
    }

    // Main method to drive the menu options
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        coaches_practice_management management = new coaches_practice_management();

        // Menu loop
        while (true) {
            System.out.println("----- Menu -----");
            System.out.println("1. Add Practice");
            System.out.println("2. View Practice");
            System.out.println("3. Update Practice");
            System.out.println("4. Delete Practice");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = getValidChoice(scanner);  // Get valid user choice
            
            switch (choice) {
                case 1:
                    addPracticeSession(scanner, management);
                    break;
                case 2:
                    viewPracticeSession(scanner, management);
                    break;
                case 3:
                    updatePracticeSession(scanner, management);
                    break;
                case 4:
                    deletePracticeSession(scanner, management);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
