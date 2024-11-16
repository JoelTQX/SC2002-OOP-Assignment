package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import datastorage.DataStorage;
import entities.Replenishment;
/**
 * {@code ReplenishmentReader} is a class responsible for reading replenishment data from a CSV file
 * and populating it into the {@link DataStorage} object.
 * This class implements the {@link DataReader} interface to facilitate reading replenishment data.
 * 
 * <p>
 * The CSV file is expected to have the following columns:
 * <ul>
 *   <li>Medicine Name</li>
 *   <li>Replenish Quantity</li>
 *   <li>Status</li>
 * </ul>
 * The class reads the data, processes it, and stores it in the {@link DataStorage}.
 * </p>
 * 
 * @see DataReader
 * @see DataStorage
 * @see Replenishment
 * 
 */
public class ReplenishmentReader implements DataReader{
	
	String csvFile = "dataFiles/Replenishment_List.csv";
    String line;
    /**
     * Reads replenishment data from the CSV file and populates the {@link DataStorage} object.
     * <p>
     * This method reads each line from the CSV file (skipping the header), splits the line into cells,
     * and extracts the medicine name, replenish quantity, and status for each replenishment record.
     * The extracted data is then used to create a {@link Replenishment} object, which is added to the
     * {@link DataStorage}.
     * </p>
     * 
     * @param dataStorage the {@link DataStorage} object into which the replenishment data will be populated
     * @throws IOException if an I/O error occurs while reading the file
     */
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
