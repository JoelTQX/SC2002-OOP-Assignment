package datareadwrite;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import datastorage.DataStorage;
import entities.Medicine;
import entities.Patient;

public class PatientWriter implements DataWriter<Patient>{
	private int noOfHeaders = 8; // Number of Headers in CSV
	private String csvFile = "dataFiles/Patient_List.csv"; //File Path
	@Override
	public void saveRecords(DataStorage dataStorage) {
		List<Patient> recordToSave = dataStorage.getPatientRecords().getPatientList();
		List<String[]> rowsToWrite = new ArrayList<>();
		
		//Create Header and add to rowsToWrite
		String[] cellsToWrite = createHeader();
		rowsToWrite.add(cellsToWrite);
		System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | "+ cellsToWrite[7] + " | ");
		
		//Populate Rows based on Available Medicine
		for(Patient patient : recordToSave) {
			cellsToWrite = createCells(patient);
			rowsToWrite.add(cellsToWrite);
			System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | "+ cellsToWrite[7] + " | ");
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
		return headerCells;
	}

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
		return patientCells;
	}

}
