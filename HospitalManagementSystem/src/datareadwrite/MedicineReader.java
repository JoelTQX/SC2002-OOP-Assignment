package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import entities.Medicine;
import datastorage.DataStorage;
/**
 * The {@code MedicineReader} class implements the {@link DataReader} interface to read and
 * load medicine data from a CSV file into the system's data storage.
 * <p>
 * This class is responsible for parsing the contents of a CSV file containing medicine information,
 * such as the name, initial stock, and stock alert levels. It then creates {@link Medicine} objects
 * and adds them to the system's inventory.
 * </p>
 * 
 * <p>
 * It reads the data from the {@code Medicine_List.csv} file, which is expected to have the following
 * format:
 * </p>
 * <pre>
 * MedicineName, InitialStock, StockAlert
 * </pre>
 * 
 * <p>
 * The {@code populateData} method processes each line in the CSV, creates a {@link Medicine} object
 * for each entry, and stores it in the {@link DataStorage} object's inventory.
 * </p>
 * 
 * @see DataStorage
 * @see Medicine
 * @see DataReader
 * 
 * 
 */
public class MedicineReader implements DataReader {
	
	String csvFile = "dataFiles/Medicine_List.csv";
    String line;
    /**
     * Reads the "Medicine_List.csv" and populates the system's data storage with medicine data from the CSV file.
     * This class will be called at system startup to create the objects based on the CSV file
     * <p>
     * This method reads the {@code Medicine_List.csv} file, parses each line, creates a new
     * {@link Medicine} object for each row in the CSV file, and adds it to the system's inventory.
     * </p>
     * 
     * @param dataStorage the {@link DataStorage} object where the medicine data will be stored
     */
	public void populateData(DataStorage dataStorage) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			 // Read Line to Skip Header
			 String header = reader.readLine();
			 while ((line = reader.readLine()) != null) {
	                String[] cells = line.split(",");
	                String medicineName = cells[0];
	                int initialStock=Integer.parseInt(cells[1]);
	                int stockAlert=Integer.parseInt(cells[2]);
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
	                
	                Medicine medicine = new Medicine(medicineName, initialStock, stockAlert);
	                dataStorage.getInventory().addMedicine(medicine);
			 }
		 }catch (Exception e) {
            e.printStackTrace();
		  }
	}
}

