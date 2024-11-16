package viewers;

import controllers.AdministratorController;
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

/**
 * The `UserView` class handles the user interface for the Hospital 
 * Management System. It manages user interactions such as login, menu display,
 * and navigation based on user roles.
 */
public class UserView implements ViewInterface {
    /** Controller for managing user-related operations. */
    private UserController userControl;

    /** Scanner for user input. */
    private Scanner inputScanner;

    /**
     * Constructs a `UserView` object with the specified user controller
     * and input scanner.
     *
     * @param userControl the controller for user-related operations.
     * @param inputScanner the scanner for reading user input.
     */
    public UserView(UserController userControl, Scanner inputScanner) {
        this.userControl = userControl;
        this.inputScanner = inputScanner;
    }

    /**
     * Displays the main menu for the Hospital Management System.
     * Provides options for login or exit.
     *
     * @return `true` to continue displaying the menu;
     *         `false` to exit the system.
     */
    public boolean displayMenu() {
        System.out.println("--- Welcome to Hospital Management System ---");
        System.out.println("1. Login ");
        System.out.println("2. Exit ");
        System.out.print("Choice: ");

        int userChoice;

        // Error handling for invalid input
        try {
            userChoice = inputScanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Option... Please Try Again...\n");
            inputScanner.next(); // Clear Scanner Buffer
            return true;
        }

        switch (userChoice) {
            case 1:
                displayLogin();
                return true;
            case 2:
                return false;
            default:
                System.out.println("Invalid Option... Please Try Again...\n");
                return true;
        }
    }

    /**
     * Switches the view based on the logged-in user's role. Each role has
     * a specific view (e.g., PatientView, DoctorView).
     *
     * @param dataStorage the `DataStorage` object used for accessing 
     *                    system data.
     */
    public void switchView(DataStorage dataStorage) {
        ViewInterface viewer = null;
        User user = userControl.getLoggedUser();

        if (user instanceof Patient) {
            viewer = new PatientView(new PatientController(user, dataStorage), inputScanner);
        } else if (user instanceof Doctor) {
            viewer = new DoctorView(new DoctorController(user, dataStorage), inputScanner);
        } else if (user instanceof Pharmacist) {
            viewer = new PharmacistView(new PharmacistController(user, dataStorage), inputScanner);
        } else if (user instanceof Administrator) {
            viewer = new AdministratorView(new AdministratorController(user, dataStorage), inputScanner);
        }

        // Continue displaying the menu until the user logs out
        while (viewer.displayMenu()) {
            // Loop until logout
        }

        userControl.logOut();
    }

    /**
     * Handles the login process for users. Allows up to three attempts
     * to log in with valid credentials.
     */
    public void displayLogin() {
        int attempts = 0;

        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String userID = inputScanner.next();
            System.out.print("Enter Password: ");
            String userPass = inputScanner.next();

            if (userControl.userLogin(userID, userPass)) {
                System.out.println("Login Successful...");
                if (userControl.isFirstLogin()) {
                    promptChangePassword();
                }
                break;
            } else {
                attempts++;
                System.out.println("Invalid Credentials. Please Try Again...");
                if (attempts == 3) {
                    System.out.println("Maximum login attempts reached. Returning to main menu...");
                }
            }
        }
    }

    /**
     * Prompts the user to change their password if they are using the default
     * password. This is mandatory for the first login.
     */
    public void promptChangePassword() {
        System.out.println("You are using the default password.");
        System.out.println("You are required to change your password.");
        System.out.print("Enter New Password: ");
        String newPass = inputScanner.next();
        userControl.changePassword(newPass);
    }
}
