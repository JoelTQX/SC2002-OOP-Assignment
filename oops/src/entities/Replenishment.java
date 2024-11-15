package entities;

public class Replenishment {
	private String medicineName;
	private int replenishAmount;
	private Status status;
	
	public enum Status{
		PENDING, 	// 0
		APPROVED,	// 1
		DENIED		// 2
	}
	
	//Constructor for reading CSV
	public Replenishment(String medicineName, int replenishAmount, Status status) {
		this.medicineName = medicineName;
		this.replenishAmount = replenishAmount;
		this.status = status;
	}
	
	//Constructor for creating Replenishment Requests
	public Replenishment(String medicineName, int replenishAmount) {
		this.medicineName = medicineName;
		this.replenishAmount = replenishAmount;
		this.status = Replenishment.Status.PENDING;
	}
	
	public void setStatus(String status) {
		switch(status.toUpperCase()){
			case "APPROVED":
				this.status = Replenishment.Status.APPROVED;
				break;
			case "DENIED":
				this.status = Replenishment.Status.DENIED;
				break;
			case "PENDING":
				this.status = Replenishment.Status.PENDING;
				break;
			default:
				System.out.println("Error Setting Status");
				break;
		}
	}

	public String getMedicineName() {
		// TODO Auto-generated method stub
		return this.medicineName;
	}

	public int getQuantity() {
		// TODO Auto-generated method stub
		return this.replenishAmount;
	}

	public int getStatus() {
		// TODO Auto-generated method stub
		return this.status.ordinal();
	}
}
