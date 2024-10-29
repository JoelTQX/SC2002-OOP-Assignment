package default_package;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import controllers.*;
import entities.*;
import helpers.*;
import boundaries.*;
import boundaries.Authenticator;
import boundaries.PatientDirectory;
import boundaries.StaffDirectory;
import entities.Appointment;
import entities.Patient;
import entities.Password;
import entities.Staff;
import helpers.CSVUtils;
import helpers.OffOnline;


/**
 * The main Application Class of the HMS (Hospital Management System) Portal Application.
 */
public class Main {
    /**
     * Main Function, starting point of the HMS Portal Application
     *
     * @param args Command-line arguments passed to the application
     * @throws NoSuchAlgorithmException When a cryptographic algorithm is not available.
     * @throws FileNotFoundException When a file with the specified pathname does not exist.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException {
        // Instantiate all the CSV files
        ArrayList<Map<String, String>> passwordRecords = CSVUtils.readCSV("Password.csv");
        ArrayList<Map<String, String>> patientListRecords = CSVUtils.readCSV("PatientList.csv");
        ArrayList<Map<String, String>> staffListRecords = CSVUtils.readCSV("StaffList.csv");
        ArrayList<Map<String, String>> appointmentRecords = CSVUtils.readCSV("AppointmentList.csv");
        ArrayList<Map<String, String>> inventoryRecords = CSVUtils.readCSV("InventoryList.csv");

        // Retrieve objects from CSV files
        ArrayList<Patient> allPatients = OffOnline.patientsRetriever(patientListRecords, passwordRecords);
        ArrayList<Staff> allStaffs = OffOnline.staffsRetriever(staffListRecords, passwordRecords);
        ArrayList<Appointment> allAppointments = OffOnline.appointmentsRetriever(appointmentRecords);
        ArrayList<InventoryItem> allInventoryItems = OffOnline.inventoryRetriever(inventoryRecords);

        // Display the current date
        System.out.print("Welcome to the HMS portal. Today is ");
        LocalDate currentDate = LocalDate.now();
        System.out.println(currentDate);

        Scanner scanner = new Scanner(System.in);
        String networkID = "";
        String password = "";
        String userType = "";

        // Authentication process
        Authenticator authenticator = new Authenticator(); // Instantiate Authenticator object
        for (int attempt = 0; attempt < 3; attempt++) { // Three attempts to log in
            System.out.print("Enter Hospital ID: ");
            networkID = scanner.nextLine().toUpperCase();

            System.out.print("Enter Password: ");
            password = scanner.nextLine();

            System.out.print("Enter user type (e.g. patient/doctor/pharmacist/admin): ");
            userType = scanner.nextLine().toLowerCase();

            // Hash the password to authenticate the user
            Password hashedPassword = new Password(Password.hashPassword(password));
            authenticator.setPermitted(networkID, hashedPassword, passwordRecords, userType); // Authenticating the user input

            if (authenticator.getPermitted()) { // Successful authentication
                System.out.println("Authentication successful!");
                break;
            } else {
                System.out.println("Failed authentication. Please try again.");
            }
        }

        if (!authenticator.getPermitted()) {
            System.out.println("Maximum attempts reached. Exiting the program.");
            return; // Exit if authentication fails
        } else {
            // Redirect user to role-specific menus
            if (userType.equals("staff") || userType.equals("doctor") || userType.equals("pharmacist")) {
                Staff user = null;
                for (Staff staff : allStaffs) {
                    if (staff.getHospitalID().equals(networkID)) {
                        user = staff;
                    }
                }

                assert user != null;
                user.printUserAttributes();
                
                // Check if the user is logging in for the first time
                if (user.getEncryptedPassword().getPW().equals(Password.hashPassword("password"))) {
                    System.out.println("You are logging in for the first time, please change your password.");
                    ChangePasswordController cpc = new ChangePasswordController(user, allPatients, allStaffs);
                }
                
                // Redirect to appropriate menu for staff (doctor or pharmacist)
                if (user.getRole().equals("doctor")) {
                    DoctorMenu doctorMenu = new DoctorMenu(user, allPatients, allAppointments);
                    doctorMenu.display();
                } else if (user.getRole().equals("pharmacist")) {
                    PharmacistMenu pharmacistMenu = new PharmacistMenu(user, allInventoryItems, allAppointments);
                    pharmacistMenu.display();
                } else {
                    StaffDirectory staffDirectory = new StaffDirectory(user, allStaffs, allPatients, allAppointments);
                    staffDirectory.displayMenu();
                }
            } else if (userType.equals("patient")) {
                Patient user = null;
                for (Patient patient : allPatients) {
                    if (patient.getHospitalID().equals(networkID)) {
                        user = patient;
                    }
                }

                assert user != null;
                user.printUserAttributes();
                
                // Check if the patient is logging in for the first time
                if (user.getEncryptedPassword().getPW().equals(Password.hashPassword("password"))) {
                    System.out.println("You are logging in for the first time, please change your password.");
                    ChangePasswordController cpc = new ChangePasswordController(user, allPatients, allStaffs);
                }

                // Patient menu
                PatientDirectory patientDirectory = new PatientDirectory(user, allAppointments);
                patientDirectory.displayMenu();
            }
        }

        // Save any updates made during the session
        ArrayList<Map<String, String>> updatedPasswordRecords = OffOnline.passwordsStorer(allPatients, allStaffs);
        ArrayList<Map<String, String>> updatedPatientListRecords = OffOnline.patientStorer(allPatients);
        ArrayList<Map<String, String>> updatedStaffListRecords = OffOnline.staffStorer(allStaffs);
        ArrayList<Map<String, String>> updatedAppointmentRecords = OffOnline.appointmentStorer(allAppointments);
        ArrayList<Map<String, String>> updatedInventoryRecords = OffOnline.inventoryStorer(allInventoryItems);

        // Write updated data to CSV files
        CSVUtils.writeCSV("Password.csv", updatedPasswordRecords);
        CSVUtils.writeCSV("PatientList.csv", updatedPatientListRecords);
        CSVUtils.writeCSV("StaffList.csv", updatedStaffListRecords);
        CSVUtils.writeCSV("AppointmentList.csv", updatedAppointmentRecords);
        CSVUtils.writeCSV("InventoryList.csv", updatedInventoryRecords);
    }
}
