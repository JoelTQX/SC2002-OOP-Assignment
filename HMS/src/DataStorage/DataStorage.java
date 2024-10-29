package DataStorage;

import inventoryManagement.Inventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import helper.MedicineReader;
import helper.PatientListReader;
import entities.User;

public class DataStorage {
	public Inventory inventory;
	public List<User> patientRecords;
	
	public DataStorage() {
		this.inventory = new Inventory();
		this.patientRecords = new ArrayList<User>();
		initialStartUp();
	}
	
	
	private void initialStartUp() {
		MedicineReader initStartUp = new MedicineReader();
		PatientListReader patientStartUp = new PatientListReader();
		initStartUp.populateData(this);
		patientStartUp.populateData(this);
	}
	
	public void addPatient(User patient) {
		this.patientRecords.add(patient);
	}
	
	public void shutdownSave() throws FileNotFoundException, IOException {
		//MedicineWriter savingFiles = new MedicineWriter(FilePath.MEDICINES.getPath());
		//savingFiles.writeMedicine(this.inventory);
	}

	public Inventory getInventory() {
		return this.inventory;
	}
}