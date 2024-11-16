package viewers;

import java.util.List;
import java.util.Scanner;

import controllers.PharmacistController;
import entities.Appointment;
import entities.Appointment.PrescribedMedication;
import entities.Medicine;

/**
 * The `PharmacistView` class provides the user interface for pharmacists
 * in the Hospital Management System. It allows pharmacists to view appointment
 * records, update prescription statuses, manage medication inventory, and
 * submit replenishment requests.
 */
public class PharmacistView implements ViewInterface {
    /** Controller for managing pharmacist-related operations. */
    private PharmacistController pharmacistControl;

    /** Scanner for user input. */
    private Scanner inputScanner;

    /**
     * Constructs a `PharmacistView` object with the specified pharmacist
     * controller and input scanner.
     *
     * @param pharmacistControl the controller for pharmacist-related operations.
     * @param inputScanner the scanner for reading user input.
     */
    public PharmacistView(PharmacistController pharmacistControl, Scanner inputScanner) {
        this.pharmacistControl = pharmacistControl;
        this.inputScanner = inputScanner;
    }

    /**
     * Displays the pharmacist menu and handles user interactions.
     *
     * @return `true` to continue displaying the menu;
     *         `false` to log out.
     */
    @Override
    public boolean displayMenu() {
        System.out.println("------ Pharmacist Menu ------");
        System.out.println("1. View Appointment Outcome Records");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. Logout");

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
                viewAppointmentOutcomeRecord();
                break;
            case 2:
                updatePrescriptionStatus();
                break;
            case 3:
                viewMedicationInventory();
                break;
            case 4:
                submitReplenishment();
                break;
            case 5:
                return false;
            default:
                System.out.println("Invalid Option... Please Try Again");
                break;
        }
        return true; // Continue looping
    }

    /**
     * Displays a list of completed appointments with recorded outcomes.
     * Allows the user to view details of a selected appointment.
     */
    private void viewAppointmentOutcomeRecord() {
        List<Appointment> completedAppointments = pharmacistControl.getCompletedAppointments();
        int displayCount = 1;
        int userChoice = -1;

        if (completedAppointments.isEmpty()) {
            System.out.println("No past appointments with recorded outcomes.");
            return;
        }

        System.out.println("------ Past Appointment Outcome Records ------");
        for (Appointment appointment : completedAppointments) {
            System.out.println(displayCount++ + ". " + appointment.getAppointmentID());
        }

        // Exit/Return option
        System.out.println(displayCount + ". Return to Menu");

        do {
            try {
                userChoice = inputScanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Format... Please Input An Integer");
                inputScanner.next(); // Clear Buffer
                continue;
            }

            if (userChoice > completedAppointments.size()) {
                if (userChoice == completedAppointments.size() + 1) break;
                System.out.println("Invalid Option... Please Try Again...");
                continue;
            }

            Appointment chosenAppointment = completedAppointments.get(userChoice - 1);
            viewAppointmentDetails(chosenAppointment);
            return;
        } while (userChoice != completedAppointments.size());

        System.out.println("Returning to menu...");
    }

    /**
     * Displays the details of a specific appointment.
     *
     * @param chosenAppointment the appointment to display details for.
     */
    private void viewAppointmentDetails(Appointment chosenAppointment) {
        System.out.println("----- Appointment Details -----");
        System.out.println("Appointment ID: " + chosenAppointment.getAppointmentID());
        System.out.println("Doctor ID: " + chosenAppointment.getDoctorId());
        System.out.println("Patient ID: " + chosenAppointment.getPatientId());
        System.out.println("Appointment Date: " + chosenAppointment.getAppointmentDate());
        System.out.println("Prescribed Medicines:");
        for (PrescribedMedication prescribedMedicine : chosenAppointment.getPrescribedMedications()) {
            System.out.print(prescribedMedicine.getMedicationName());
            System.out.print(" | Quantity: " + prescribedMedicine.getMedicineQuantity());
            System.out.println(" | Status: " + prescribedMedicine.getMedicineStatus());
        }
        System.out.println("Appointment Type: " + chosenAppointment.getAppointmentType());
        System.out.println("Consultation Notes: " + chosenAppointment.getConsultationNotes());
        System.out.println("-------------------------------");
    }

    /**
     * Updates the prescription status of a medicine for a specific appointment.
     */
    private void updatePrescriptionStatus() {
        System.out.println("------ Update Prescription Status ------");
        System.out.print("Enter Appointment ID: ");
        String appointmentID = inputScanner.next();
        pharmacistControl.updatePrescriptionStatus(appointmentID);
    }

    /**
     * Displays the current medication inventory.
     */
    private void viewMedicationInventory() {
        pharmacistControl.getInventory().viewInventory();
    }

    /**
     * Submits a replenishment request for a specific medicine in the inventory.
     */
    private void submitReplenishment() {
        List<Medicine> medicineRecords = pharmacistControl.getInventory().getMedicineRecords();
        int medicineChoice, medicineQuantity;

        // Print available medicines
        System.out.println("------- Medicine Replenishment -------");
        for (Medicine medicine : medicineRecords) {
            System.out.println((medicineRecords.indexOf(medicine) + 1) + ". " + medicine.getMedicineName());
        }
        // Last option to return to menu
        System.out.println((medicineRecords.size() + 1) + ". Return to Menu");

        // Loop to ask for medicine choice
        while (true) {
            System.out.print("Select Option: ");
            try {
                medicineChoice = inputScanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid Input... Re-enter Choice...");
                inputScanner.next(); // Clear Scanner Buffer
                medicineChoice = -1;
            }

            if (medicineChoice == medicineRecords.size() + 1) {
                System.out.println("Returning to menu...");
                return;
            } else if (medicineChoice > medicineRecords.size() || medicineChoice < 1) {
                System.out.println("Invalid Option... Try Again...");
            }
            break;
        }

        System.out.println("To Cancel Replenishment Enter Values LESS OR EQUAL TO 0");
        System.out.print("Enter Quantity to Replenish: ");
        try {
            medicineQuantity = inputScanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid Input... Returning to Main Menu");
            inputScanner.next(); // Clear Scanner Buffer
            medicineQuantity = -1;
        }

        if (medicineQuantity <= 0) return;
        pharmacistControl.createReplenishmentRequest(medicineChoice - 1, medicineQuantity);
        System.out.println("Replenishment Request Submitted... Returning to menu...");
    }
}
