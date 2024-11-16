package viewers;

import controllers.DoctorController;
import datastorage.PatientRecords;
import entities.Appointment;
import entities.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The `DoctorView` class represents the interface for doctors to interact with the hospital management system.
 * It provides functionality for viewing and updating patient records, managing personal schedules, 
 * setting availability, handling appointment requests, and recording appointment outcomes.
 */
public class DoctorView implements ViewInterface {
    private DoctorController doctorControl;
    private Scanner inputScanner;

    /**
     * Constructs a `DoctorView` object.
     *
     * @param doctorControl the controller handling doctor-related operations
     * @param inputScanner  the `code Scanner` instance for user input
     */
    public DoctorView(DoctorController doctorControl, Scanner inputScanner) {
        this.doctorControl = doctorControl;
        this.inputScanner = inputScanner;
    }

    /**
     * Displays the main menu for the doctor and processes user inputs to navigate through options.
     *
     * @return `true` to continue displaying the menu, `false` to logout
     */
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
        } catch (Exception e) {
            System.out.println("Invalid Option... Please Try Again...\n");
            inputScanner.next(); // Clear Scanner Buffer
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
                acceptOrDeclineAppointment();
                break;
            case 6:
                viewUpcomingAppointments();
                break;
            case 7:
                recordAppointmentOutcome();
                break;
            case 8:
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }

    /**
     * Displays the medical records of all patients accessible to the doctor.
     */

    // OPTION 1: View Patient Medical Records
    private void viewPatientRecords() {
        PatientRecords patientRecords = doctorControl.getPatientsRecords();
        for (Patient patient : patientRecords.getPatientList()) {
            System.out.println("Name: " + patient.getUserName());
            System.out.println("Blood Type: " + patient.getPatientBloodType());
            System.out.println("DOB: " + patient.getPatientDOB());
            System.out.println("Gender: " + patient.getUserGender());
            System.out.println("Diagnoses: " + patient.getPatientdiagnoses());
            System.out.println("Treatment: " + patient.getPatienttreatment());
            System.out.println("    "); // line spacing
        }
    }

    /**
     * Updates the medical records of a specific patient.
     */
    
    // OPTION 2: Update Patient Medical Records
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

    /**
     * Displays the doctor's personal schedule, listing upcoming appointments.
     */

    // OPTION 3: View Personal Schedule
    private void viewPersonalSchedule() {
        System.out.println("------ Doctor's Personal Schedule ------");
        List<Appointment> upcomingAppointments = doctorControl.getUpcomingAppointments();
        if (upcomingAppointments.isEmpty()) {
            System.out.println("No Upcoming Schedule");
        } else {
            for (Appointment appointment : upcomingAppointments) {
                System.out.println("----------------------------");
                System.out.println("Appointment ID: " + appointment.getAppointmentID());
                System.out.println("Date & Time: " + appointment.getAppointmentDate() + " " + appointment.getAppointmentTime());
                System.out.println("Patient ID: " + appointment.getPatientId());
            }
            System.out.println("----------------------------");
        }
    }

    /**
     * Allows the doctor to set their availability for appointments on a specific date.
     */
    
    // OPTION 4: Set Availability for Appointments
    private void setAvailability() {
        System.out.println("------ Set Availability for Appointments ------");
        System.out.print("Enter date for availability (DD/MM/YYYY): ");
        String date = inputScanner.nextLine();

        // Display available slots for the specified date
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

    /**
     * Allows the doctor to accept or decline appointment requests.
     */

    // OPTION 5: Accept or Decline Appointment Requests
    private void acceptOrDeclineAppointment() {
        System.out.println("------ Accept/Decline Appointment Requests ------");
        List<Appointment> requests = doctorControl.getAppointmentRequests(); // fetch the list of doctor APT

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

    /**
     * Displays a list of the doctor's upcoming appointments.
     */
    
    // OPTION 6: View Upcoming Appointments
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

    /**
     * Records the outcome of a completed appointment.
     */
    
    // OPTION 7: Record Appointment Outcome
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
        String inputLine = inputScanner.nextLine(); // Read the whole line
        String[] tokens = inputLine.split(","); // Split by comma
        List<Integer> medicationsQTYList = new ArrayList<>();
        for (String token : tokens) {
            try {
                medicationsQTYList.add(Integer.parseInt(token.trim())); // Convert to Integer and add to list
            } catch (NumberFormatException e) {
                System.out.println("Invalid quantity format. Please enter valid numbers.");
                return;
            }
        }
        System.out.print("Enter Consultation Notes: ");
        String notes = inputScanner.nextLine();

        boolean success = doctorControl.recordAppointmentOutcome(
                appointmentId, date, serviceType, List.of(medications.split(",")), medicationsQTYList, notes);

        System.out.println(success ? "Outcome recorded successfully." : "Failed to record outcome.");
    }
}