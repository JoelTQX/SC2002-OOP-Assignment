package viewers;

import java.util.Scanner;

import controllers.DoctorController;
import controllers.PatientController;
import controllers.UserController;
import entities.User;
import entities.Doctor;
import entities.Patient;

public class UserView implements ViewInterface {
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
        // Just in case
        return true;
    }
    
    public void switchView() {
        ViewInterface viewer = null;
        if (user instanceof Patient) {
            viewer = new PatientView(new PatientController(user), inputScanner);
            isLoggedIn=false;
        } else if (user instanceof Doctor) {
            viewer = new DoctorView(new DoctorController(user), inputScanner);
            isLoggedIn=false;
        }
        
        while (viewer.displayMenu()) {
            // Continues till user logs out
        }
    }
    
    public void displayLogin() {
        int attempts = 0;
        while (attempts < 3 && !isLoggedIn) {
            System.out.print("Enter User ID: ");
            String userID = inputScanner.next();
            System.out.print("Enter Password: ");
            String userPass = inputScanner.next();

            user = userControl.userLogin(userID, userPass);
            
            if (user != null) {
                isLoggedIn = true;
                System.out.println("Login Successful...");
                if (user.isFirstLogin()) promptChangePassword();
                return;
            } else {
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
        userControl.changePassword(this.user, newPass);
        
    }
}
