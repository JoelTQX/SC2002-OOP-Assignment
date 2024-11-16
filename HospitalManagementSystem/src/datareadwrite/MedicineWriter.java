package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Appointment;
import entities.Medicine;
import entities.Patient;
/**
 * MedicineWriter class is used to write Medicine attributes to a CSV file.
 * Implements the {@link DataWriter} interface for the {@link Medicine} type.
 * The CSV file header will be populated by "Medicine Name", "Available Stock", "Low Stock Alert"
 * This class reads {@link Medicine} records from a {@link DataStorage} object and writes
 * them to a CSV file named {@code Medicine_List.csv}. It adds the data row by row, and 
 * each patient's attributes are separated into cells based on the defined headers.
 * </p>
 * 
 * @see DataWriter
 * @see Medicine
 * @see DataStorage
 * 
 */
public class MedicineWriter implements DataWriter<Medicine>{
	private int noOfHeaders = 3; // Number of Headers in CSV
	private String csvFile = "dataFiles/Medicine_List.csv"; //File Path
	
	/**
     * Write the list of Medicine from {@link DataStorage} to the CSV file row by row.
     * 
     * @param dataStorage the {@link DataStorage} object containing list of Medicine attribute
     */
	@Override
	public void saveRecords(DataStorage dataStorage) {
		List<Medicine> recordToSave = dataStorage.getInventory().getMedicineRecords();
		List<String[]> rowsToWrite = new ArrayList<>();
		
		//Create Header and add to rowsToWrite
		String[] cellsToWrite = createHeader();
		rowsToWrite.add(cellsToWrite);
		System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | ");
		
		//Populate Rows based on Available Medicine
		for(Medicine medicine : recordToSave) {
			cellsToWrite = createCells(medicine);
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
		System.out.println("Medicine Records saved successfully");
	}
	
	/**
     * Generates the header row for the Medicine CSV file.
     * 
     * @return a string array containing the column headers
     */
	@Override
	public String[] createHeader() {
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Medicine Name";
		headerCells[1] = "Available Stock";
		headerCells[2] = "Low Stock Alert";
		return headerCells;
	}
	
	/**
     * This class creates array of Medicine attributes to be saved. Example: MedicineCells[0]=MedicineStock of this Medicine.
     * 
     * @param Medicine the {@link Appointment} object containing Medicine attributes
     * @return a string array representing a single row of attributes for the CSV file separated into columns
     */
	@Override
	public String[] createCells(Medicine medicine) {
		String[] medicineCells = new String[noOfHeaders];
		medicineCells[0] = medicine.getMedicineName();
		medicineCells[1] = String.valueOf(medicine.getMedicineStock());
		medicineCells[2] = String.valueOf(medicine.getMedicineStockAlert());
		return medicineCells;
	}
}
