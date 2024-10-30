package viewers;

import java.util.Scanner;

import controllers.PatientController;

public class PatientView implements ViewInterface{
	private PatientController patientControl;
	private Scanner inputScanner;
	
	public PatientView(PatientController patientControl, Scanner inputScanner) {
		this.patientControl = patientControl;
		this.inputScanner = inputScanner;
	}

	public boolean displayMenu() {
		System.out.println("------ Patient Menu ------");
		System.out.println("1. View Medical Record");
		System.out.println("2. Update Personal Information");
		System.out.println("3. View Available Appointment Slots");
		System.out.println("4. Schedule an Appointment");
		System.out.println("5. Reschedule an Appointment");
		System.out.println("6. Cancel an Appointment");
		System.out.println("7. View Scheduled Appointments");
		System.out.println("8. View Past Appointment Outcome Records");
		System.out.println("9. Logout ");
		System.out.println("What Do You Want To Do?: ");
		
		int userChoice = inputScanner.nextInt();
		if(userChoice == 1) viewMedicalRecord();
		else if(userChoice == 9) return false;
		return true;
		
	}
	
	public void viewMedicalRecord() {
		System.out.println("User ID: " + patientControl.getUserID());
	}
}
