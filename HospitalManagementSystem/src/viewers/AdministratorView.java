package viewers;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Scanner;

import controllers.AdministratorController;
import datastorage.Password;
import datastorage.StaffRecords;
import entities.Staff;
import entities.Appointment.PrescribedMedication;
import entities.Administrator;
import entities.Appointment;
import entities.Doctor;
import entities.Pharmacist;
import entities.Replenishment;

/**
 * The AdministratorView class provides the user interface for the administrator
 * to interact with the hospital management system. It allows administrators to 
 * manage hospital staff, view appointments, manage medication inventory, and approve 
 * replenishment requests.
 * Implements the `ViewInterface`.
 */

public class AdministratorView implements ViewInterface{
	/** The administrator controller used to manage hospital operations. */
	private AdministratorController adminControl;
	
    /** The scanner used to read user input. */
	private Scanner inputScanner;
	
    /**
     * Constructor to initialize the AdministratorView with the given controller
     * and scanner.
     *
     * @param administratorControl The controller that handles administrator actions.
     * @param inputScanner The scanner used for reading user input.
     */
	public AdministratorView(AdministratorController administratorControl, Scanner inputScanner) {
		this.adminControl = administratorControl;
		this.inputScanner = inputScanner;
	}
	
    /**
     * Displays the administrator menu and processes user input to perform various actions.
     * 
     * @return true if the administrator wants to continue, false to log out and exit.
     */
	public boolean displayMenu() {
		System.out.println("------ Administrator Menu ------");
		System.out.println("1. View and Manage Hospital Staff");
		System.out.println("2. View Appointments details");
		System.out.println("3. View and Manage Medication Inventory");
		System.out.println("4. Approve Replenishment Requests");
		System.out.println("5. Logout ");
		System.out.println("What Do You Want To Do?: ");
		
		int userChoice;
		
		//Error Handling
		try {
			userChoice = inputScanner.nextInt();
		}catch(Exception e) {
			System.out.println("Invalid Option... Please Try Again...\n");
			inputScanner.next(); //Clear Scanner Buffer
			return true;
		}
		switch(userChoice){
			case 1: 
				manageHospitalStaff();
				break;
			case 2:
				viewAppointmentDetails();
				break;
			case 3: 
				inventoryManagement();
				break;
			case 4: 
				approveReplenishmentRequests();
				break;
			default:
				System.out.println("Invalid Option... Please Try Again...\n");
		}
		
		if(userChoice == 5) {
			adminControl.updatedb();
			return false;
		}
		return true;
	}

    /**
     * Displays the details of past appointments and allows the administrator to select one
     * for further inspection.
     */
	private void viewAppointmentDetails() {
	    List<Appointment> allAppointments = adminControl.getAppointmentRecords();
	    int displayCount = 1;
	    int userChoice = -1;

	    if (allAppointments.isEmpty()) {
	        System.out.println("No past appointments with recorded outcomes.");
	        return;
	    }
	    System.out.println("------ Past Appointment Outcome Records ------");
	    for (Appointment appointment : allAppointments) {
	        System.out.println(displayCount++ + ". " + appointment.getAppointmentID());
	    }
	    // Exit/Return after last available options
	    System.out.println(displayCount + ". Return to Menu");

	    do {
	        System.out.print("Enter Option: ");
	        try {
	            userChoice = inputScanner.nextInt();
	        } catch (Exception e) {
	            System.out.println("Invalid Format... Please Input An Integer");
	            inputScanner.next(); // Clear Buffer;
	        }
	        if (userChoice > allAppointments.size()) {
	            if (userChoice == allAppointments.size() + 1) break; // Exit if "Return to Menu" option selected
	            System.out.println("Invalid Option... Please Try Again...");
	            continue;
	        }

	        Appointment chosenAppointment = allAppointments.get(userChoice - 1);
	        viewAppointmentDetails(chosenAppointment);  // Show appointment details

	        // Ask user if they want to continue viewing more appointments or return to the menu
	        System.out.println("\nWould you like to view another appointment? (Y/N): ");
	        String continueChoice = inputScanner.next();
	        if (continueChoice.equalsIgnoreCase("N")) {
	            break; // Exit loop and return to menu
	        }

	    } while (userChoice != allAppointments.size());
	    
	    System.out.println("Returning to menu...");
	}


