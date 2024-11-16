package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Appointment;
import entities.Patient;
import entities.Replenishment;
/**
 * ReplenishmentWriter class is used to write Replenishment attributes to a CSV file.
 * Implements the {@link DataWriter} interface for the {@link Replenishment} type.
 * The CSV file header will be populated by "Medicine To Replenish","Replenishment Quantity","Replenishment Status"
 * 
 * This class reads {@link Replenishment} records from a {@link DataStorage} object and writes
 * them to a CSV file named {@codeReplenishment_List.csv}. It adds the data row by row, and 
 * each Replenishment attributes are separated into cells based on the defined headers.
 * </p>
 * 
 * @see DataWriter
 * @see Replenishment
 * @see DataStorage
 */
public class ReplenishmentWriter implements DataWriter<Replenishment>{
	private String csvFile = "dataFiles/Replenishment_List.csv"; //File Path
	private int noOfHeaders = 3; // Number of Headers in CSV
	/**
     * Write the list of Replenishment from {@link DataStorage} to the CSV file row by row.
     * 
     * @param dataStorage the {@link DataStorage} object containing list of Replenishment attribute
     */
	@Override
	public void saveRecords(DataStorage dataStorage) {
		// TODO Auto-generated method stub
		List<Replenishment> recordToSave = dataStorage.getReplenishmentRecords().getReplenishmentRecords();
		List<String[]> rowsToWrite = new ArrayList<>();
		
		//Create Header and add to rowsToWrite
		String[] cellsToWrite = createHeader();
		rowsToWrite.add(cellsToWrite);
		System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | ");
		
		//Populate Rows based on Available Medicine
		for(Replenishment replenishment : recordToSave) {
			cellsToWrite = createCells(replenishment);
			rowsToWrite.add(cellsToWrite);
			System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | ");
		}
		
		//Try to access the csvFile
		try(BufferedReader reader = new BufferedReader(new FileReader(csvFile))){
			//Try to Write data to CSV File
			try(PrintWriter writer = new PrintWriter(new FileWriter(csvFile))){
				for(String[] currentRow : rowsToWrite) {
					writer.println(String.join(",", currentRow));
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Success Message
		System.out.println("Replenishment Records saved successfully");
		
	}
	/**
     * Generates the header row for the Replenishment CSV file.
     * 
     * @return a string array containing the column headers
     */
	@Override
	public String[] createHeader() {
		// TODO Auto-generated method stub
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Medicine To Replenish";
		headerCells[1] = "Replenishment Quantity";
		headerCells[2] = "Replenishment Status";
		return headerCells;
	}
	/**
     * This class creates array of Replenishment attributes to be saved. Example: rowCells[0]=MedicineName of this Replenishment.
     * 
     * @param Replenishment the {@link Appointment} object containing Replenishment attributes
     * @return a string array representing a single row of attributes for the CSV file separated into columns
     */
	@Override
	public String[] createCells(Replenishment replenish) {
		// TODO Auto-generated method stub
		String[] rowCells = new String[noOfHeaders];
		rowCells[0] = replenish.getMedicineName();
		rowCells[1] = String.valueOf(replenish.getQuantity());
		rowCells[2] = String.valueOf(replenish.getStatus());
		return rowCells;
	}

}
