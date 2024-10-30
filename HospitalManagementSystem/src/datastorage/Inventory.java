package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Medicine;
import controllers.MedicineController;

public class Inventory {
	private List<Medicine> inventory;
	private MedicineController medicineControl;
	
	//Constructor
	public Inventory() {
		this.inventory = new ArrayList<Medicine>();
		this.medicineControl = new MedicineController();
	}
	
	//To add Medicine to Inventory
	public void addMedicine(Medicine medicine) {
		this.inventory.add(medicine);
	}
	
	//To remove Medicine from Inventory
	public void removeMedicine(Medicine medicine) {
		this.inventory.remove(medicine);
	}
	
	//Get Medicine by Medicine Name
	public Medicine getMedicineByName(String medicineName) {
		for( Medicine medicine : inventory) {
			if(medicine.getMedicineName().equals(medicineName)) {
				return medicine;
			}
		}
		return null; //If Medicine does not exist in Inventory
	}
	
	//View Inventory
	public void viewInventory() {
		System.out.println("------ Inventory -------");
		for( Medicine medicine : inventory) {
			System.out.printf("%d %-15s", inventory.indexOf(medicine)+1, medicine.getMedicineName());
			System.out.printf("  |  Stock : %4d", medicine.getMedicineStock());
			System.out.printf("  |  Stock Alert : %d\n", medicine.getMedicineStockAlert());
		}
	}
}
