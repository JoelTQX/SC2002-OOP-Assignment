package viewers;

import java.util.List;
import java.util.Scanner;

import controllers.PharmacistController;
import entities.Appointment;
import entities.Appointment.PrescribedMedication;
import entities.Medicine;
import entities.PrescribedMedicine;

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
		System.out.println("4. Submit Replenishment Request");
		System.out.println("5. Logout");
		
		int userChoice;
		//Error Handling
		try {
			userChoice = inputScanner.nextInt();
		}catch(Exception e) {
        	System.out.println("Invalid Option... Please Try Again...\n");
        	inputScanner.next(); //Clear Scanner Buffer
        	return true;
		}
		
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
				submitReplenishment();
				break;
			case 5:
				return false;
			default:
				System.out.println("Invalid Option... Please Try Again");
				break;
		}
		return true; // Continue Looping
	}

	  // Option 1: View past appointments with recorded outcomes
    private void viewAppointmentOutcomeRecord() {
    	List<Appointment> completedAppointments = pharmacistControl.getCompletedAppointments();
        int displayCount = 1;
        int userChoice = -1;
    	
        if (completedAppointments.isEmpty()) {
            System.out.println("No past appointments with recorded outcomes.");
            return;
        }
        System.out.println("------ Past Appointment Outcome Records ------");
        for (Appointment appointment : completedAppointments) {
            System.out.println(displayCount++ + ". " + appointment.getAppointmentID());
        }
        //Exit/Return after last available options
        System.out.println(displayCount + ". Return to Menu");
        
        
        do{
        	try{
        		userChoice = inputScanner.nextInt();
        	}catch(Exception e) {
        		System.out.println("Invalid Format... Please Input An Integer");
        		inputScanner.next(); //Clear Buffer;
        		continue;
        	}
        	if(userChoice > completedAppointments.size()) {
        		if(userChoice == completedAppointments.size()+1) break;
        		System.out.println("Invalid Option... Please Try Again...");
        		continue;
        	}
        	Appointment chosenAppointment = completedAppointments.get(userChoice-1);
        	viewAppointmentDetails(chosenAppointment);
        	return;
        }while(userChoice != completedAppointments.size());
        System.out.println("Returning to menu...");
    }

	 private void viewAppointmentDetails(Appointment chosenAppointment) {
		// TODO Auto-generated method stub
		System.out.println("----- Appoint Details -----");
		System.out.println("Appointment ID: " + chosenAppointment.getAppointmentID());
		System.out.println("Doctor ID: " + chosenAppointment.getDoctorId());
		System.out.println("Patient ID: " + chosenAppointment.getPatientId());
		System.out.println("Appointment Date:" + chosenAppointment.getAppointmentDate());
		System.out.println("Prescripted Medicines:");
		for(PrescribedMedication prescribedMedicine : chosenAppointment.getPrescribedMedications()) {
			System.out.print(prescribedMedicine.getMedicationName());
			System.out.print(" | Quantity: " + prescribedMedicine.getMedicineQuantity());
			System.out.println(" | Status: " + prescribedMedicine.getMedicineStatus());
		}
		System.out.println("Appointment Type:" + chosenAppointment.getAppointmentType());
		System.out.println("Consultion Note:" + chosenAppointment.getConsultationNotes());
		System.out.println("-------------------------");
		
	}

	// Option 2: Update the prescription status of a medicine in an appointment
	 private void updatePrescriptionStatus() {
		System.out.println("------ Update Prescription Status ------");
        System.out.print("Enter Appointment ID: ");
        String appointmentID = inputScanner.next();
        pharmacistControl.updatePrescriptionStatus(appointmentID);
    }

	private void submitReplenishment() {
		// TODO Auto-generated method stub
		List<Medicine> medicineRecords = pharmacistControl.getInventory().getMedicineRecords();
		int medicineChoice, medicineQuantity;
		
		//Print available medicines
		System.out.println("------- Medicine Replenishment -------");
		for(Medicine medicine : medicineRecords) {
			System.out.println( (medicineRecords.indexOf(medicine)+1) + ". " + medicine.getMedicineName());
		}
		//Last Option to Return to Menu
		System.out.println((medicineRecords.size()+1) +". Return to Menu");
		
		//Loop To Ask For Medicine Choice
		while(true) {
			//Get User Input
			System.out.print("Select Option: ");
			
			//Error in Input
			try {
				medicineChoice = inputScanner.nextInt();
			}catch(Exception e) {
				System.out.println("Invalid Input... Re-enter Choice...");
				inputScanner.next(); //Clear Scanner Buffer
				medicineChoice = -1;
			}
			
			if(medicineChoice == medicineRecords.size()+1) {
				System.out.println("Returning to menu...");
				return;
			}
			//Check if out of bound | Sanity Check
			else if(medicineChoice > medicineRecords.size() || medicineChoice < 1) {
				System.out.println("Invalid Option... Try Again...");
			}
			break;
		}
		//Print Cancel Condition
		System.out.println("To Cancel Replenishment Enter Values LESS OR EQUAL TO 0");
		System.out.print("Enter Quantity to Replenish: ");
		try {
			medicineQuantity = inputScanner.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid Input... Returning to Main Menu");
			inputScanner.next(); //Clear Scanner Buffer
			medicineQuantity = -1;
		}
		if(medicineQuantity <= 0) return;
		pharmacistControl.createReplenishmentRequest(medicineChoice-1, medicineQuantity);
		System.out.println("Replenishment Request Submitted... Returning to menu...");
	}
	
	private void viewMedicationInventory() {
		pharmacistControl.getInventory().viewInventory();
	}
}
