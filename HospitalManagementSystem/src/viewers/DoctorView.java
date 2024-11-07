package viewers;

import java.util.List;
import java.util.Scanner;

import controllers.AppointmentController;
import controllers.DoctorController;
import controllers.PatientController;
import datastorage.DataStorage;
import datastorage.PatientRecords;
import entities.Appointment;
import entities.Patient;

public class DoctorView implements ViewInterface{
	private DoctorController doctorControl;
	private AppointmentController appointmentControl; // NEW: Reference to AppointmentController
	private Scanner inputScanner;
	
	public DoctorView(DoctorController doctorControl, Scanner inputScanner) {
		this.doctorControl = doctorControl;
		this.appointmentControl = appointmentControl; // NEW
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
        
        int userChoice = inputScanner.nextInt();
		

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

	private void viewPatientRecords() {
		PatientRecords patientRecords = doctorControl.getPatientsRecords();
		for(Patient patient : patientRecords.getPatientList()) {
			System.out.println("Name: " + patient.getUserName());
			System.out.println("Blood Type: " + patient.getPatientBloodType());
			System.out.println("DOB: " + patient.getPatientDOB());
			System.out.println("Gender: " + patient.getUserGender());
		}
	}
        
    //NEW    
    private void updatePatientRecords() {
		System.out.println("------ Update Patient Medical Records ------");
		System.out.print("Enter Patient ID to update records: ");
		String patientId = inputScanner.nextLine();
		inputScanner.nextLine(); // Consume newline
	
		System.out.print("Enter new diagnoses: ");
		String newDiagnoses = inputScanner.nextLine();
	
		System.out.print("Enter new prescriptions: ");
		String prescriptions = inputScanner.nextLine();
	
		System.out.print("Enter treatment plan: ");
		String treatmentPlan = inputScanner.nextLine();
	
		boolean success = doctorControl.updatePatientRecords(patientId, newDiagnoses, prescriptions, treatmentPlan);
		System.out.println(success ? "Patient records updated successfully." : "Failed to update patient records. Please check the Patient ID.");
	}
	
	//NEW 
	private void viewPersonalSchedule() {
		System.out.println("------ Doctor's Personal Schedule ------");
		String schedule = doctorControl.getPersonalSchedule();
		if (schedule != null && !schedule.isEmpty()) {
			System.out.println(schedule);
		} else {
			System.out.println("No schedule available.");
		}
	}
	
	//NEW 
	private void setAvailability() {
		System.out.println("------ Set Availability for Appointments ------");
		System.out.print("Enter date for availability (YYYY-MM-DD): ");
		String date = inputScanner.nextLine();
	
		System.out.print("Enter available time slots (e.g., 09:00-12:00): ");
		String timeSlots = inputScanner.nextLine();
	
		boolean success = doctorControl.setAvailability(date, timeSlots);
		System.out.println(success ? "Availability set successfully." : "Failed to set availability. Please try again.");
	}
		
        
     
    // NEW: Accept or decline an appointment request
    private void acceptOrDeclineAppointment() {
        System.out.println("------ Accept/Decline Appointment Requests ------");
        List<Appointment> requests = appointmentControl.getAppointmentRequests();
        
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

            boolean success = appointmentControl.handleAppointmentRequest(appointmentId, choice.equals("A"));
            System.out.println(success ? "Request processed successfully." : "Failed to process request. Please check details.");
        }
    }

    // NEW: View upcoming appointments
    private void viewUpcomingAppointments() {
        System.out.println("------ Upcoming Appointments ------");
        List<Appointment> upcomingAppointments = appointmentControl.getUpcomingAppointments();
        
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
        System.out.print("Enter Date of Appointment (YYYY-MM-DD): ");
        String date = inputScanner.next();
        System.out.print("Enter Type of Service (e.g., Consultation, X-ray): ");
        String serviceType = inputScanner.next();
        System.out.print("Enter any Prescribed Medications (comma-separated): ");
        inputScanner.nextLine(); // Consume newline
        String medications = inputScanner.nextLine();
        System.out.print("Enter Consultation Notes: ");
        String notes = inputScanner.nextLine();

        boolean success = appointmentControl.recordAppointmentOutcome(
                appointmentId, date, serviceType, List.of(medications.split(",")), notes);
        
        System.out.println(success ? "Outcome recorded successfully." : "Failed to record outcome.");
    }   
        
        
        
	}


