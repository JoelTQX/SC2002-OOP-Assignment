package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Replenishment;

/**
 * Manages the collection of replenishment records within the system.
 * Provides methods to add replenishment requests and retrieve all replenishment records.
 */
public class ReplenishmentRecords {
    /**
     * A list that stores the replenishment records.
     */
	private List<Replenishment> replenishmentRecords;
	
    /**
     * Constructs a `ReplenishmentRecords` instance, initializing an empty list of replenishment records.
     */
	public ReplenishmentRecords() {
		this.replenishmentRecords = new ArrayList<Replenishment>();
	}
	
    /**
     * Retrieves the list of all replenishment records.
     * 
     * @return A list of all `Replenishment` records.
     */
	public List<Replenishment> getReplenishmentRecords(){
		return this.replenishmentRecords;
	}
	
    /**
     * Adds a replenishment request to the records.
     * If the replenishment is `null`, an error message is printed.
     * 
     * @param replenishment The `Replenishment` request to add to the records.
     */
	public void addReplenishment(Replenishment replenishment) {
		if(replenishment == null) {
			System.out.println("Error adding replenishment request...");
			return;
		}
		this.replenishmentRecords.add(replenishment);
	}
}
