package viewers;

import java.util.Scanner;

import controllers.DoctorController;
import controllers.PatientController;
import datastorage.DataStorage;
import datastorage.PatientRecords;
import entities.Patient;

public class DoctorView implements ViewInterface{
	private DoctorController doctorControl;
	private Scanner inputScanner;
	
	public DoctorView(DoctorController doctorControl, Scanner inputScanner) {
		this.doctorControl = doctorControl;
		this.inputScanner = inputScanner;
	}

	@Override
	public boolean displayMenu() {
		System.out.println("Doctor Menu:");
        System.out.println("(1) View Patient Medical Records");
        System.out.println("(2) Update Patient Medical Records");
        System.out.println("(3) View Personal Schedule");
        System.out.println("(4) Set Availability for Appointments");
        System.out.println("(5) Accept or Decline Appointment Requests");
        System.out.println("(6) View Upcoming Appointments");
        System.out.println("(7) Record Appointment Outcome");
        System.out.println("(8) Logout");
        
        int userChoice = inputScanner.nextInt();
		
		switch(userChoice){
			case 1: 
				Scanner scanner= new Scanner(System.in);
				viewPatientRecords();
				//viewMedicalRecord();
				break;
			case 2:
				//updatePersonalInformation();
				break;
			case 3: 
				//viewAvailableSlots();
				break;
		}
		if(userChoice == 8) return false;
		else return true;
			//viewMedicalRecord();
		//else if(userChoice == 8) return false;
		//return true;
	}

	private void viewPatientRecords() {
		PatientRecords patientRecords = doctorControl.getPatientsRecords();
		for(Patient patient : patientRecords.getPatientList()) {
			System.out.println("Name: " + patient.getUserName());
			System.out.println("Blood Type: " + patient.getPatientBloodType());
			System.out.println("DOB: " + patient.getPatientDOB());
			System.out.println("Gender: " + patient.getUserGender());
		}
	}
        
        
        
        
        
        
        
        
	}


