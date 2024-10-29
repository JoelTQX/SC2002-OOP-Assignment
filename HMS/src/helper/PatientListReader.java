package helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PatientListReader implements DataReader {
    public void read(){
    	String csvFile = "C:/Users/jingj/Desktop/Code/SC2002-OOP-Assignment/Patient_List.csv";
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // Optional: Read header line
            String header = reader.readLine();
            System.out.println(header);

            // Read the rest of the lines
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");  // Split each line into columns

                // Dynamically print all cells in the row
                for (int i = 0; i < cells.length; i++) {
                    System.out.print(cells[i]);  // Print the current cell
                    if (i < cells.length - 1) {
                        System.out.print(", ");  // Add comma between cells, except after the last cell
                    }
                }
                System.out.println();  // Move to the next line after printing all cells
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
