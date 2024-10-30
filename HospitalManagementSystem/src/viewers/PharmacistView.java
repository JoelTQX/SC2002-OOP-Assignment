package viewers;

import java.util.Scanner;

import controllers.PharmacistController;

public class PharmacistView implements ViewInterface{
	private PharmacistController pharmacistControl;
	private Scanner inputScanner;
	
	public PharmacistView(PharmacistController pharmacistControl, Scanner inputScanner) {
		this.pharmacistControl = pharmacistControl;
		this.inputScanner = inputScanner;
	}

	@Override
	public boolean displayMenu() {
		System.out.println("------ Pharmacist Menu ------");
		System.out.println("1. View Appointment Outcome Records");
		System.out.println("2. Update Prescription Status");
		System.out.println("3. View Medication Inventory");
		System.out.println("4. Logout");
		
		int userChoice = inputScanner.nextInt();
		switch(userChoice) {
			case 1: 
				viewAppointmentOutcomeRecord();
				break;
			case 2:
				updatePrescriptionStatus();
				break;
			case 3:
				viewMedicationInventory();
				break;
			case 4:
				return false;
			default:
				System.out.println("Invalid Option... Please Try Again");
				break;
		}
		return true; // Continue Looping
	}
	
	private void viewAppointmentOutcomeRecord() {
		// TODO Auto-generated method stub
		
	}
	
	private void updatePrescriptionStatus() {
		// TODO Auto-generated method stub
		
	}
	
	private void viewMedicationInventory() {
		pharmacistControl.getInventory().viewInventory();
	}
}
