package viewers;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import DataReadWrite.StaffListWriter;
import controllers.AdministratorController;
import datastorage.DataStorage;
import datastorage.Password;
import datastorage.StaffRecords;
import hospitalmanagementsystem.HospitalManagementSystem;
import entities.Staff;
import entities.Administrator;
import entities.Doctor;
import entities.Pharmacist;

public class AdministratorView implements ViewInterface{
	private AdministratorController adminControl;
	private Scanner inputScanner;
	
	public AdministratorView(AdministratorController administratorControl, Scanner inputScanner) {
		this.adminControl = administratorControl;
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
				manageHospitalStaff();
				break;
			case 2:
				//viewAppointmentDetails();
				break;
			case 3: 
				
				break;
			case 4: 
				//approveReplenishmentRequests();
				break;
		}
		
		if(userChoice == 5) {
			adminControl.updatedb();
			return false;
		}
		return true;
	}
	
	private void manageHospitalStaff() {
		System.out.println("------ Manage Hospital Staff ------");
		System.out.println("1. View Staff");
		System.out.println("2. Add Staff");
		System.out.println("3. Remove Staff");
		
		int userChoice = inputScanner.nextInt();
		
		switch(userChoice) {
			case 1:
				viewStaff();
				break;
				
			case 2:
				addStaff();
				break;
			
			case 3:
				removeStaff();
				break;
		}
	}

	private void removeStaff() {
		// TODO Auto-generated method stub
		System.out.println("------ Remove Staff ------");
		System.out.println("Enter Staff ID");
		String staffIDToRemove = inputScanner.next();
		adminControl.removeStaffByID(staffIDToRemove);
	}

	private void addStaff() {
		System.out.println("------ Add Staff ------");
		//Continue Loop till Valid Staff ID
		boolean validStaffID = false;
		String userID = "";
		String role = "";
		Staff staffToAdd;
		while(!validStaffID) {
			System.out.println("Enter New Staff ID");
			//atchar[0] check inital letter to match role implementation
			
			userID = inputScanner.next();
			char prerole=userID.charAt(0);
			
			switch(prerole) {
			case 'D':
				role="Doctor";
				validStaffID = true;
				break;
			case 'P':
				role="Pharmacist";
				validStaffID = true;
				break;
			case 'A':
				role="Administrator";
				validStaffID = true;
				break;
			default:
				System.out.println("Please input an appropriate UserID");
				break;
			}
		}
		staffToAdd = adminControl.getStaffByID(userID);
		if(staffToAdd != null) {
			System.out.println("UserID already existed");
			return;
		}
		String defaultPassword = "";
		try {
			defaultPassword = Password.hashPassword("password");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		inputScanner.nextLine(); // Clear Buffer
		System.out.println("Enter Name");
		String userName = inputScanner.nextLine();
		System.out.println("Enter Gender");
		String gender = inputScanner.nextLine();
		System.out.println("Enter Age");
		int age = inputScanner.nextInt();
		
		if(role.equals("Doctor")) {
			staffToAdd = new Doctor(userID, userName, defaultPassword, true, role, gender, age);
		}
		else if(role.equals("Pharmacist")) {
			staffToAdd = new Pharmacist(userID, userName, defaultPassword, true, role, gender, age);
		}
		else if(role.equals("Administrator")) {
			staffToAdd = new Administrator(userID, userName, defaultPassword, true, role, gender, age);
		}
		if(staffToAdd == null) {
			System.out.println("Error Adding User...");
		}
		else adminControl.addStaff(staffToAdd);
		
		
	}

	private void viewStaff() {
		System.out.println("------ List of Hospital Staff ------");
		StaffRecords staffRecords = adminControl.getStaffRecords();
		staffRecords.viewStaff();
	}
	

}
