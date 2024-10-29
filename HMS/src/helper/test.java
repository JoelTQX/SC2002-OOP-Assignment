package helper;



import java.io.FileNotFoundException;
import java.io.IOException;
import DataStorage.DataStorage;
import inventoryManagement.InventoryDisplay;
import inventoryManagement.SimpleConsoleDisplay;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataStorage dataStorage = new DataStorage();
		InventoryDisplay inventoryDisplay = new SimpleConsoleDisplay(dataStorage.inventory);
		inventoryDisplay.displayInventory();
		
		/*
		DataPrinter reading=new MedicineListPrinter();
		reading.print();
		DataPrinter readpatientlist=new PatientListPrinter();
		readpatientlist.print();
		DataWriter writing= new PatientListWriter();
		// (row, column, message)
		writing.write(1, 1, "");
		readpatientlist.print();
		*/
		
	}

}
