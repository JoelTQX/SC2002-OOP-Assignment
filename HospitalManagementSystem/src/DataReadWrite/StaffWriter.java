package DataReadWrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Patient;
import entities.Staff;

public class StaffWriter implements DataWriter<Staff>{
	private int noOfHeaders = 7; // Number of Headers in CSV
	private String csvFile = "dataFiles/Staff_List.csv"; //File Path
	@Override
	public void saveRecords(DataStorage dataStorage) {
		List<Staff> recordToSave = dataStorage.getStaffRecords().getStaffList();
		List<String[]> rowsToWrite = new ArrayList<>();
		
		//Create Header and add to rowsToWrite
		String[] cellsToWrite = createHeader();
		rowsToWrite.add(cellsToWrite);
		System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | ");
		
		//Populate Rows based on Available Medicine
		for(Staff staff : recordToSave) {
			cellsToWrite = createCells(staff);
			rowsToWrite.add(cellsToWrite);
			System.out.println(cellsToWrite[0] + " | " + cellsToWrite[1] + " | " + cellsToWrite[2] + " | "+ cellsToWrite[3] + " | "+ cellsToWrite[4] + " | "+ cellsToWrite[5] + " | "+ cellsToWrite[6] + " | ");
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
		System.out.println("Staff Records saved successfully");
		
	}

	@Override
	public String[] createHeader() {
		String[] headerCells = new String[noOfHeaders];
		headerCells[0] = "Staff ID";
		headerCells[1] = "Password";
		headerCells[2] = "firstlogin";
		headerCells[3] = "Name";
		headerCells[4] = "Role";
		headerCells[5] = "Gender";
		headerCells[6] = "Age";
		return headerCells;
	}

	@Override
	public String[] createCells(Staff staff) {
		String[] staffCells = new String[noOfHeaders];
		staffCells[0] = staff.getUserID();
		staffCells[1] = staff.getPassword();
		staffCells[2] = String.valueOf(staff.isFirstLogin());
		staffCells[3] = staff.getUserName();
		staffCells[4] = staff.getRole();
		staffCells[5] = staff.getUserGender();
		staffCells[6] = String.valueOf(staff.getAge());
		return staffCells;

	}
	

}
