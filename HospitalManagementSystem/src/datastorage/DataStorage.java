package datastorage;
import DataReadWrite.StaffListReader;

import java.io.IOException;

import DataReadWrite.DataReader;
import DataReadWrite.PatientListReader;
public class DataStorage {
	private Inventory inventory;
	private StaffRecords staffRecords;
	private PatientRecords patientRecords;
	
	public DataStorage() throws IOException {
		this.inventory = new Inventory();
		this.patientRecords = new PatientRecords();
		this.staffRecords = new StaffRecords();
		initialStartUp();
	}
	
	public void initialStartUp() throws IOException {
		//initStartUp.populateInventory(this.inventory);
		DataReader patientStartUp = new PatientListReader();
		DataReader staffStartUp = new StaffListReader();
		
		patientStartUp.populateData(this);
		staffStartUp.populateData(this);

		System.out.println("Data Ready");
	}
	
	public void shutdownSave(){
	}

	public Inventory getInventory() {
		return this.inventory;
	}
	
	public StaffRecords getStaffRecords() {
		return this.staffRecords;
	}
	
	public PatientRecords getPatientRecords() {
		return this.patientRecords;
	}
}
