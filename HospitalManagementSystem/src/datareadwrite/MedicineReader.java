package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import entities.Medicine;
import datastorage.DataStorage;

public class MedicineReader implements DataReader {
	
	String csvFile = "dataFiles/Medicine_List.csv";
    String line;
	
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

