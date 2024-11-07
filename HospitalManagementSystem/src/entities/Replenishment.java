package entities;

public class Replenishment {
	private String medicineName;
	private int replenishAmount;
	private Status status;
	
	private enum Status{
		APPROVED,
		DENIED,
		PENDING
	}
	
	public Replenishment(String medicineName, int replenishAmount, Status status) {
		this.medicineName = medicineName;
		this.replenishAmount = replenishAmount;
		this.status = status;
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
}
