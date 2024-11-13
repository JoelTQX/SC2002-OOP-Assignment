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
import entities.Pharmacist;
import entities.Staff;

public class AppointmentReader implements DataReader{
	String csvFile = "dataFiles/AppointmentList.csv";
    String line;
	
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
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]+ ", " + cells[3]+ ", " + cells[4]+ ", " + cells[5]+ ", " + cells[6]+ ", " + cells[7]+ ", " + cells[8]);
	                }
			 		
	                
			 
		 }catch (Exception e) {
	            e.printStackTrace();
		 }
		 }



		 private List<PrescribedMedication> parseMedications(String prescribedMedications) {
		        List<PrescribedMedication> medications = new ArrayList<>();
		        
		        // Split the input string by newline to get individual medications
		        String[] medicationEntries = prescribedMedications.split("||");

		        for (String entry : medicationEntries) {
		            // Split each entry to get the medication name and quantity
		            String[] parts = entry.split("-quantity:");
		            if (parts.length == 2) {
		                String name = parts[0].trim();
		                int quantity = Integer.parseInt(parts[1].trim());
		                
		                // Create a new PrescribedMedication object and add to the list
		                PrescribedMedication medication = new PrescribedMedication(name, quantity);
		                medications.add(medication);
		            }
		        }

		        return medications;
		    }
}


	

