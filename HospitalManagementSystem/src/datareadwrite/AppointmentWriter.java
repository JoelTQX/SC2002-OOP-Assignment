package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Appointment;
import entities.Patient;
import entities.Appointment.PrescribedMedication;

public class AppointmentWriter implements DataWriter<Appointment>{
	private int noOfHeaders = 9; // Number of Headers in CSV
	private String csvFile = "dataFiles/AppointmentList.csv"; //File Path
	@Override
	public void saveRecords(DataStorage dataStorage) {
		List<Appointment> recordToSave = dataStorage.getAppointmentRecords().getFullAppointmentList();
		List<String[]> rowsToWrite = new ArrayList<>();
		
		//Create Header and add to rowsToWrite
		String[] cellsToWrite = createHeader();
		rowsToWrite.add(cellsToWrite);
		System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | "+ cellsToWrite[7] + " | ");
		
		//Populate Rows based on Available Medicine
		for(Appointment appointment : recordToSave) {
			cellsToWrite = createCells(appointment);
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
		System.out.println("Appointment Records saved successfully");
	}

	@Override
	public String[] createHeader() {
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Appointment ID";
		headerCells[1] = "Patient ID";
		headerCells[2] = "Doctor ID";
		headerCells[3] = "Appointment Status";
		headerCells[4] = "Appointment Date";
		headerCells[5] = "Appointment Time";
		headerCells[6] = "Appointment Type";
		headerCells[7] = "Prescribed Medications";
		headerCells[8] = "Consultation Notes";
		return headerCells;
	}

	@Override
	public String[] createCells(Appointment appointment) {
		String[] appointmentCells = new String[noOfHeaders];
		appointmentCells[0] = appointment.getAppointmentID();
		appointmentCells[1] = appointment.getPatientId();
		appointmentCells[2] = String.valueOf(appointment.getDoctorId());
		appointmentCells[3] = appointment.getStatus().toString();
		appointmentCells[4] = appointment.getAppointmentDate();
		appointmentCells[5] = appointment.getAppointmentTime();
		appointmentCells[6] = appointment.getAppointmentType();
		// Populating medications with quantity on separate lines
	    StringBuilder medicationsBuilder = new StringBuilder();
	    for (PrescribedMedication medication : appointment.getPrescribedMedications()) {
	        medicationsBuilder.append(medication.getMedicationName())
	                          .append(" - Quantity: ")
	                          .append(medication.getMedicineQuantity())
	                          .append("\n"); // New line for each medication
	    }
	    appointmentCells[7] = medicationsBuilder.toString();
		appointmentCells[8] = appointment.getConsultationNotes();
		return appointmentCells;
	}

}
