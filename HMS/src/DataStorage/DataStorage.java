package DataStorage;

import inventoryManagement.Inventory;

import java.io.FileNotFoundException;
import java.io.IOException;

import helper.MedicineReader;
//import helper.MedicineWriter;

public class DataStorage {
	public Inventory inventory;
	
	public DataStorage() {
		this.inventory = new Inventory();
		initialStartUp();
	}
	
	private void initialStartUp() {
		MedicineReader initStartUp = new MedicineReader();
		initStartUp.populateData(this);
	}
	
	public void shutdownSave() throws FileNotFoundException, IOException {
		//MedicineWriter savingFiles = new MedicineWriter(FilePath.MEDICINES.getPath());
		//savingFiles.writeMedicine(this.inventory);
	}

	public Inventory getInventory() {
		return this.inventory;
	}
}
