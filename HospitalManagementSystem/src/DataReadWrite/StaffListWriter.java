package DataReadWrite;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaffListWriter implements DataWriter {
	
	public void write(int x, int y,String message){
    	String csvFile = "C:/Users/jingj/Desktop/Code/SC2002-OOP-Assignment/Staff_List.csv";
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
}
