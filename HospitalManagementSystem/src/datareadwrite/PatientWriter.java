package datareadwrite;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import datastorage.DataStorage;
import entities.Appointment;
import entities.Medicine;
import entities.Patient;
/**
 * PatientWriter class is used to write Patient attributes to a CSV file.
 * Implements the {@link DataWriter} interface for the {@link Patient} type.
 * The CSV file header will be populated by Patient ID, Password, firstlogin, Name, Date of Birth, Gender, Blood Type, Contact Information, Contact Number, Diagnoses, Treatment
 * 
 * This class reads {@link Patient} records from a {@link DataStorage} object and writes
 * them to a CSV file named {@code Patient_List.csv}. It adds the data row by row, and 
 * each patient's attributes are separated into cells based on the defined headers.
 * </p>
 * 
 * @see DataWriter
 * @see Patient
 * @see DataStorage
 */
public class PatientWriter implements DataWriter<Patient>{
	private int noOfHeaders = 11; // Number of Headers in CSV
	private String csvFile = "dataFiles/Patient_List.csv"; //File Path
	
	/**
     * Write the list of Patient from {@link DataStorage} to the CSV file row by row.
     * 
     * @param dataStorage the {@link DataStorage} object containing list of Patient attribute
     */
	@Override
	public void saveRecords(DataStorage dataStorage) {
		List<Patient> recordToSave = dataStorage.getPatientRecords().getPatientList();
		List<String[]> rowsToWrite = new ArrayList<>();
		
		//Create Header and add to rowsToWrite
		String[] cellsToWrite = createHeader();
		rowsToWrite.add(cellsToWrite);
		System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | "+ cellsToWrite[7] + " | "+ cellsToWrite[8] + " | "+ cellsToWrite[9] + " | "+ cellsToWrite[10] + " | ");
		
		//
		for(Patient patient : recordToSave) {
			cellsToWrite = createCells(patient);
			rowsToWrite.add(cellsToWrite);
			System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | "+ cellsToWrite[7] + " | "+ cellsToWrite[8] + " | "+ cellsToWrite[9] + " | "+ cellsToWrite[10] + " | ");
		}
		
		//Try to access the csvFile
		try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
			//Try to Write data to CSV File
			try(PrintWriter writer = new PrintWriter(new FileWriter(csvFile))){
				for(String[] currentRow : rowsToWrite) {
					writer.println(String.join(",", currentRow));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Success Message
		System.out.println("Patient Records saved successfully");
	}
	/**
     * Generates the header row for the Patient CSV file.
     * 
     * @return a string array containing the column headers
     */
	@Override
	public String[] createHeader() {
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Patient ID";
		headerCells[1] = "Password";
		headerCells[2] = "firstlogin";
		headerCells[3] = "Name";
		headerCells[4] = "Date of Birth";
		headerCells[5] = "Gender";
		headerCells[6] = "Blood Type";
		headerCells[7] = "Contact Information";
		headerCells[8] = "Contact Number";
		headerCells[9] = "Diagnoses";
		headerCells[10] = "Treatment";
		
		return headerCells;
	}
	/**
     * This class creates array of Patient attributes to be saved. Example: PatientCells[0]=UserName of this Patient.
     * 
     * @param Patient the {@link Appointment} object containing Patient attributes
     * @return a string array representing a single row of attributes for the CSV file separated into columns
     */
	@Override
	public String[] createCells(Patient patient) {
		String[] patientCells = new String[noOfHeaders];
		patientCells[0] = patient.getUserID();
		patientCells[1] = patient.getPassword();
		patientCells[2] = String.valueOf(patient.isFirstLogin());
		patientCells[3] = patient.getUserName();
		patientCells[4] = patient.getPatientDOB();
		patientCells[5] = patient.getUserGender();
		patientCells[6] = patient.getPatientBloodType();
		patientCells[7] = patient.getPatientContactInfo();
		patientCells[8] = patient.getPatientContactNumber();
		patientCells[9] = patient.getPatientdiagnoses();
		patientCells[10] = patient.getPatienttreatment();
		
		return patientCells;
	}

}
