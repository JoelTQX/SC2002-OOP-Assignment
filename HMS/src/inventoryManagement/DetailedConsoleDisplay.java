package inventoryManagement;

public class DetailedConsoleDisplay implements InventoryDisplay{
	private Inventory inventory;
		
		public DetailedConsoleDisplay(Inventory inventory){
			this.inventory = inventory;
		}
		
		@Override
		public void displayInventory() {
			// TODO Auto-generated method stub
			System.out.println("------------ Inventory ------------");
			for(int i = 0; i < inventory.getMedicines().size(); i++) {
				Medicine medicine = inventory.getMedicines().get(i);
				System.out.printf("%d %-15s", i+1, medicine.getMedicineName());
				System.out.printf("  |  Stock : %4d", medicine.getMedicineStock());
				System.out.printf("  |  Stock Alert : %d\n", medicine.getMedicineStockAlert());
			}
		}
}
