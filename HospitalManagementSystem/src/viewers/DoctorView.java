package viewers;

import controllers.DoctorController;
import datastorage.PatientRecords;
import entities.Appointment;
import entities.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DoctorView implements ViewInterface{
	private DoctorController doctorControl;
	private Scanner inputScanner;
	
	public DoctorView(DoctorController doctorControl, Scanner inputScanner) {
		this.doctorControl = doctorControl;
		this.inputScanner = inputScanner;
	}

	@Override
	public boolean displayMenu() {
		System.out.println("Doctor Menu:");
        System.out.println("(1) View Patient Medical Records");
        System.out.println("(2) Update Patient Medical Records");
        System.out.println("(3) View Personal Schedule");
        System.out.println("(4) Set Availability for Appointments");
        System.out.println("(5) Accept or Decline Appointment Requests");
        System.out.println("(6) View Upcoming Appointments");
        System.out.println("(7) Record Appointment Outcome");
        System.out.println("(8) Logout");
        
        int userChoice;
        
		try {
			userChoice = inputScanner.nextInt();
			inputScanner.nextLine(); // Clear the buffer
		}catch(Exception e) {
        	System.out.println("Invalid Option... Please Try Again...\n");
        	inputScanner.next(); //Clear Scanner Buffer
        	return true;
		}

        switch (userChoice) {
            case 1:
                viewPatientRecords();
                break;
            case 2:
                updatePatientRecords();
                break;
            case 3:
                viewPersonalSchedule();
                break;
            case 4:
                setAvailability();
                break;
            case 5:
                acceptOrDeclineAppointment(); // NEW: Handle appointment requests
                break;
            case 6:
                viewUpcomingAppointments(); // NEW: View upcoming appointments
                break;
            case 7:
                recordAppointmentOutcome(); // NEW: Record appointment outcome
                break;
            case 8:
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

        // OPTION 1 
	private void viewPatientRecords() {
		PatientRecords patientRecords = doctorControl.getPatientsRecords();
		for(Patient patient : patientRecords.getPatientList()) {
			System.out.println("Name: " + patient.getUserName());
			System.out.println("Blood Type: " + patient.getPatientBloodType());
			System.out.println("DOB: " + patient.getPatientDOB());
			System.out.println("Gender: " + patient.getUserGender());
            System.out.println("    ");     // line spacing 
		}
	}
        
    //OPTION 2   
    private void updatePatientRecords() {
		System.out.println("------ Update Patient Medical Records ------");
		System.out.print("Enter Patient ID to update records: ");
		String patientId = inputScanner.nextLine();
		
		
		System.out.print("Enter new diagnoses: ");
		String newDiagnoses = inputScanner.nextLine();
	
		System.out.print("Enter new prescriptions: ");
		String prescriptions = inputScanner.nextLine();
	
		System.out.print("Enter treatment plan: ");
		String treatmentPlan = inputScanner.nextLine();
	
		boolean success = doctorControl.updatePatientRecords(patientId, newDiagnoses, prescriptions, treatmentPlan);
		System.out.println(success ? "Patient records updated successfully." : "Failed to update patient records. Please check the Patient ID.");
	}
	
	//NEW   OPTION3 
    // shows the doctor ALL SCHDULED APPOINTMENTS WITH THEIR ID 
	private void viewPersonalSchedule() {
		System.out.println("------ Doctor's Personal Schedule ------"); 
		if(doctorControl.getUpcomingAppointments().isEmpty()) {
			System.out.println("No Upcoming Schedule");
		}
		for(Appointment appointment : doctorControl.getUpcomingAppointments()) {
			System.out.println("----------------------------");
			System.out.println("Appointment ID: " + appointment.getAppointmentID());
			System.out.println("Date & Time: " + appointment.getAppointmentDate() + " " + appointment.getAppointmentTime());
			System.out.println("Patient ID: " + appointment.getPatientId());
			
		}
		System.out.println("----------------------------");
	}
	
	//NEW OPTION 4 
    // SET BY DATE 
    // AFTER CHOOSING FROM A LIST OF DATES WHERE THEY HAVE SLOTS 
    // EACH DAY IS 9-5 WHICH IS 9 SLOTS 
    private void setAvailability() {
        System.out.println("------ Set Availability for Appointments ------");
        System.out.println("Enter date for availability (DD/MM/YYYY): ");
        String date = inputScanner.nextLine();
        
        // CALL THE GET SLOT FUNCTION TO SHOW THE AMT OF SLOTS ON THAT DAY
        System.out.println("Slots available are:");
        List<String> emptySlots = doctorControl.getEmptySlots(date);
        System.out.println(emptySlots); // print the slots using a general method
        
        boolean continueSetting = true;
        while (continueSetting) {
            System.out.print("Enter available time slot (HH:MM) (e.g., 09:00): ");
            String timeSlot = inputScanner.nextLine();
            boolean success = doctorControl.setAvailability(date, timeSlot); // call the apt constructor
            System.out.println(success ? "Availability set successfully." : "Failed to set availability. Please try again.");
            
            System.out.print("Do you want to set another time slot? (yes/no): ");
            String response = inputScanner.nextLine().trim().toLowerCase();
            continueSetting = response.equals("yes");
        }
    }


    
    // NEW: Accept or decline an appointment request
    // this is after patients indiated PENDING
    private void acceptOrDeclineAppointment() {
        System.out.println("------ Accept/Decline Appointment Requests ------");
        List<Appointment> requests = doctorControl.getAppointmentRequests();    // fetch the list of doctor APT
        
        if (requests.isEmpty()) {
            System.out.println("No pending appointment requests.");
        } else {
            for (Appointment appointment : requests) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID() +
                                   ", Date: " + appointment.getAppointmentDate() +
                                   ", Time: " + appointment.getAppointmentTime() +
                                   ", Patient ID: " + appointment.getPatientId());
            }

            System.out.print("Enter Appointment ID to Accept/Decline: ");
            String appointmentId = inputScanner.next();
            System.out.print("Accept (A) or Decline (D): ");
            String choice = inputScanner.next().toUpperCase();

            boolean success = doctorControl.handleAppointmentRequest(appointmentId, choice.equals("A"));
            System.out.println(success ? "Request processed successfully." : "Failed to process request. Please check details.");
        }
    }

    // NEW: View upcoming appointments
    // for the doctor 
    private void viewUpcomingAppointments() {
        System.out.println("------ Upcoming Appointments ------");
        List<Appointment> upcomingAppointments = doctorControl.getUpcomingAppointments();
        
        if (upcomingAppointments.isEmpty()) {
            System.out.println("No upcoming appointments.");
        } else {
            for (Appointment appointment : upcomingAppointments) {
                System.out.println("Appointment ID: " + appointment.getAppointmentID() +
                                   ", Date: " + appointment.getAppointmentDate() +
                                   ", Time: " + appointment.getAppointmentTime() +
                                   ", Patient ID: " + appointment.getPatientId());
            }
        }
    }

    // NEW: Record appointment outcome
    private void recordAppointmentOutcome() {
        System.out.println("------ Record Appointment Outcome ------");
        System.out.print("Enter Appointment ID: ");
        String appointmentId = inputScanner.next();
        System.out.print("Enter Date of Appointment (DD/MM/YYYY): ");
        String date = inputScanner.next();
        System.out.print("Enter Type of Service (e.g., Consultation, X-ray): ");
        String serviceType = inputScanner.next();
        System.out.print("Enter any Prescribed Medications (comma-separated): ");
        inputScanner.nextLine(); // Consume newline
        String medications = inputScanner.nextLine();
        System.out.print("Enter Medications QTY (comma-separated), Enter 0 if none: ");
        //inputScanner.nextLine(); // Consume newline
        String inputLine = inputScanner.nextLine();  // Read the whole line
        String[] tokens = inputLine.split(",");      // Split by comma
        List<Integer> medicationsQTYList = new ArrayList<>();
        for (String token : tokens) {
            medicationsQTYList.add(Integer.parseInt(token));  // Convert to Integer and add to list
        }
        System.out.print("Enter Consultation Notes: ");
        String notes = inputScanner.nextLine();

        boolean success = doctorControl.recordAppointmentOutcome(
                appointmentId, date, serviceType, List.of(medications.split(",")), medicationsQTYList, notes);
        
        System.out.println(success ? "Outcome recorded successfully." : "Failed to record outcome.");
    }   
        
        
        
	}


