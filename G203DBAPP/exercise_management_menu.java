package G203DBAPP;

import java.util.Scanner;

public class exercise_management_menu {

    public exercise_management_menu() {
    }

    public int menu(Scanner console) {
        int menuselection = 0;

      
        System.out.println("=======================================================");
        System.out.println("    Exercise Management Menu                           ");
        System.out.println("-------------------------------------------------------");
        System.out.println("[1] Add a New Exercise Record                         ");
        System.out.println("[2] Update an Exercise Record                         ");
        System.out.println("[3] Delete an Exercise Record                         ");
        System.out.println("[4] View an Exercise Record                           ");
        System.out.println("[0] Exit Exercise Management                          ");
        System.out.println("=======================================================");

        System.out.print("Enter Selected Function: ");
        menuselection = Integer.parseInt(console.nextLine());

        if (menuselection == 1) {
            
            exercise_management er = new exercise_management();
            System.out.println("Enter Exercise Record Details:");
            System.out.print("Assignment ID       : ");
            er.assignmentID = console.nextLine();
            System.out.print("Athlete ID          : ");
            er.athleteID = console.nextLine();
            System.out.print("Exercise ID         : ");
            er.exerciseID = console.nextLine();
            System.out.print("Assigned Date       : ");
            er.assignedDate = console.nextLine();
            System.out.print("Scheduled Date      : ");
            er.scheduledDate = console.nextLine();
            System.out.print("Completed Date      : ");
            er.completedDate = console.nextLine();
            System.out.print("Sets Assigned       : ");
            er.setsAssigned = Integer.parseInt(console.nextLine());
            System.out.print("Reps Assigned       : ");
            er.repsAssigned = Integer.parseInt(console.nextLine());
            System.out.print("Weight Assigned     : ");
            er.weightAssigned = Float.parseFloat(console.nextLine());
            System.out.print("Notes               : ");
            er.notes = console.nextLine();
            System.out.print("Status (ongoing/completed/cancelled): ");
            er.status = console.nextLine();

            int result = er.addExerciseRecord();
            if (result == 1) {
                System.out.println("Exercise Record Added Successfully.");
            } else {
                System.out.println("Failed to Add Exercise Record.");
            }
        } else if (menuselection == 2) {
            
            exercise_management er = new exercise_management();
            System.out.print("Enter Assignment ID of the record to update: ");
            er.assignmentID = console.nextLine();

            if (er.getExerciseRecord() == 0) {
                System.out.println("No such record found.");
            } else {
                System.out.println("Update Exercise Record Details:");
                System.out.print("Athlete ID          : ");
                er.athleteID = console.nextLine();
                System.out.print("Exercise ID         : ");
                er.exerciseID = console.nextLine();
                System.out.print("Assigned Date       : ");
                er.assignedDate = console.nextLine();
                System.out.print("Scheduled Date      : ");
                er.scheduledDate = console.nextLine();
                System.out.print("Completed Date      : ");
                er.completedDate = console.nextLine();
                System.out.print("Sets Assigned       : ");
                er.setsAssigned = Integer.parseInt(console.nextLine());
                System.out.print("Reps Assigned       : ");
                er.repsAssigned = Integer.parseInt(console.nextLine());
                System.out.print("Weight Assigned     : ");
                er.weightAssigned = Float.parseFloat(console.nextLine());
                System.out.print("Notes               : ");
                er.notes = console.nextLine();
                System.out.print("Status (ongoing/completed/cancelled): ");
                er.status = console.nextLine();

                int result = er.updateExerciseRecord();
                if (result == 1) {
                    System.out.println("Exercise Record Updated Successfully.");
                } else {
                    System.out.println("Failed to Update Exercise Record.");
                }
            }
        } else if (menuselection == 3) {
            
            exercise_management er = new exercise_management();
            System.out.print("Enter Assignment ID to delete: ");
            er.assignmentID = console.nextLine();

            int result = er.deleteExerciseRecord();
            if (result == 1) {
                System.out.println("Exercise Record Deleted Successfully.");
            } else {
                System.out.println("Failed to Delete Exercise Record.");
            }
        } else if (menuselection == 4) {
            
            exercise_management er = new exercise_management();
            System.out.print("Enter Assignment ID to view: ");
            er.assignmentID = console.nextLine();

            if (er.getExerciseRecord() > 0) {
                System.out.println("Exercise Record Details:");
                System.out.println("Assignment ID       : " + er.assignmentID);
                System.out.println("Athlete ID          : " + er.athleteID);
                System.out.println("Exercise ID         : " + er.exerciseID);
                System.out.println("Assigned Date       : " + er.assignedDate);
                System.out.println("Scheduled Date      : " + er.scheduledDate);
                System.out.println("Completed Date      : " + er.completedDate);
                System.out.println("Sets Assigned       : " + er.setsAssigned);
                System.out.println("Reps Assigned       : " + er.repsAssigned);
                System.out.println("Weight Assigned     : " + er.weightAssigned);
                System.out.println("Notes               : " + er.notes);
                System.out.println("Status              : " + er.status);
            } else {
                System.out.println("No such record found.");
            }
        }

        return menuselection;
    }
}
