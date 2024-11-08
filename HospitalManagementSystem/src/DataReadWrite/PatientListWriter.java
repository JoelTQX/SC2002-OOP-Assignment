package DataReadWrite;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import entities.Patient;

public class PatientListWriter{
	
	public void write(int x, int y,String message){
		String csvFile = "dataFiles/Patient_List.csv";
        String line;

        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = reader.readLine()) != null) {
                rows.add(line.split(","));  // Split each row into columns
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (rows.size() <= x) {
            // Add a new empty row with a placeholder column (adjust if needed)
            rows.add(new String[] {""});
        }

        // Get the target row
        String[] targetRow = rows.get(x);

        // Ensure the row has enough columns (expand it if needed)
        if (y >= targetRow.length) {
            String[] expandedRow = new String[y + 1];
            System.arraycopy(targetRow, 0, expandedRow, 0, targetRow.length);
            for (int i = targetRow.length; i <= y; i++) {
                expandedRow[i] = "";  // Fill new cells with empty strings
            }
            rows.set(x, expandedRow);  // Update the row in the list
        }

        // Modify the specific cell
        rows.get(x)[y] = message;

        // Write the modified data back to the CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] row : rows) {
                writer.println(String.join(",", row));  // Join columns with commas
            }
            System.out.println("CSV updated successfully!");
        } 
        catch (IOException e) {
        e.printStackTrace();
        }
    }
	
	public void write(String userId, int y,String message){
		String csvFile = "dataFiles/Patient_List.csv";
        String line;
        int x = 0,rowNumber = 1;
        List<String[]> rows = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            while ((line = reader.readLine()) != null) {
                rows.add(line.split(","));  // Split each row into columns
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Get the target row
        
        
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			 String header = reader.readLine();
			 while ((line = reader.readLine()) != null) {
	                String[] cells = line.split(","); // Split the line by commas
	                String patientId = cells[0]; // Assuming the patient ID is in the first column

	                if (userId.equals(patientId)) { // Use .equals() for string comparison
	                    System.out.println("Patient ID found at row: " + rowNumber);
	                    // You can perform additional operations here, e.g., storing the row number
	                    break; // Exit the loop if found
	                }
	                rowNumber++; // Increment the row number for the next iteration
	            }
		 }catch (Exception e) {
           e.printStackTrace();
		  }

        // Modify the specific cell
        rows.get(rowNumber)[y] = message;

        // Write the modified data back to the CSV file
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            for (String[] row : rows) {
                writer.println(String.join(",", row));  // Join columns with commas
            }
            System.out.println("CSV updated successfully!");
        } 
        catch (IOException e) {
        e.printStackTrace();
        }
    }
	
}
