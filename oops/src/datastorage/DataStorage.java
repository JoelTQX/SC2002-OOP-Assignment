package datastorage;
import entities.Appointment;
import entities.Medicine;
import entities.Patient;
import entities.Replenishment;
import entities.Staff;
import java.io.IOException;

import datareadwrite.AppointmentReader;
import datareadwrite.DataReader;
import datareadwrite.DataWriter;
import datareadwrite.MedicineReader;
import datareadwrite.MedicineWriter;
import datareadwrite.PatientListReader;
import datareadwrite.PatientWriter;
import datareadwrite.ReplenishmentReader;
import datareadwrite.ReplenishmentWriter;
import datareadwrite.AppointmentWriter;
import datareadwrite.StaffListReader;
import datareadwrite.StaffWriter;
public class DataStorage {
	private Inventory inventory;
	private StaffRecords staffRecords;
	private PatientRecords patientRecords;
	private AppointmentRecords appointmentRecords;
	private ReplenishmentRecords replenishmentRecords;
	
	public DataStorage(){
		this.inventory = new Inventory();
		this.patientRecords = new PatientRecords();
		this.staffRecords = new StaffRecords();
		this.appointmentRecords = new AppointmentRecords();
		this.replenishmentRecords = new ReplenishmentRecords();
	}
	
	//Read CSV files and load data into respective records
	public void readCSVs() throws IOException {
		DataReader medicineStartUp = new MedicineReader();
		DataReader patientStartUp = new PatientListReader();
		DataReader staffStartUp = new StaffListReader();
		DataReader replenishmentStartUp = new ReplenishmentReader();
		DataReader appointmentRecordsStartUp = new AppointmentReader();
		
		medicineStartUp.populateData(this);
		patientStartUp.populateData(this);
		staffStartUp.populateData(this);
		replenishmentStartUp.populateData(this);
		appointmentRecordsStartUp.populateData(this);
		System.out.println("Data Ready");
	}
	
	//Saves all records Into respective CSV files
	public void saveRecords(){
		DataWriter<Medicine> medicineWriter = new MedicineWriter();
		DataWriter<Patient> patientWriter = new PatientWriter();
		DataWriter<Staff> staffWrite = new StaffWriter();
		DataWriter<Replenishment> replenishmentWrite = new ReplenishmentWriter();
		DataWriter<Appointment> appointmentWrite = new AppointmentWriter();
		
		medicineWriter.saveRecords(this);
		patientWriter.saveRecords(this);
		staffWrite.saveRecords(this);
		replenishmentWrite.saveRecords(this);
		appointmentWrite.saveRecords(this);
		System.out.println("Records Saved");
	}
	
	//Getters
	public Inventory getInventory() {
		return this.inventory;
	}
	
	public StaffRecords getStaffRecords() {
		return this.staffRecords;
	}
	
	public PatientRecords getPatientRecords() {
		return this.patientRecords;
	}

	public AppointmentRecords getAppointmentRecords() {
		return this.appointmentRecords;
	}

	public ReplenishmentRecords getReplenishmentRecords() {
		return this.replenishmentRecords;
	}
}
