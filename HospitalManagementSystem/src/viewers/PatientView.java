package viewers;

import java.util.Scanner;

import DataReadWrite.PatientListWriter;
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
		
		switch(userChoice){
			case 1: 
				viewMedicalRecord();
				break;
			case 2:
				updatePersonalInformation();
				break;
			case 3: 
				viewAvailableSlots();
				break;
		}
		
		if(userChoice == 9) return false;
		return true;
	}
	




	private void viewMedicalRecord() {
		System.out.println("Patient ID: " + patientControl.getUserID());
		System.out.println("Patient Name: " + patientControl.getUserName());
		System.out.println("Patient Gender: " + patientControl.getUserGender());
		System.out.println("Patient Date Of Birth: " + patientControl.getUserDOB());
		System.out.println("Patient Email: " + patientControl.getUserContactInfo());
		System.out.println("Patient Blood Type: " + patientControl.getUserBloodType());
		
	}
	
	private void updatePersonalInformation() {
		System.out.println("------ Update Personal Information ------");
		System.out.println("1. Email Address");
		System.out.println("2. Contact Information");
		System.out.println("3. Exit");
		
		int userChoice = inputScanner.nextInt();
		
		switch(userChoice){
			case 1: 
				System.out.println("Enter New Email Address");
				Scanner input=new Scanner(System.in);
				String email = input.nextLine();
				patientControl.setPatientContactInfo(email);
				PatientListWriter writer = new PatientListWriter();
				writer.write(patientControl.getUserID(),6,email);
				System.out.println("Email Address Updated");
				break;
			case 2:
				System.out.println("Enter New Contact Information");
				break;
		}
		
	}
	
	private void viewAvailableSlots() {
		// TODO Auto-generated method stub
		
	}
}
