package entities;

/**
 * Represents a replenishment request for medicine in the hospital management system.
 * Tracks the medicine name, quantity to replenish, and the current status of the request.
 */
public class Replenishment {
    /**
     * Name of the medicine for replenishment.
     */
	private String medicineName;
	
    /**
     * Amount of medicine to replenish.
     */
	private int replenishAmount;
	
    /**
     * Current status of the replenishment request.
     */
	private Status status;
	
	
    /**
     * Enumeration for the possible statuses of a replenishment request.
     */
	public enum Status{
        /**
         * Replenishment request is pending approval.
         */
		PENDING, 	// 0
		
        /**
         * Replenishment request has been approved.
         */
		APPROVED,	// 1
		
        /**
         * Replenishment request has been denied.
         */
		DENIED		// 2
	}
	
    /**
     * Constructs a `Replenishment` object when reading from a data source like a CSV file.
     *
     * @param medicineName   The name of the medicine to replenish.
     * @param replenishAmount The amount of medicine to replenish.
     * @param status         The current status of the replenishment request.
     */
	public Replenishment(String medicineName, int replenishAmount, Status status) {
		this.medicineName = medicineName;
		this.replenishAmount = replenishAmount;
		this.status = status;
	}
	
    /**
     * Constructs a new `Replenishment` request with a default status of `PENDING`.
     *
     * @param medicineName   The name of the medicine to replenish.
     * @param replenishAmount The amount of medicine to replenish.
     */
	public Replenishment(String medicineName, int replenishAmount) {
		this.medicineName = medicineName;
		this.replenishAmount = replenishAmount;
		this.status = Replenishment.Status.PENDING;
	}
	
    /**
     * Updates the status of the replenishment request based on a string value.
     *
     * @param status A string representing the new status. Expected values are "APPROVED", "DENIED", or "PENDING".
     */
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

    /**
     * Gets the name of the medicine to be replenished.
     *
     * @return The name of the medicine.
     */
	public String getMedicineName() {
		// TODO Auto-generated method stub
		return this.medicineName;
	}

    /**
     * Gets the quantity of medicine to replenish.
     *
     * @return The amount to replenish.
     */
	public int getQuantity() {
		// TODO Auto-generated method stub
		return this.replenishAmount;
	}

    /**
     * Gets the current status of the replenishment request.
     *
     * @return The status as a string.
     */
	public String getStatus() {
		// TODO Auto-generated method stub
		//return this.status.ordinal();
		return this.status.toString();
	}
}
