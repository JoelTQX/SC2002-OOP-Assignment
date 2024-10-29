package inventoryManagement;

public class SimpleConsoleDisplay implements InventoryDisplay{
	private Inventory inventory;
	
	public SimpleConsoleDisplay(Inventory inventory){
		this.inventory = inventory;
	}
	
	@Override
	public void displayInventory() {
		// TODO Auto-generated method stub
		System.out.println("------------ Inventory ------------");
		for(int i = 0; i < inventory.getMedicines().size(); i++) {
			Medicine medicine = inventory.getMedicines().get(i);
			System.out.printf("%d %-15s", i+1, medicine.getMedicineName());
			System.out.println("  |  Stock : " + medicine.getMedicineStock());
		}
	}
}
