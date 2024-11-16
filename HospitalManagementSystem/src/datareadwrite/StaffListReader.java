package datareadwrite;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import datastorage.DataStorage;
import entities.Medicine;
import entities.Doctor;
import entities.Staff;
import entities.Pharmacist;
import entities.Administrator;
/**
 * {@code StaffListReader} is a class responsible for reading staff data from a CSV file
 * and populating it into the {@link DataStorage} object.
 * This class implements the {@link DataReader} interface for the purpose of reading staff data.
 * 
 * <p>
 * The CSV file is expected to have the following columns:
 * <ul>
 *   <li>Staff ID</li>
 *   <li>Password</li>
 *   <li>First Login (boolean)</li>
 *   <li>Name</li>
 *   <li>Role</li>
 *   <li>Gender</li>
 *   <li>Age</li>
 * </ul>
 * </p>
 * The class reads each row, processes the data, and instantiates the appropriate {@link Staff} subclass
 * (Doctor, Pharmacist, or Administrator) based on the staff ID prefix ('D', 'P', 'A'). The created objects
 * are then added to the {@link DataStorage}.
 * 
 * @see DataReader
 * @see DataStorage
 * @see Staff
 * @see Doctor
 * @see Pharmacist
 * @see Administrator
 */
public class StaffListReader implements DataReader {
	
	String csvFile = "dataFiles/Staff_List.csv";
    String line;
    /**
     * Reads staff data from the CSV file and populates the {@link DataStorage} object.
     * <p>
     * This method reads each line from the CSV file (skipping the header), splits the line into cells,
     * and extracts the staff ID, password, first login status, name, role, gender, and age for each staff member.
     * Based on the staff ID prefix ('D', 'P', or 'A'), a specific {@link Staff} subclass (Doctor, Pharmacist,
     * or Administrator) is instantiated. The created objects are added to the {@link DataStorage} staff records.
     * </p>
     * 
     * @param dataStorage the {@link DataStorage} object into which the staff data will be populated
     */
	public void populateData(DataStorage dataStorage) {
		 try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
			 String header = reader.readLine();
			 
			 while ((line = reader.readLine()) != null) {
	                String[] cells = line.split(",");
	                String staffid = cells[0];
	                String password = cells[1];
	                String name = cells[3];
	                String role = cells[4];
	                String gender = cells[5];
	                int age = Integer.parseInt(cells[6]);
	                boolean firstlogin = Boolean. parseBoolean(cells[2]);
	                if(staffid.charAt(0)=='D') {
	                	Staff doctor=new Doctor(staffid,name,password,firstlogin,role,gender,age);
	                	dataStorage.getStaffRecords().addStaff(doctor);
	                }
	                else if(staffid.charAt(0)=='P') {
	                	Staff pharmacist=new Pharmacist(staffid,name,password,firstlogin,role,gender,age);
	                	dataStorage.getStaffRecords().addStaff(pharmacist);
	                }
	                else if(staffid.charAt(0)=='A') {
	                	Staff admin=new Administrator(staffid,name,password,firstlogin,role,gender,age);
	                	dataStorage.getStaffRecords().addStaff(admin);
	                }
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
	                
			 }
		 }catch (Exception e) {
            e.printStackTrace();
		  }
	}
	
}


