package viewers;

import controllers.PatientController;
import entities.Appointment;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * The PatientView class provides the user interface for patient-related functionalities.
 * It allows patients to view and manage their personal information, appointments,
 * and medical records through various menu options.
 */
public class PatientView implements ViewInterface {
    private PatientController patientControl;
    private Scanner inputScanner;

    public PatientView(PatientController patientControl, Scanner inputScanner) {
        this.patientControl = patientControl;
        this.inputScanner = inputScanner;
    }

    @Override
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
            case 9:
                return false;
            default:
                System.out.println("Invalid Option... Please Try Again...\n");
                break;
        }
        return true;
    }

    private void updatePersonalInformation() {
        System.out.println("------ Update Personal Information ------");
        System.out.println("1. Email Address");
        System.out.println("2. Contact Information");
        System.out.println("3. Exit");

        int userChoice;
        try {
            userChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Clear the buffer
        } catch (Exception e) {
            System.out.println("Invalid Option... Please Try Again...\n");
            inputScanner.next(); // Clear Scanner Buffer
            return;
        }

        switch (userChoice) {
            case 1:
                System.out.println("Enter New Email Address");
                String email = inputScanner.next();
                patientControl.setPatientContactInfo(email);
                System.out.println("Email Address Updated");
                break;
            case 2:
                System.out.println("Enter New Contact Information");
                String newNumber = inputScanner.next();
                patientControl.setPatientContactNumber(newNumber);
                System.out.println("Contact Information Updated");
                break;
            case 3:
                return;
            default:
                System.out.println("Invalid Option... Please Try Again...\n");
                break;
        }
    }

    private void scheduleAppointment() {
        System.out.println("------ Schedule an Appointment ------");
        System.out.print("Enter Doctor ID: ");
        String doctorId = inputScanner.next();
        System.out.print("Enter Appointment Date (DD/MM/YYYY): ");
        String date = inputScanner.next();

        // Validate date format
        if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Invalid date format. Please use DD/MM/YYYY.");
            return;
        }

        // Validate if the date is a valid calendar date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(date, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Please enter a valid date.");
            return;
        }

        System.out.print("Enter Appointment Time (HH:MM): ");
        String time = inputScanner.next();

        // List of designated time slots
        List<String> allSlots = List.of("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");

        // Validate time format and check if it is within the designated time slots
        if (!time.matches("\\d{2}:\\d{2}") || !allSlots.contains(time)) {
            System.out.println("Invalid time. Please enter a valid time slot (e.g., 09:00, 10:00, etc.).");
            return;
        }

        String success = patientControl.scheduleAppointment(doctorId, date, time);

        if (success != null) {
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

        // Validate date format
        if (!newDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Invalid date format. Please use DD/MM/YYYY.");
            return;
        }

        // Validate if the date is a valid calendar date
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate parsedDate = LocalDate.parse(newDate, dateFormatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date. Please enter a valid date.");
            return;
        }

        System.out.print("Enter New Appointment Time (HH:MM): ");
        String newTime = inputScanner.next();

        // List of designated time slots
        List<String> allSlots = List.of("09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00");

        // Validate time format and check if it is within the designated time slots
        if (!newTime.matches("\\d{2}:\\d{2}") || !allSlots.contains(newTime)) {
            System.out.println("Invalid time. Please enter a valid time slot (e.g., 09:00, 10:00, etc.).");
            return;
        }

        String success = patientControl.rescheduleAppointment(appointmentId, newDate, newTime);

        if (success != null) {
            System.out.println("Appointment No: " + appointmentId + " rescheduled successfully.");
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

    private void viewMedicalRecord() {
        System.out.println("Patient ID: " + patientControl.getUserID());
        System.out.println("Patient Name: " + patientControl.getUserName());
        System.out.println("Patient Gender: " + patientControl.getUserGender());
        System.out.println("Patient Date Of Birth: " + patientControl.getUserDOB());
        System.out.println("Patient Email: " + patientControl.getUserContactInfo());
        System.out.println("Patient Contact Number: " + patientControl.getUserContactNumber());
        System.out.println("Patient Blood Type: " + patientControl.getUserBloodType());
        System.out.println("Patient Diagnoses: " + patientControl.getUserDiagnoses());
        System.out.println("Patient Treatment: " + patientControl.getUserTreatment());
    }

    private void viewAvailableSlots() {
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
                        ", Status: " + appointment.getStatus());
            }
        }
    }
}