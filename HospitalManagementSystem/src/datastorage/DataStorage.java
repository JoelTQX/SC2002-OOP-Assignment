package datastorage;
import DataReadWrite.StaffListReader;
import entities.Medicine;

import java.io.IOException;

import DataReadWrite.DataReader;
import DataReadWrite.MedicineReader;
import DataReadWrite.MedicineWriter;
import DataReadWrite.PatientListReader;
public class DataStorage {
	private Inventory inventory;
	private StaffRecords staffRecords;
	private PatientRecords patientRecords;
	private AppointmentRecords appointmentRecords;
	
	public DataStorage() throws IOException {
		this.inventory = new Inventory();
		this.patientRecords = new PatientRecords();
		this.staffRecords = new StaffRecords();
		this.appointmentRecords = new AppointmentRecords();
		initialStartUp();
	}
	
	//Read CSV files and load data into respective records
	public void initialStartUp() throws IOException {
		DataReader medicineReader = new MedicineReader();
		DataReader patientStartUp = new PatientListReader();
		DataReader staffStartUp = new StaffListReader();
		
		medicineReader.populateData(this);
		patientStartUp.populateData(this);
		staffStartUp.populateData(this);

		System.out.println("Data Ready");
	}
	
	//Saves all records Into respective CSV files
	public void shutdownSave(){
		MedicineWriter medicineWriter = new MedicineWriter();
		
		medicineWriter.saveRecords(this);
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
}
