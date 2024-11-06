package entities;

public class Medicine {
	private String name;
	private int stock, stockAlert;
	
	//Constructors
	public Medicine() {
		this("N/A", 0, 0);
	}
	public Medicine(String name, int stock, int stockAlert) {
		this.name = name;
		this.stock = stock;
		this.stockAlert = stockAlert;
	}
	
	//Public Methods
	public String getMedicineName() {
		return this.name;
	}
	public int getMedicineStock() {
		return this.stock;
	}
	public int getMedicineStockAlert() {
		return this.stockAlert;
	}
	public boolean isLowStock() {
		return this.stock < this.stockAlert;
	}
	
	public void setMedicineName(String name) {
		this.name = name;
	}
	public void setMedicineStock(int stock) {
		this.stock = stock;
		if(this.isLowStock()) {
			System.out.println(this.name + " has dropped below " + this.stockAlert);
			System.out.println("Replenishment is required...");
		}
	}
	public void setMedicineStockAlert(int stockAlert) {
		this.stockAlert = stockAlert;
	}
	
	
}
