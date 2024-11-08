package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Replenishment;

public class ReplenishmentRecords {
	private List<Replenishment> replenishmentRecords;
	
	public ReplenishmentRecords() {
		this.replenishmentRecords = new ArrayList<Replenishment>();
	}
	
	public List<Replenishment> getReplenishmentRecords(){
		return this.replenishmentRecords;
	}
	
	public void addReplenishment(Replenishment replenishment) {
		if(replenishment == null) {
			System.out.println("Error adding replenishment request...");
			return;
		}
		this.replenishmentRecords.add(replenishment);
	}
}
