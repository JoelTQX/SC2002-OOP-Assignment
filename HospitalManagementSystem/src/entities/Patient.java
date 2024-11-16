package entities;

import java.util.ArrayList;

public class Patient extends User{
	private String dob;
	private String bloodType;
	private String contactInfo;
	private String phoneNumber;
	private ArrayList<String> diagnoses;
	private ArrayList<String> treatment;
	
	public Patient(String userID, String userName,String userPass, boolean firstLogin,String dob,String bloodType,String contactInfo, String gender, String phoneNumber,ArrayList<String> diagnoses,ArrayList<String> treatments) {
		super(userID,userName, userPass, firstLogin,gender);
		this.dob=dob;
		this.bloodType=bloodType;
		this.contactInfo=contactInfo;
		this.phoneNumber=phoneNumber;
		this.diagnoses=diagnoses;
		this.treatment=treatments;
	}
	
	public String getPatientDOB() {
		return this.dob;
	}
	
	public String getPatientContactInfo() {
		return this.contactInfo;
	}
	public String getPatientContactNumber() {
		return this.phoneNumber;
	}

	public String getPatientBloodType() {
		// TODO Auto-generated method stub
		return this.bloodType;
	}
	
	public void setPatientContactInfo(String email) {
		this.contactInfo=(email);
	}
	
	public void setPatientContactNumber(String phoneNumber ) {
		this.phoneNumber=(phoneNumber);
	}
	
	public void addPatientdiagnoses(String diagnoses) {
		if (this.diagnoses.contains("NULL")) {
	        // Replace the first occurrence of "NULL" with the new diagnosis
	        int index = this.diagnoses.indexOf("NULL");
	        this.diagnoses.set(index, diagnoses);
	    } else {
	        // If the diagnosis is not "NULL", add it to the list
	        this.diagnoses.add(diagnoses);
	    }
	}

	
	public void addPatienttreatment(String treatment ) {
		if (this.treatment.contains("NULL")) {
	        // Replace the first occurrence of "NULL" with the new diagnosis
	        int index = this.treatment.indexOf("NULL");
	        this.treatment.set(index, treatment);
	    } else {
	        // If the diagnosis is not "NULL", add it to the list
	        this.treatment.add(treatment);
	    }
	}
	
	public String getPatientdiagnoses(){
		return String.join(";", this.diagnoses);
	}

	public String getPatienttreatment() {
		return String.join(";", this.treatment);
	}
}
