package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Medicine;

public class Inventory {
	private List<Medicine> medicineRecords;
	
	//Constructor
	public Inventory() {
		this.medicineRecords = new ArrayList<Medicine>();
	}
	
	//To add Medicine to Inventory
	public void addMedicine(Medicine medicine) {
		this.medicineRecords.add(medicine);
	}
	
	//To remove Medicine from Inventory
	public void removeMedicine(Medicine medicine) {
		this.medicineRecords.remove(medicine);
	}
	
	//Get Medicine by Medicine Name
	public Medicine getMedicineByName(String medicineName) {
		for( Medicine medicine : medicineRecords) {
			if(medicine.getMedicineName().equals(medicineName)) {
				return medicine;
			}
		}
		return null; //If Medicine does not exist in Inventory
	}
	
	//View Inventory
	public void viewInventory() {
		System.out.println("------ Inventory -------");
		for( Medicine medicine : medicineRecords) {
			System.out.printf("%d %-15s", medicineRecords.indexOf(medicine)+1, medicine.getMedicineName());
			System.out.printf("  |  Stock : %4d", medicine.getMedicineStock());
			System.out.printf("  |  Stock Alert : %d\n", medicine.getMedicineStockAlert());
		}
	}
	
	// Return MedicineRecords
	public List<Medicine> getMedicineRecords() {
		return this.medicineRecords;
	}
}
