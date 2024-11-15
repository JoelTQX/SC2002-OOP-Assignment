package entities;

public class PrescribedMedicine {
	private String medicineName;
	private int quantity;
	private boolean hasDispensed;
	
	public PrescribedMedicine(String medicineName, int quantity){
		this.medicineName = medicineName;
		this.quantity = quantity;
		this.hasDispensed = false;
	}
}
