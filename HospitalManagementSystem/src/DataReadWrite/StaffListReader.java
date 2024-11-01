package DataReadWrite;

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

public class StaffListReader implements DataReader {
	
	String csvFile = "dataFiles/Staff_List.csv";
    String line;
	
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
	                boolean firstlogin = Boolean. parseBoolean(cells[2]);
	                if(staffid.charAt(0)=='D') {
	                	Staff doctor=new Doctor(staffid,name,password,firstlogin,role,gender);
	                	dataStorage.getStaffRecords().addStaff(doctor);
	                }
	                else if(staffid.charAt(0)=='P') {
	                	Staff pharmacist=new Pharmacist(staffid,name,password,firstlogin,role,gender);
	                	dataStorage.getStaffRecords().addStaff(pharmacist);
	                }
	                else if(staffid.charAt(0)=='a') {
	                	Staff admin=new Administrator(staffid,name,password,firstlogin,role,gender);
	                	dataStorage.getStaffRecords().addStaff(admin);
	                }
	                System.out.println( cells[0] + ", " + cells[1] + ", " + cells[2]);
	                
			 }
		 }catch (Exception e) {
            e.printStackTrace();
		  }
	}
	
}


