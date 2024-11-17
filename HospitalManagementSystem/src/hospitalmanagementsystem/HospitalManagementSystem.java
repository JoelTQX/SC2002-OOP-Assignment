package hospitalmanagementsystem;

import controllers.UserController;
import datastorage.DataStorage;
import java.io.IOException;
import java.util.Scanner;
import viewers.UserView;
import viewers.ViewInterface;

/**
 * The main class for the Hospital Management System.
 * This class initializes the system, manages startup and shutdown processes, and handles user interactions through the command line interface.
 */
public class HospitalManagementSystem {
    /**
     * Handles data storage and retrieval.
     */
	private DataStorage dataStorage;
	
    /**
     * Controls user-related operations such as login and view switching.
     */
	private UserController userControl;
	
    /**
     * The interface for displaying user views.
     */
	private ViewInterface userView;
	
    /**
     * Scanner for capturing user input from the command line.
     */
	private Scanner inputScanner;

    /**
     * Constructs a `HospitalManagementSystem` instance, initializing its components.
     *
     * @throws IOException If an error occurs during the initialization of data storage.
     */
	public HospitalManagementSystem() throws IOException {
		dataStorage = new DataStorage();
		inputScanner = new Scanner(System.in);
		userControl = new UserController(dataStorage); 
		userView = new UserView(userControl, inputScanner);
	}
	
    /**
     * Starts up the system by reading necessary data from CSV files.
     *
     * @throws IOException If an error occurs while reading the CSV files.
     */
	public void startUp() throws IOException {
		dataStorage.readCSVs();
	}
	
    /**
     * Shuts down the system by saving records and closing resources.
     */
	private void shutDown() {
		System.out.println("System Shutting Down");
		dataStorage.saveRecords();
		inputScanner.close();
	}
	
    /**
     * The main method for the Hospital Management System.
     * It initializes the system, manages the main loop for user interaction, and handles system shutdown.
     *
     * @param args Command-line arguments (not used).
     */
	public static void main(String args[]){
		HospitalManagementSystem hms = null;
		try{
			hms = new HospitalManagementSystem();
			hms.startUp();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(hms != null) {
			while(hms.userView.displayMenu()) {
				if(hms.userControl.isLoggedIn()) {
					((UserView) hms.userView).switchView(hms.dataStorage);
				}
			}
			hms.shutDown();
		}
		else System.out.println("Start Up Error!!");
	}
}
