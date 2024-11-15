package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MedicineListPrinter implements DataPrinter {
	
	public void print(){
    	String csvFile = "C:/Users/jingj/Desktop/Code/SC2002-OOP-Assignment/Medicine_List.csv";
        String line;

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            // Optional: Read header line
            String header = reader.readLine();
            System.out.println(header);

            // Read the rest of the lines
            while ((line = reader.readLine()) != null) {
                String[] cells = line.split(",");
                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

