package viewers;

import controllers.PatientController;
import entities.Appointment;
import java.util.List;
import java.util.Scanner;

public class PatientView implements ViewInterface{
	private PatientController patientControl;
	private Scanner inputScanner;
	
	public PatientView(PatientController patientControl, Scanner inputScanner) {
		this.patientControl = patientControl;
		this.inputScanner = inputScanner;
	}

	public boolean displayMenu() {
		System.out.println("------ Patient Menu ------");
		System.out.println("1. View Medical Record");
		System.out.println("2. Update Personal Information");
		System.out.println("3. View Available Appointment Slots");
		System.out.println("4. Schedule an Appointment");
		System.out.println("5. Reschedule an Appointment");
		System.out.println("6. Cancel an Appointment");
		System.out.println("7. View Scheduled Appointments");
		System.out.println("8. View Past Appointment Outcome Records");
		System.out.println("9. Logout ");
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
				viewMedicalRecord();
				break;
			case 2:
				updatePersonalInformation();
				break;
			case 3: 
				viewAvailableSlots();
				break;
			case 4:
				scheduleAppointment();
				break;
			case 5:
				rescheduleAppointment(); 
				break;
			case 6:
				cancelAppointment(); 
				break;	
			case 7:
				viewScheduledAppointments(); 
				break;
			case 8:
				viewAppointmentOutcomeRecord(); 
				break;

		}
		
		if(userChoice == 9) return false;
		return true;
	}
	



// OPTION 1
	private void viewMedicalRecord() {
		System.out.println("Patient ID: " + patientControl.getUserID());
		System.out.println("Patient Name: " + patientControl.getUserName());
		System.out.println("Patient Gender: " + patientControl.getUserGender());
		System.out.println("Patient Date Of Birth: " + patientControl.getUserDOB());
		System.out.println("Patient Email: " + patientControl.getUserContactInfo());
		System.out.println("Patient Email: " + patientControl.getUserContactNumber());
		System.out.println("Patient Blood Type: " + patientControl.getUserBloodType());
		System.out.println("Patient Diagnoses: " + patientControl.getUserDiagnoses());
		System.out.println("Patient Treatment: " + patientControl.getUserTreatment());
		
	}
	
	//option 2
	private void updatePersonalInformation() {
		System.out.println("------ Update Personal Information ------");
		System.out.println("1. Email Address");
		System.out.println("2. Contact Information");
		System.out.println("3. Exit");
		
		int userChoice = inputScanner.nextInt();
		
		switch(userChoice){
			case 1: 
				System.out.println("Enter New Email Address");
				Scanner input=new Scanner(System.in);
				String email = input.nextLine();
				patientControl.setPatientContactInfo(email);
				/*PatientListWriter writer = new PatientListWriter();
				writer.write(patientControl.getUserID(),6,email);*/
				System.out.println("Email Address Updated");
				break;
			case 2:
				System.out.println("Enter New Contact Information");
				Scanner input1=new Scanner(System.in);
				String newnumber = input1.nextLine();
				patientControl.setPatientContactNumber(newnumber);
				break;
		}
		
	}
	
	// option 3 
	// this is afte the doctor indicate avail
	private void viewAvailableSlots() 
	{
		System.out.println("------ Available Appointment Slots ------");
		List<Appointment> availableSlots = patientControl.getAvailableSlots();
		if (availableSlots.isEmpty()) {
			System.out.println("No available slots at the moment.");
		} else {
			for (Appointment appointment : availableSlots) {
				System.out.println("Appointment ID: " + appointment.getAppointmentID() +
							   ", Doctor ID: " + appointment.getDoctorId() +
							   ", Date: " + appointment.getAppointmentDate() +
							   ", Time: " + appointment.getAppointmentTime() +
							   ", Status: " + appointment.getStatus() );
			}
		}
	}
	
		
	

	private void scheduleAppointment() {
		System.out.println("------ Schedule an Appointment ------");
		System.out.print("Enter Doctor ID: ");
		String doctorId = inputScanner.next();
		//String datesAvailable = getAvailableSlots()
		System.out.print("Enter Appointment Date (DD/MM/YYYY): ");
		String date = inputScanner.next();
		System.out.print("Enter Appointment Time (HH:MM): ");
		String time = inputScanner.next();
	
		String success = patientControl.scheduleAppointment(doctorId, date, time);
	
		if (success != null) {		// creation failed 
			System.out.println("Appointment No: " + success + " is now pending.");
		} else {
			System.out.println("Failed to schedule appointment. Please check the details or try another slot.");
		}
	}
	

	private void rescheduleAppointment() {
		System.out.println("------ Reschedule an Appointment ------");
		System.out.print("Enter Appointment ID to reschedule: ");
		String appointmentId = inputScanner.next();
		System.out.print("Enter New Appointment Date (DD/MM/YYYY): ");
		String newDate = inputScanner.next();
		System.out.print("Enter New Appointment Time (HH:MM): ");
		String newTime = inputScanner.next();
	
		String success = patientControl.rescheduleAppointment( appointmentId, newDate, newTime);
	
		if (success != null) {
			System.out.println("Appointment No: " + appointmentId +" rescheduled successfully.");
			System.out.println("New Appointment No: " + success + " is now pending.");
		} else {
			System.out.println("Failed to reschedule appointment. Please check the details or slot availability.");
		}
	}
	
	private void cancelAppointment() {
		System.out.println("------ Cancel an Appointment ------");
		System.out.print("Enter Appointment ID to cancel: ");
		String appointmentId = inputScanner.next();
	
		boolean success = patientControl.cancelAppointment(appointmentId);
	
		if (success) {
			System.out.println("Appointment canceled successfully.");
		} else {
			System.out.println("Failed to cancel the appointment. Please check the appointment ID.");
		}
	}
	
	private void viewScheduledAppointments() {
    System.out.println("------ Scheduled Appointments ------");
    List<Appointment> appointments = patientControl.getScheduledAppointments();

    if (appointments.isEmpty()) {
        System.out.println("No scheduled appointments found.");
    } else {
        for (Appointment appointment : appointments) {
            System.out.println("Appointment ID: " + appointment.getAppointmentID() +
								", Doctor ID: " + appointment.getDoctorId() +
                               ", Date: " + appointment.getAppointmentDate() +
                               ", Time: " + appointment.getAppointmentTime() +
                               ", Status: " + appointment.getStatus());
        }
    }
}


private void viewAppointmentOutcomeRecord() {
    System.out.println("------ Past Appointment Outcome Records ------");
    List<Appointment> completedAppointments = patientControl.getCompletedAppointments();

    if (completedAppointments.isEmpty()) {
        System.out.println("No past appointments with recorded outcomes.");
    } else {
        for (Appointment appointment : completedAppointments) {
			System.out.println(patientControl.displayDetails(appointment));
        }
    }
}



}
