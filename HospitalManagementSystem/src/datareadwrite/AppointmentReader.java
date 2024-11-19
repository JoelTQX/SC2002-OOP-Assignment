package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Administrator;
import entities.Appointment;
import entities.Appointment.AppointmentStatus;
import entities.Appointment.PrescribedMedication;
import entities.Doctor;
import entities.Medicine;
import entities.Pharmacist;
import entities.Staff;
/**
 * The {@code AppointmentReader} class implements the {@link DataReader} interface to read and
 * load Appointment data from a CSV file into the system's data storage.
 * <p>
 * This class is responsible for parsing the contents of a CSV file containing Appointment attributes,
 * such as the patientid, date, and time etc. It then creates {@link Appointment} objects
 * and adds them to the system's inventory.
 * </p>
 * 
 * <p>
 * It reads the data from the {@code Medicine_List.csv} file, which is expected to have the following
 * format:
 * </p>
 * <pre>
 * Appointment ID	Patient ID	Doctor ID	Appointment Status	Appointment Date	Appointment Time	Appointment Type	Prescribed Medications	Consultation Notes
 * </pre>
 * 
 * <p>
 * The {@code populateData} method processes each line in the CSV, creates a {@link Appointment} object
 * for each entry, and stores it in the {@link DataStorage} object's inventory.
 * </p>
 * 
 * @see DataStorage
 * @see Appointment
 * @see DataReader
 * 
 * 
 */
public class AppointmentReader implements DataReader{
	String csvFile = "dataFiles/AppointmentList.csv";
    String line;
    /**
     * Reads appointment records from a "AppointmentList.csv" CSV file and populates the provided 
     * {@code DataStorage} with {@code Appointment} objects.
     * 
     * This class will be called at system startup to create the objects based on the CSV file
     * 
     * @param dataStorage the {@code DataStorage} object to be populated with appointment attribute
     */
	public void populateData(DataStorage dataStorage) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			 String header = reader.readLine();
			 
			 while ((line = reader.readLine()) != null) {
	                String[] cells = line.split(",");
	                String appointmentID = cells[0];
	                String patientId = cells[1];
	                String doctorId = cells[2];
	                String appstat = cells[3];
	                AppointmentStatus appointmentStatus=AppointmentStatus.valueOf(appstat);
	                String appointmentDate = cells[4];
	                String appointmentTime = cells[5];
	                String appointmentType = cells[6];
	                String prescribedMedications = cells[7];
	                String consultationNotes = cells[8];
	                
	                List<PrescribedMedication> medications = parseMedications(prescribedMedications);               
	               
	                Appointment appointment=new Appointment(appointmentID,patientId,doctorId,appointmentStatus,appointmentDate,appointmentTime,appointmentType,medications,consultationNotes);
	                dataStorage.getAppointmentRecords().addAppointment(appointment);
	                
	                }
			 		
	                
			 
		 }catch (Exception e) {
	            e.printStackTrace();
		 }
		 }

	/**
     * Helper class to Parses a string of prescribed medications into a list of {@code PrescribedMedication} objects as when data is read from CSV, it is in String format.
     * 
     * @param prescribedMedications a string containing medication details, formatted as 
     *                              "MedicationName - Quantity: X || ..."
     * @return a list of {@code PrescribedMedication} objects parsed from the input string
     */

	private List<PrescribedMedication> parseMedications(String prescribedMedications) {
	    List<PrescribedMedication> medications = new ArrayList<>();
	    
	    // Split the input string by "||" to get individual medications
	    String[] medicationEntries = prescribedMedications.split("\\|\\|");

	    for (String entry : medicationEntries) {
	        // Split each entry by "-quantity:" to separate medication name and quantity
	        String[] parts = entry.split(" - Quantity: ");
	        if (parts.length == 2) {
	            String name = parts[0].trim();
	            int quantity = Integer.parseInt(parts[1].trim());
	            
	            // Create a new PrescribedMedication object and add it to the list
	            PrescribedMedication medication = new PrescribedMedication(name, quantity);
	            medications.add(medication);
	        } else {
	        }
	    }

	    return medications;
	}

}


	

