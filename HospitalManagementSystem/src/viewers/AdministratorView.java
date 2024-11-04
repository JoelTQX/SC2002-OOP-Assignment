package viewers;
import java.util.Scanner;

import DataReadWrite.StaffListWriter;
import controllers.AdministratorController;

public class AdministratorView implements ViewInterface{
	private AdministratorController adminControl;
	private Scanner inputScanner;
	
	public AdministratorView(AdministratorController administratorControl, Scanner inputScanner) {
		this.adminControl = adminControl;
		this.inputScanner = inputScanner;
	}
	
	public boolean displayMenu() {
		System.out.println("------ Administrator Menu ------");
		System.out.println("1. View and Manage Hospital Staff");
		System.out.println("2. View Appointments details");
		System.out.println("3. View and Manage Medication Inventory");
		System.out.println("4. Approve Replenishment Requests");
		System.out.println("5. Logout ");
		System.out.println("What Do You Want To Do?: ");
		
		int userChoice = inputScanner.nextInt();
		
		switch(userChoice){
			case 1: 
				
				break;
			case 2:
				
				break;
			case 3: 
				
				break;
		}
		
		if(userChoice == 9) return false;
		return true;
	}
	

}
