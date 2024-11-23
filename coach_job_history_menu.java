package G203DBAPP;
import java.util.Scanner;

public class coach_job_history_menu {
	public String coachId;

public coach_job_history_menu(String coachID) {
	this.coachId = coachID;
}
	
	public void addJobHistory() {
		Scanner console   	  = new Scanner(System.in);
		coach_job_history_management ch = new coach_job_history_management(coachId);
		System.out.println("  ");
		System.out.println("  ");
		System.out.println("=======================================================");
		System.out.println("    Configure Coach Job History							   ");
		System.out.println("=======================================================");
		System.out.println ("Start Date          : ");  ch.startDate  		     = console.nextLine();
		System.out.println ("Team ID             : ");  ch.teamID		 		 = console.nextLine();
		System.out.println ("Role                : ");  ch.role 	             = console.nextLine();
		ch.generate_history();
	}	
}
