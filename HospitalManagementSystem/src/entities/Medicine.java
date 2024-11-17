package entities;

/**
 * Represents a medicine in the hospital management system.
 * Tracks the medicine's name, current stock, and stock alert level.
 */
public class Medicine {
	private String name;
	private int stock, stockAlert;
	
    /**
     * Default constructor for `Medicine`.
     * Initializes the medicine with default values ("N/A", 0, 0).
     */
	//Constructors
	public Medicine() {
		this("N/A", 0, 0);
	}
	

    /**
     * Constructs a {@code Medicine} object with specified values.
     *
     * @param name       The name of the medicine.
     * @param stock      The current stock of the medicine.
     * @param stockAlert The stock level at which an alert should be triggered.
     */
	public Medicine(String name, int stock, int stockAlert) {
		this.name = name;
		this.stock = stock;
		this.stockAlert = stockAlert;
	}
	
    /**
     * Gets the name of the medicine.
     *
     * @return The name of the medicine.
     */
	public String getMedicineName() {
		return this.name;
	}
	
    /**
     * Gets the current stock of the medicine.
     *
     * @return The current stock of the medicine.
     */
	public int getMedicineStock() {
		return this.stock;
	}
	
    /**
     * Gets the stock alert level of the medicine.
     *
     * @return The stock alert level.
     */
	public int getMedicineStockAlert() {
		return this.stockAlert;
	}
	
    /**
     * Checks if the medicine stock is low based on the stock alert level.
     *
     * @return {@code true} if the stock is less than or equal to the alert level; {@code false} otherwise.
     */
	public boolean isLowStock() {
		return this.stock <= this.stockAlert;
	}
	
    /**
     * Sets the name of the medicine.
     *
     * @param name The new name of the medicine.
     */
	public void setMedicineName(String name) {
		this.name = name;
	}
	
    /**
     * Updates the stock level of the medicine.
     * If the stock level drops below or equals the alert level, a warning message is displayed.
     *
     * @param stock The new stock level of the medicine.
     */
	public void setMedicineStock(int stock) {
		this.stock = stock;
		if(this.isLowStock()) {
			System.out.println(this.name + " has dropped below " + this.stockAlert);
			System.out.println("Replenishment is required...");
		}
	}
	
    /**
     * Sets the stock alert level for the medicine.
     *
     * @param stockAlert The new stock alert level.
     */
	public void setMedicineStockAlert(int stockAlert) {
		this.stockAlert = stockAlert;
	}
	
	
}
