package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Medicine;

public class MedicineWriter implements DataWriter<Medicine>{
	private int noOfHeaders = 3; // Number of Headers in CSV
	private String csvFile = "dataFiles/Medicine_List.csv"; //File Path
	
	//Save Records to CSV
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
	
	//Generate header for Medicine Record
	@Override
	public String[] createHeader() {
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Medicine Name";
		headerCells[1] = "Available Stock";
		headerCells[2] = "Low Stock Alert";
		return headerCells;
	}
	
	//Generate cells for Medicine Object
	@Override
	public String[] createCells(Medicine medicine) {
		String[] medicineCells = new String[noOfHeaders];
		medicineCells[0] = medicine.getMedicineName();
		medicineCells[1] = String.valueOf(medicine.getMedicineStock());
		medicineCells[2] = String.valueOf(medicine.getMedicineStockAlert());
		return medicineCells;
	}
}
