package G203DBAPP;
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
        System.out.println("[1] Create a new Athlete Record                      ");
        System.out.println("[2] Update an Athlete Record                         ");
        System.out.println("[3] Delete an Athlete Record                         ");
        System.out.println("[4] View an Athlete Record and Current Team          ");
        System.out.println("[5] View Performance History of an Athlete           ");
        System.out.println("[0] Exit Athlete Management                          ");
        System.out.println("=======================================================");

        System.out.println("Enter Selected Function: ");
        menuselection = Integer.parseInt(console.nextLine());

        if (menuselection == 1) {
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete profile");
            System.out.println("-------------------------------------------------------------------");
            System.out.println("Athlete ID         : ");
            a.athleteId = console.nextLine();
            System.out.println("First Name         : ");
            a.firstName = console.nextLine();
            System.out.println("Last Name          : ");
            a.lastName = console.nextLine();
            System.out.println("Age                : ");
            a.age = Integer.parseInt(console.nextLine());
            System.out.println("Gender             : ");
            a.gender = console.nextLine().charAt(0);

            a.add_athlete();

        } else if (menuselection == 2) {
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete profile");
            System.out.println("Athlete ID         : ");
            a.athleteId = console.nextLine();

            if (a.view_athlete() == 0) {
                System.out.println("Athlete does not exist in the records");
            } else {
                System.out.println("Current Athlete profile");
                System.out.println("-------------------------------------------------------------------");
                System.out.println("Athlete ID         : " + a.athleteId);
                System.out.println("First Name         : " + a.firstName);
                System.out.println("Last Name          : " + a.lastName);
                System.out.println("Age                : " + a.age);
                System.out.println("Gender             : " + a.gender);

                System.out.println("\nEnter updated athlete profile");
                System.out.println("-------------------------------------------------------------------");
                System.out.println("First Name         : ");
                a.firstName = console.nextLine();
                System.out.println("Last Name          : ");
                a.lastName = console.nextLine();
                System.out.println("Age                : ");
                a.age = Integer.parseInt(console.nextLine());
                System.out.println("Gender             : ");
                a.gender = console.nextLine().charAt(0);

                a.update_athlete();
            }
        } else if (menuselection == 3) {
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete profile");
            System.out.println("Athlete ID         : ");
            a.athleteId = console.nextLine();

            a.delete_athlete();

        } else if (menuselection == 4) {
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete profile");
            System.out.println("Athlete ID         : ");
            a.athleteId = console.nextLine();

            if (a.view_athlete() == 0) {
                System.out.println("Athlete does not exist in the records");
            } else {
                System.out.println("Athlete Record and Team Retrieved");
            }

        } else if (menuselection == 5) {
            athlete_management a = new athlete_management();

            System.out.println("Enter athlete profile");
            System.out.println("Athlete ID         : ");
            a.athleteId = console.nextLine();

            if (a.track_performance_history() == 0) {
                System.out.println("No performance history found for the athlete");
            }
        }

        return menuselection;
    }

    public static void main(String args[]) {

        athlete_management_menu amm = new athlete_management_menu();
        while (amm.menu() != 0) {
        }
    }
}
