package DataStorage;
import entities.User;

import inventoryManagement.Inventory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataStorage {
	public Inventory inventory;
	public List<User> patientRecords;
	
	public DataStorage() {
		this.inventory = new Inventory();
		this.patientRecords = new ArrayList<User>();
		initialStartUp();
	}
	
	private void initialStartUp() {
		//MedicineReader initStartUp = new MedicineReader(FilePath.MEDICINES.getPath());
		//initStartUp.populateInventory(this.inventory);
	}
	
	public void shutdownSave() throws FileNotFoundException, IOException {
		//MedicineWriter savingFiles = new MedicineWriter(FilePath.MEDICINES.getPath());
		//savingFiles.writeMedicine(this.inventory);
	}

	public Inventory getInventory() {
		return this.inventory;
	}
}
