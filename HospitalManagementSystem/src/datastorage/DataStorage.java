package datastorage;
import DataReadWrite.StaffListReader;
import DataReadWrite.StaffWriter;
import entities.Medicine;
import entities.Replenishment;

import java.io.IOException;
import java.util.List;

import DataReadWrite.DataReader;
import DataReadWrite.MedicineReader;
import DataReadWrite.MedicineWriter;
import DataReadWrite.PatientListReader;
import DataReadWrite.PatientWriter;
public class DataStorage {
	private Inventory inventory;
	private StaffRecords staffRecords;
	private PatientRecords patientRecords;
	private AppointmentRecords appointmentRecords;
	private ReplenishmentRecords replenishmentRecords;
	
	public DataStorage() throws IOException {
		this.inventory = new Inventory();
		this.patientRecords = new PatientRecords();
		this.staffRecords = new StaffRecords();
		this.appointmentRecords = new AppointmentRecords();
		this.replenishmentRecords = new ReplenishmentRecords();
		initialStartUp();
	}
	
	//Read CSV files and load data into respective records
	public void readCSVs() throws IOException {
		DataReader medicineReader = new MedicineReader();
		DataReader patientStartUp = new PatientListReader();
		DataReader staffStartUp = new StaffListReader();
		
		medicineReader.populateData(this);
		patientStartUp.populateData(this);
		staffStartUp.populateData(this);

		System.out.println("Data Ready");
	}
	
	//Saves all records Into respective CSV files
	public void saveRecords(){
		MedicineWriter medicineWriter = new MedicineWriter();
		PatientWriter patientWriter = new PatientWriter();
		StaffWriter staffWrite = new StaffWriter();
		
		medicineWriter.saveRecords(this);
		patientWriter.saveRecords(this);
		staffWrite.saveRecords(this);
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
