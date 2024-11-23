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
		System.out.println("[4] View a Coach Record							       ");
		System.out.println("[5] View the current team of a Coach				   ");
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
			System.out.println ("Middle Initial      : ");  c.middleInitial      = console.nextLine();
			System.out.println ("Gender              : ");  c.gender	    	 = console.nextLine().charAt(0);
			System.out.println ("Birthday            : ");  c.birthday 		     = console.nextLine();
			

			c.add_coach();
			coach_job_history_menu ch = new coach_job_history_menu(c.coachId);
			ch.menu();
			
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
				System.out.println ("Middle Initial      : " + c.middleInitial);
				System.out.println ("Gender              : " + c.gender);
				System.out.println ("Birthday            : " + c.birthday);

	
				System.out.println ("\nEnter updated coach profile");
				System.out.println ("-------------------------------------------------------------------");
				System.out.println ("First Name          : ");  c.firstName 		 = console.nextLine();
				System.out.println ("Last Name           : ");  c.lastName  	     = console.nextLine();
				System.out.println ("Middle Initial      : ");  c.middleInitial      = console.nextLine();
				System.out.println ("Gender              : ");  c.gender	    	 = console.nextLine().charAt(0);
				System.out.println ("Birthday            : ");  c.birthday 		     = console.nextLine();	
			
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
				System.out.println ("Middle Initial      : " + c.middleInitial);
				System.out.println ("Gender              : " + c.gender);
				System.out.println ("Birthday            : " + c.birthday);
			}
			
		} else if (menuselection==5) {
			coach_management c = new coach_management();
			
			System.out.println ("Enter coach ID");
			System.out.println ("Coach ID        : ");  c.coachId  		 = console.nextLine();
			c.get_team();
			if (c.view_current_team()==0) {
				System.out.println("Coach does not have a current team");
			} else {
				System.out.println ("Current Team");
				System.out.println ("-------------------------------------------------------------------");
				System.out.println ("Team Name            : " + c.teamName);
				System.out.println ("Sport                : " + c.sport);
			}
		}
			
		return menuselection;
	}	
	
	public static void main (String args[]) {
		
		coach_management_menu pmm = new coach_management_menu();
		while (pmm.menu() != 0) {}
	}
}