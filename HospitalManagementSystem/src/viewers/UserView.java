package viewers;

import controllers.AdministratorController;
import controllers.AppointmentController;
import controllers.DoctorController;
import controllers.PatientController;
import controllers.PharmacistController;
import controllers.UserController;
import datastorage.DataStorage;
import entities.Administrator;
import entities.Doctor;
import entities.Patient;
import entities.Pharmacist;
import entities.User;
import java.util.Scanner;

public class UserView implements ViewInterface {
	private UserController userControl;
    private Scanner inputScanner;
    
    public UserView(UserController userControl, Scanner inputScanner) {
        this.userControl = userControl;
        this.inputScanner = inputScanner;
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
                return true;
            case 2: return false;
            default:
                System.out.println("Invalid Option... Please Try Again...\n");
                return true;
        }
    }
    
    public void switchView(DataStorage dataStorage) {
    	ViewInterface viewer = null;
        User user = userControl.getLoggedUser();
        if (user instanceof Patient) {
            viewer = new PatientView(new PatientController(user, dataStorage), inputScanner);
        } else if (user instanceof Doctor) {
            viewer = new DoctorView(new DoctorController(user, dataStorage), inputScanner);
        }else if (user instanceof Pharmacist) {
            viewer = new PharmacistView(new PharmacistController(user, dataStorage), inputScanner); 
        }else if (user instanceof Administrator) {
            viewer = new AdministratorView(new AdministratorController(user, dataStorage), inputScanner);
        }
        
        while (viewer.displayMenu()) {
            // Continues till user logs out
        }
        userControl.logOut();
    }
    
    public void displayLogin() {
    	int attempts = 0;
        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String userID = inputScanner.next();
            System.out.print("Enter Password: ");
            String userPass = inputScanner.next();

            if(userControl.userLogin(userID, userPass)) {
            	System.out.println("Login Successful...");
            	if(userControl.isFirstLogin()) promptChangePassword();
            	break;
            }
            else {
                attempts++;
                System.out.println("Invalid Credentials. Please Try Again...");
                if (attempts == 3) {
                    System.out.println("Maximum login attempts reached. Returning to main menu...");
                }
            }
        }
    }
    
    public void promptChangePassword() {
        System.out.println("You are using the default password.");
        System.out.println("You are required to change your password.");
        System.out.print("Enter New Password: ");
        String newPass = inputScanner.next();
        userControl.changePassword(newPass);   
    }
}
