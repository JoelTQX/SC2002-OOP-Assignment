package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Medicine;

/**
 * The `Inventory` class represents a collection of medicines and provides methods
 * to manage the inventory, such as adding and removing medicines, viewing the inventory,
 * and retrieving specific medicines by name.
 */
public class Inventory {
    /**
     * A list of medicine records stored in the inventory.
     */
	private List<Medicine> medicineRecords;
	
    /**
     * Constructs an empty inventory.
     */
	public Inventory() {
		this.medicineRecords = new ArrayList<Medicine>();
	}
	
    /**
     * Adds a medicine to the inventory.
     *
     * @param medicine The medicine to be added to the inventory.
     */
	public void addMedicine(Medicine medicine) {
		this.medicineRecords.add(medicine);
	}
	
    /**
     * Removes a medicine from the inventory.
     *
     * @param medicine The medicine to be removed from the inventory.
     */
	public void removeMedicine(Medicine medicine) {
		this.medicineRecords.remove(medicine);
	}
	
    /**
     * Retrieves a medicine from the inventory by its name.
     *
     * @param medicineName The name of the medicine to retrieve.
     * @return The medicine object if found, or null if the medicine does not exist in the inventory.
     */
	public Medicine getMedicineByName(String medicineName) {
		for( Medicine medicine : medicineRecords) {
			if(medicine.getMedicineName().equals(medicineName)) {
				return medicine;
			}
		}
		return null; //If Medicine does not exist in Inventory
	}
	
    /**
     * Displays the list of all medicines in the inventory along with their stock information.
     */
	public void viewInventory() {
		System.out.println("------ Inventory -------");
		for( Medicine medicine : medicineRecords) {
			System.out.printf("%d %-15s", medicineRecords.indexOf(medicine)+1, medicine.getMedicineName());
			System.out.printf("  |  Stock : %4d", medicine.getMedicineStock());
			System.out.printf("  |  Stock Alert : %d\n", medicine.getMedicineStockAlert());
		}
	}
	
    /**
     * Returns the list of all medicines in the inventory.
     *
     * @return The list of medicine records.
     */
	public List<Medicine> getMedicineRecords() {
		return this.medicineRecords;
	}
}
