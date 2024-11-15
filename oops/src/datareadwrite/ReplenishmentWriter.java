package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Replenishment;

public class ReplenishmentWriter implements DataWriter<Replenishment>{
	private String csvFile = "dataFiles/Replenishment_List.csv"; //File Path
	private int noOfHeaders = 3; // Number of Headers in CSV
	
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

	@Override
	public String[] createHeader() {
		// TODO Auto-generated method stub
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Medicine To Replenish";
		headerCells[1] = "Replenishment Quantity";
		headerCells[2] = "Replenishment Status";
		return headerCells;
	}

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
