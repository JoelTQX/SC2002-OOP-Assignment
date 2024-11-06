package viewers;
import java.io.IOException;
import java.util.Scanner;

import DataReadWrite.StaffListWriter;
import controllers.AdministratorController;
import datastorage.DataStorage;
import datastorage.StaffRecords;
import hospitalmanagementsystem.HospitalManagementSystem;

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
		
		if(userChoice == 5) return false;
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
				System.out.println("------ List of Hospital Staff ------");
			DataStorage ds = null;
			try {
				ds = new DataStorage();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				ds=ds.getDataStorage();
				StaffRecords staffrecords=ds.getStaffRecords();
				staffrecords.viewStaff();
				break;
				
			case 2:
				System.out.println("------ Add Staff ------");
				System.out.println("Enter New Staff ID");
				//atchar[0] check inital letter to match role implementation
				
				Scanner input=new Scanner(System.in);
				String userID = input.nextLine();
				String role;
				char prerole=userID.charAt(0);
				boolean appropriate=false;
				while(!appropriate)
				switch(prerole) {
				case 'D':
					role="Doctor";
					appropriate=true;
					break;
					
				case 'P':
					role="Pharmacist";
					appropriate=true;
					break;
				case 'A':
					role="Administrator";
					appropriate=true;
					break;
				default:
					System.out.println("Please input an appropriate UserID");
					break;
				}
			
				System.out.println("Enter password");
				String userPass = input.nextLine();
				System.out.println("Enter Name");
				String userName = input.nextLine();
				System.out.println("Enter Gender");
				String gender = input.nextLine();
				System.out.println("Enter Age");
				int age = input.nextInt();
				break;
			
			case 3:
				System.out.println("------ Remove Staff ------");
				System.out.println("Enter Staff ID");
				Scanner scanner=new Scanner(System.in);
				String usertodel=scanner.nextLine();
				StaffListWriter del=new StaffListWriter();
				del.deleteUser(usertodel);
				//Scanner input=new Scanner(System.in);
				break;
		}
	}
	

}
