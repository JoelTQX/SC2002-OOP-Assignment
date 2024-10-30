package viewers;

import java.util.Scanner;

import controllers.DoctorController;

public class DoctorView implements ViewInterface{
	private DoctorController doctorControl;
	private Scanner inputScanner;
	
	public DoctorView(DoctorController doctorControl, Scanner inputScanner) {
		this.doctorControl = doctorControl;
		this.inputScanner = inputScanner;
	}

	@Override
	public boolean displayMenu() {
		System.out.println("--- Doctor Menu ---");
		System.out.println("?. Exit ");
		return false;
	}

}