    /**
     * Displays the detailed information of a selected appointment.
     *
     * @param chosenAppointment The appointment whose details will be displayed.
     */
	private void viewAppointmentDetails(Appointment chosenAppointment) {
		// TODO Auto-generated method stub
		System.out.println("----- Appoint Details -----");
		System.out.println("Appointment ID: " + chosenAppointment.getAppointmentID());
		System.out.println("Date & Time : " + chosenAppointment.getAppointmentDate() + " " + chosenAppointment.getAppointmentTime());
		System.out.println("Appointment Status: " + chosenAppointment.getStatus());
		System.out.println("Doctor ID: " + chosenAppointment.getDoctorId());
		System.out.println("Patient ID: " + chosenAppointment.getPatientId());
		System.out.println("Prescripted Medicines:");
		for(PrescribedMedication prescribedMedicine : chosenAppointment.getPrescribedMedications()) {
			System.out.print(prescribedMedicine.getMedicationName());
			System.out.print(" | Quantity: " + prescribedMedicine.getMedicineQuantity());
			System.out.print(" | Status: " + prescribedMedicine.getMedicineStatus());
		}
		System.out.println("-------------------------");
	}

    /**
     * Allows the administrator to approve replenishment requests for medicines.
     */
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

    /**
     * Manages the inventory system, allowing the administrator to view or manage inventory.
     */
	private void inventoryManagement() {
		// TODO Auto-generated method stub
		int userChoice;
		do{
			System.out.println("------ Inventory Management -------");
			System.out.println("1. View Inventory");
			System.out.println("2. Manage Inventory");
			System.out.println("3. Return To Menu");
			System.out.print("Enter Option: ");
			
			//Error Handling
			try {
				userChoice = inputScanner.nextInt();
			}catch(Exception e) {
	        	inputScanner.next(); //Clear Scanner Buffer
	        	userChoice = -1;
			}
			
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
	
    /**
     * Handles stock management by allowing the administrator to add, remove, or update inventory stock.
     */
	private void manageInventory() {
		// TODO Auto-generated method stub
		int userChoice;
		do{ 
			System.out.println("1. Adding stock levels");
			System.out.println("2. Removing stock levels");
			System.out.println("3. Updating Low stock alert");
			System.out.println("4. Return To Inventory Management");
			System.out.print("Enter Option: ");
			//Error Handling
			try {
				userChoice = inputScanner.nextInt();
			}catch(Exception e) {
	        	inputScanner.next(); //Clear Scanner Buffer
	        	userChoice = -1;
			}
		
			switch(userChoice) {
				case 1:
					addStock();
					return;
				case 2:
					removeStock();
					return;
				case 3:
					updateLowStockAlert();
					return;
				case 4:
					return;
				default:
					System.out.println("Invalid Option... Re-enter Choice...");
					break;
			}
		}while(userChoice != 4);
	}

    /**
     * Adds stock for a specific medicine.
     */
	private void addStock() {
	    System.out.print("Enter Medicine Name to Add Stock: ");
	    inputScanner.nextLine(); // Clear the buffer
	    String medicineName = inputScanner.nextLine();
	    
	    System.out.print("Enter Quantity to Add: ");
	    int quantityToAdd = inputScanner.nextInt();
	    
	    adminControl.addStock(medicineName, quantityToAdd);
	}
	
    /**
     * Removes stock for a specific medicine.
     */
	private void removeStock() {
	    System.out.print("Enter Medicine Name to Remove Stock: ");
	    inputScanner.nextLine(); // Clear the buffer
	    String medicineName = inputScanner.nextLine();
	    
	    System.out.print("Enter Quantity to Remove: ");
	    int quantityToRemove = inputScanner.nextInt();
	    
	    adminControl.removeStock(medicineName, quantityToRemove);
	}
	
    /**
     * Updates the low stock alert threshold for a specific medicine.
     */
	private void updateLowStockAlert() {
	    System.out.print("Enter Medicine Name to Set Low Stock Alert: ");
	    inputScanner.nextLine(); // Clear the buffer
	    String medicineName = inputScanner.nextLine();
	    
	    System.out.print("Enter New Low Stock Threshold: ");
	    int newThreshold = inputScanner.nextInt();
	    
	    adminControl.updateLowStockAlert(medicineName, newThreshold);
	}
	
    /**
     * Allows the administrator to manage hospital staff, including viewing, adding, or removing staff members.
     */
	private void manageHospitalStaff() {
		while(true) {
			System.out.println("------ Manage Hospital Staff ------");
			System.out.println("1. View Staff");
			System.out.println("2. Add Staff");
			System.out.println("3. Remove Staff");
			System.out.println("4. Update Staff");
			System.out.println("5. Return to Menu");
			
			int userChoice;
			//Error Handling
			try {
				userChoice = inputScanner.nextInt();
			}catch(Exception e) {
				System.out.println("Invalid Option... Please Try Again...\n");
	        	inputScanner.next(); //Clear Scanner Buffer
	        	return;
			}
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
				case 4:
					updateStaff();
					break;
				case 5:
					break;
				default:
					System.out.println("Invalid Option... Try Again");
			}
		}
	}
	
	/**
	 * Prints a list of staffIDs for the administrator to choose from
	 */
    private void updateStaff() {
		// TODO Auto-generated method stub
    	List<Staff> staffRecords = adminControl.getStaffRecords().getStaffList();
    	while(true){
			System.out.println("----- Available Staff -----");
			int printIndex = 1;
			int userChoice = 0;
			for(Staff staff : staffRecords) {
				System.out.println(printIndex++ + ". " + staff.getUserID());
			}
			System.out.println(printIndex + ". Exit");
			System.out.print("Enter Option: ");
			try {
				userChoice = inputScanner.nextInt();
			}catch(Exception e) {
				System.out.println("You have entered a invalid format... Please Try Again");
				inputScanner.next(); // Clear Buffer
				userChoice = 0;
			}
			if(userChoice >= 1 && userChoice <= staffRecords.size()) {
				Staff chosenStaff = staffRecords.get(userChoice-1);
				updateStaff(chosenStaff);
				return;
				
			}
			else if(userChoice == staffRecords.size()+1) {
				System.out.println("Returning to menu...");
				return;
			}
			System.out.println("Invalid Option... Please Try Again");
    	}
	}
    
    /**
	 * Get administrator to choose desired particular to change and update the particular for the staff
	 */
    private void updateStaff(Staff staff) {
    	int userChoice = 0;
    	
		while(true) {
			System.out.println("----- " + staff.getUserID() + " -----");
			System.out.println("1. Update Name");
			System.out.println("2. Update Gender");
			System.out.println("3. Update Age");
			System.out.println("4. Reset Password");
			System.out.println("5. Cancel Update");
			System.out.print("Enter Option: ");
			try {
				userChoice = inputScanner.nextInt();
			}catch(Exception e) {
				System.out.println("You have entered a invalid format... Please Try Again");
				inputScanner.next(); // Clear Buffer
				userChoice = 0;
			}
			switch(userChoice) {
				case 1:	inputScanner.nextLine(); // Clear Buffer
						System.out.print("Enter New Name: ");
						String newName = inputScanner.nextLine();
						adminControl.updateStaffName(staff, newName);
						System.out.println("Staff Name has been updated to " + staff.getUserName());
						break;
				case 2: while(true) {
							System.out.print("Enter New Gender(Male/Female): ");
							String newGender = inputScanner.next();
							if(newGender.trim().equals("Male")|| newGender.trim().equals("Female")) {
								adminControl.updateStaffGender(staff, newGender);
								break;
							}
							System.out.println("Invalid Gender... Please Try Again...");
						}
						System.out.println("Staff Gender has been updated to " + staff.getUserGender());
						break;
				case 3: while(true) {
							try {
								System.out.print("Enter New Age: ");
								int newAge = inputScanner.nextInt();
								if(newAge < 16) {
									System.out.println("Age has to be above 16...");
									continue;
								}
								adminControl.updateStaffAge(staff, newAge);
								break;
							}catch(Exception e) {
								System.out.println("Invalid Format... Please Input Again...");
								inputScanner.next(); // Clear Buffer
							}
						}
						System.out.println("Staff Age has been updated to " + staff.getAge());
						break;
				case 4: adminControl.resetStaffPassword(staff);
						System.out.println("Staff password has been resetted");
						break;
				case 5: break;
				default: System.out.println("Invalid Option... Please Try Again...");
			}
		}
    }
	/**
     * Removes a staff member from the hospital system based on their staff ID.
     */
	private void removeStaff() {
		// TODO Auto-generated method stub
		System.out.println("------ Remove Staff ------");
		System.out.println("Enter Staff ID");
		String staffIDToRemove = inputScanner.next();
		adminControl.removeStaffByID(staffIDToRemove);
	}

    /**
     * Adds a new staff member to the hospital system after verifying the role and details.
     */
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

    /**
     * Displays a list of all hospital staff members.
     */
	private void viewStaff() {
		System.out.println("------ List of Hospital Staff ------");
		StaffRecords staffRecords = adminControl.getStaffRecords();
		staffRecords.viewStaff();
	}
	

}
