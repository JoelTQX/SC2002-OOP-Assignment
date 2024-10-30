package viewers;

import java.util.Scanner;

import controllers.ControllerInterface;
import controllers.DoctorController;
import controllers.PatientController;
import controllers.PharmacistController;
import controllers.UserController;
import entities.Doctor;
import entities.Patient;
import entities.Pharmacist;
import entities.User;

public class UserView implements ViewInterface{
	private boolean isLoggedIn;
	private UserController userControl;
	private Scanner inputScanner;
	private User user;
	
	public UserView(UserController userControl, Scanner inputScanner) {
		this.userControl = userControl;
		this.inputScanner = inputScanner;
		isLoggedIn = false;
	}
	
	public boolean displayMenu() {
		System.out.println("--- Welcome to Hospital Management System ---");
		System.out.println("1. Login ");
		System.out.println("2. Exit ");
		System.out.print("Choice: ");
		int userChoice = inputScanner.nextInt();
		switch(userChoice) {
			case 1:
				displayLogin();
				if(isLoggedIn) switchView();
				break;
			case 2: return false;
			default:
				System.out.println("Invalid Option... Please Try Again...\n");
				return true;
		}
		//Just In Case
		return true;
	}
	
	public void switchView() {
		ViewInterface viewer = null;
		if(user instanceof Patient) {
			PatientController patientControl = new PatientController(user);
			viewer = new PatientView(patientControl, inputScanner);
		}
		else if(user instanceof Doctor) {
			DoctorController doctorControl = new DoctorController(user);
			viewer = new DoctorView(doctorControl, inputScanner);
		}
		else if(user instanceof Pharmacist) {
			PharmacistController pharmacistControl = new PharmacistController(userControl);//was new PharmacistController(user,userControl)
			viewer = new PharmacistView(pharmacistControl, inputScanner);
		}
		while(viewer.displayMenu()) {
			//Continues till User Logs Out
		}
	}
	
	public void displayLogin() {
		System.out.print("Enter User ID: ");
		String userID = inputScanner.next();
		System.out.print("Enter Password: ");
		String userPass = inputScanner.next();
		
		user = userControl.userLogin(userID, userPass);
		if(user != null) {
			isLoggedIn = true;
			if(user.isFirstLogin()) promptChangePassword();
			System.out.println("Login Successful...");
		}
		else {
			isLoggedIn = false;
			System.out.println("Invalid Credentials. Please Try Again...");
		}
	}
	
	public void promptChangePassword() {
		System.out.println("You are using the default password.");
		System.out.println("You are required to change your password");
		System.out.print("Enter New Password: ");
		String newPass = inputScanner.next();
		userControl.changePassword(this.user, newPass);
	}
}
