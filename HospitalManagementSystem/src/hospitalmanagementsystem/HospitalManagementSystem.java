package hospitalmanagementsystem;

import controllers.AppointmentController;
import controllers.UserController;
import datastorage.DataStorage;
import java.io.IOException;
import java.util.Scanner;
import viewers.UserView;
import viewers.ViewInterface;

public class HospitalManagementSystem {
	private DataStorage dataStorage;
	private UserController userControl;
	private ViewInterface userView;
	private Scanner inputScanner;
	// NEW: Declare AppointmentController field
	private AppointmentController appointmentController;
	
	public HospitalManagementSystem() throws IOException {
		dataStorage = new DataStorage();
		inputScanner = new Scanner(System.in);
		userControl = new UserController(dataStorage);
		// NEW: Initialize AppointmentController
		appointmentController = new AppointmentController();
		userView = new UserView(userControl, inputScanner, appointmentController);
	}
	
	public void startUp() throws IOException {
		dataStorage.initialStartUp();
	}
	
	private void shutDown() {
		System.out.println("System Shutting Down");
		dataStorage.shutdownSave();
		inputScanner.close();
	}
	
	public static void main(String args[]){
		HospitalManagementSystem hms = null;
		try{
			hms = new HospitalManagementSystem();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(hms != null) {
			while(hms.userView.displayMenu()) {
				if(hms.userControl.isLoggedIn()) {
					((UserView) hms.userView).switchView(hms.dataStorage);
				}
				/*
				try {
					hms.startUp();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
				//Loop till User exit System.
			}
			hms.shutDown();
		}
		else System.out.println("Start Up Error!!");
	}
}
