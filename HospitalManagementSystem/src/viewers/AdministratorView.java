package viewers;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import controllers.AdministratorController;
import datareadwrite.StaffListWriter;
import datastorage.DataStorage;
import datastorage.Password;
import datastorage.StaffRecords;
import hospitalmanagementsystem.HospitalManagementSystem;
import entities.Staff;
import entities.Administrator;
import entities.Doctor;
import entities.Pharmacist;
import entities.Replenishment;

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
				inventoryManagement();
				break;
			case 4: 
				approveReplenishmentRequests();
				break;
		}
		
		if(userChoice == 5) {
			adminControl.updatedb();
			return false;
		}
		return true;
	}

	private void approveReplenishmentRequests() {
		// TODO Auto-generated method stub
		List<Replenishment> pendingReplenishments = adminControl.getPendingRequests(); 
		int replenishChoice = 0;
		
		//Check if there is a request
		if(pendingReplenishments.isEmpty()) {
			System.out.println("No Pending Replenishment Requests... Returning to Menu");
			return;
		}
		
		//Print all pending requests
		System.out.println("---- Pending Replenishment Requests ----");
		for(Replenishment replenishment : pendingReplenishments) {
			System.out.print(pendingReplenishments.indexOf(replenishment)+1);
			System.out.print(". " + replenishment.getMedicineName());
			System.out.println(" | Quantity: " + replenishment.getQuantity());
		}
		//Exit Option @ Last Option
		System.out.println(pendingReplenishments.size()+1 + ". Exit");
		
		System.out.print("Select Replenishment To Approve or Exit: ");
		while(true) {
			replenishChoice = inputScanner.nextInt();
			if(replenishChoice == pendingReplenishments.size()+1) break;
			else if(replenishChoice > pendingReplenishments.size() || replenishChoice < 1) {
				System.out.println("Invalid Option... Try Again");
			}
			else {
				adminControl.approveReplenishment(pendingReplenishments.get(replenishChoice-1));
				return;
			}
		}
		
	}

	private void inventoryManagement() {
		// TODO Auto-generated method stub
		int userChoice;
		do{
			System.out.println("------ Inventory Management -------");
			System.out.println("1. View Inventory");
			System.out.println("2. Manage Inventory");
			System.out.println("3. Return To Menu");
			System.out.print("Enter Option: ");
			userChoice = inputScanner.nextInt();
			switch(userChoice) {
				case 1:
					adminControl.getInventory().viewInventory();
					break;
				case 2:
					manageInventory();
					break;
				case 3:
					break;
				default:
					System.out.println("Invalid Option... Re-enter Choice...");
					break;
			}
		}while(userChoice != 3);
	}
	
	private void manageInventory() {
		// TODO Auto-generated method stub
		int userChoice;
		
		System.out.println("1. Adding stock levels");
		System.out.println("2. Removing stock levels");
		System.out.println("3. Updating Low stock alert");
		System.out.println("4. Return To Inventory Management");
		System.out.print("Enter Option: ");
		userChoice = inputScanner.nextInt();
		switch(userChoice) {
			case 1:
				addStock();
				break;
			case 2:
				removeStock();
				break;
			case 3:
				updateLowStockAlert();
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid Option... Re-enter Choice...");
				break;
		}
	}

	private void addStock() {
	    System.out.print("Enter Medicine Name to Add Stock: ");
	    inputScanner.nextLine(); // Clear the buffer
	    String medicineName = inputScanner.nextLine();
	    
	    System.out.print("Enter Quantity to Add: ");
	    int quantityToAdd = inputScanner.nextInt();
	    
	    boolean success = adminControl.getInventory().addStock(medicineName, quantityToAdd);
	    if (success) {
	        System.out.println("Stock added successfully.");
	    } else {
	        System.out.println("Error adding stock. Please check if the medicine name is correct.");
	    }
	}
	
	private void removeStock() {
	    System.out.print("Enter Medicine Name to Remove Stock: ");
	    inputScanner.nextLine(); // Clear the buffer
	    String medicineName = inputScanner.nextLine();
	    
	    System.out.print("Enter Quantity to Remove: ");
	    int quantityToRemove = inputScanner.nextInt();
	    
	    boolean success = adminControl.getInventory().removeStock(medicineName, quantityToRemove);
	    if (success) {
	        System.out.println("Stock removed successfully.");
	    } else {
	        System.out.println("Error removing stock. Please check if the medicine name and quantity are correct.");
	    }
	}
	
	private void updateLowStockAlert() {
	    System.out.print("Enter Medicine Name to Set Low Stock Alert: ");
	    inputScanner.nextLine(); // Clear the buffer
	    String medicineName = inputScanner.nextLine();
	    
	    System.out.print("Enter New Low Stock Threshold: ");
	    int newThreshold = inputScanner.nextInt();
	    
	    boolean success = adminControl.getInventory().updateLowStockAlert(medicineName, newThreshold);
	    if (success) {
	        System.out.println("Low stock alert updated successfully.");
	    } else {
	        System.out.println("Error updating low stock alert. Please check if the medicine name is correct.");
	    }
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
