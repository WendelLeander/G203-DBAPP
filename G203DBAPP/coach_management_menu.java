package G203DBAPP;
import java.util.Scanner;

public class coach_management_menu {

public coach_management_menu() {
		
	}
	
	public int menu() {
		int 	menuselection = 0;
		Scanner console   	  = new Scanner(System.in);
		
		System.out.println("  ");
		System.out.println("  ");
		System.out.println("=======================================================");
		System.out.println("    Coach Management Menu							   ");
		System.out.println("-------------------------------------------------------");
		System.out.println("[1] Create a new Coach Record						   ");
		System.out.println("[2] Update a Coach Record							   ");
		System.out.println("[3] Delete a Coach Record							   ");
		System.out.println("[4] View a Coach Record							   ");
		System.out.println("[0] Exit Coach Management							   ");
		System.out.println("=======================================================");
		
		System.out.println("Enter Selected Function: ");
		menuselection = Integer.parseInt(console.nextLine());
		
		if (menuselection==1) {
			// Adding a new Records, ask the user for the values of the record fields
			coach_management c = new coach_management();
			
			System.out.println ("Enter coach profile");
			System.out.println ("-------------------------------------------------------------------");
			System.out.println ("Coach ID            : ");  c.coachId  		     = console.nextLine();
			System.out.println ("First Name          : ");  c.firstName 		 = console.nextLine();
			System.out.println ("Last Name           : ");  c.lastName  	     = console.nextLine();
			System.out.println ("Age                 : ");  c.age          		 = Integer.parseInt(console.nextLine());
			System.out.println ("Gender              : ");  c.gender	    	 = console.nextLine().charAt(0);
			System.out.println ("Coaching Role       : ");  c.coachingRole 		 = console.nextLine();
			System.out.println ("Hire Date           : ");  c.hireDate 			 = console.nextLine();

			c.add_coach();
		
		} else if (menuselection==2) {			
			
			coach_management c = new coach_management();
			
			System.out.println ("Enter coach profile");
			System.out.println ("Coach ID        : ");  c.coachId  		 = console.nextLine();

			if (c.get_coach()==0) {
				System.out.println("Coach does not exists on the records");
			} else {
				System.out.println ("Current Coach profile");
				System.out.println ("-------------------------------------------------------------------");
				System.out.println ("Coach ID            : " + c.coachId);
				System.out.println ("First Name          : " + c.firstName);
				System.out.println ("Last Name           : " + c.lastName);
				System.out.println ("Age                 : " + c.age);
				System.out.println ("Gender              : " + c.gender);
				System.out.println ("Coaching Role       : " + c.coachingRole);
				System.out.println ("Hire Date           : " + c.hireDate);
	
				System.out.println ("\nEnter updated coach profile");
				System.out.println ("-------------------------------------------------------------------");
				System.out.println ("First Name          : ");  c.firstName 		 = console.nextLine();
				System.out.println ("Last Name           : ");  c.lastName  	     = console.nextLine();
				System.out.println ("Age                 : ");  c.age          		 = Integer.parseInt(console.nextLine());
				System.out.println ("Gender              : ");  c.gender	    	 = console.nextLine().charAt(0);
				System.out.println ("Coaching Role       : ");  c.coachingRole 		 = console.nextLine();
				System.out.println ("Hire Date           : ");  c.hireDate 			 = console.nextLine();		
			
				c.update_coach();
			}
		} else if (menuselection==3) {
			coach_management c = new coach_management();
			
			System.out.println ("Enter coach profile");
			System.out.println ("Coach ID        : ");  c.coachId  		 = console.nextLine();		
			
			c.delete_coach();
			
		} else if (menuselection==4) {
			coach_management c = new coach_management();
			
			System.out.println ("Enter coach profile");
			System.out.println ("Coach ID        : ");  c.coachId  		 = console.nextLine();

			if (c.get_coach()==0) {
				System.out.println("Coach does not exists on the records");
			} else {
				System.out.println ("Current Coach profile");
				System.out.println ("-------------------------------------------------------------------");
				System.out.println ("Coach ID            : " + c.coachId);
				System.out.println ("First Name          : " + c.firstName);
				System.out.println ("Last Name           : " + c.lastName);
				System.out.println ("Age                 : " + c.age);
				System.out.println ("Gender              : " + c.gender);
				System.out.println ("Coaching Role       : " + c.coachingRole);
				System.out.println ("Hire Date           : " + c.hireDate);
			}
		}
		
		return menuselection;
	}	
	
	public static void main (String args[]) {
		
		coach_management_menu pmm = new coach_management_menu();
		while (pmm.menu() != 0) {}
	}
}