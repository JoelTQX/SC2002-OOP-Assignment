package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import datastorage.DataStorage;
import entities.Replenishment;
import entities.Replenishment.Status;

public class ReplenishmentReader implements DataReader{
	
	String csvFile = "dataFiles/Replenishment_List.csv";
    String line;
	
	@Override
	public void populateData(DataStorage dataStorage) throws IOException {
		// TODO Auto-generated method stub
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			//Skip Header
			String header = reader.readLine();
			while ((line = reader.readLine()) != null) {
			        String[] cells = line.split(",");
			        
			String medicineName = cells[0];
			int replenishQuantity = Integer.parseInt(cells[1]);
			Replenishment.Status status = Replenishment.Status.valueOf(cells[2]);
			System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
            
            Replenishment replenishment = new Replenishment(medicineName, replenishQuantity, status);
            dataStorage.getReplenishmentRecords().addReplenishment(replenishment);
			}
		 }catch (Exception e) {
		       e.printStackTrace();	  
		 }
	}
}
