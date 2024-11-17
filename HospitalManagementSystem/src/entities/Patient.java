package entities;

import java.util.ArrayList;

/**
 * Represents a patient in the hospital management system.
 * Stores personal information, medical history, and treatment records for a patient.
 */
public class Patient extends User{
	private String dob;
	private String bloodType;
	private String contactInfo;
	private String phoneNumber;
	private ArrayList<String> diagnoses;
	private ArrayList<String> treatment;
	
    /**
     * Constructs a {@code Patient} object with specified attributes.
     *
     * @param userID       The unique ID of the patient.
     * @param userName     The name of the patient.
     * @param userPass     The password for the patient's account.
     * @param firstLogin   Indicates if it is the patient's first login.
     * @param dob          The date of birth of the patient.
     * @param bloodType    The blood type of the patient.
     * @param contactInfo  The email address of the patient.
     * @param gender       The gender of the patient.
     * @param phoneNumber  The phone number of the patient.
     * @param diagnoses    The initial list of diagnoses for the patient.
     * @param treatments   The initial list of treatments for the patient.
     */
	public Patient(String userID, String userName,String userPass, boolean firstLogin,String dob,String bloodType,String contactInfo, String gender, String phoneNumber,ArrayList<String> diagnoses,ArrayList<String> treatments) {
		super(userID,userName, userPass, firstLogin,gender);
		this.dob=dob;
		this.bloodType=bloodType;
		this.contactInfo=contactInfo;
		this.phoneNumber=phoneNumber;
		this.diagnoses=diagnoses;
		this.treatment=treatments;
	}
	
    /**
     * Gets the patient's date of birth.
     *
     * @return The date of birth of the patient.
     */
	public String getPatientDOB() {
		return this.dob;
	}
	
    /**
     * Gets the patient's contact email.
     *
     * @return The email address of the patient.
     */
	public String getPatientContactInfo() {
		return this.contactInfo;
	}
	
    /**
     * Gets the patient's phone number.
     *
     * @return The phone number of the patient.
     */
	public String getPatientContactNumber() {
		return this.phoneNumber;
	}

    /**
     * Gets the patient's blood type.
     *
     * @return The blood type of the patient.
     */
	public String getPatientBloodType() {
		// TODO Auto-generated method stub
		return this.bloodType;
	}
	
    /**
     * Updates the patient's contact email.
     *
     * @param email The new email address for the patient.
     */
	public void setPatientContactInfo(String email) {
		this.contactInfo=(email);
	}
	
    /**
     * Updates the patient's phone number.
     *
     * @param phoneNumber The new phone number for the patient.
     */
	public void setPatientContactNumber(String phoneNumber ) {
		this.phoneNumber=(phoneNumber);
	}
	
    /**
     * Adds a new diagnosis to the patient's list of diagnoses.
     * If "NULL" exists in the list, it is replaced with the new diagnosis.
     *
     * @param diagnoses The diagnosis to add.
     */
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

    /**
     * Adds a new treatment to the patient's list of treatments.
     * If "NULL" exists in the list, it is replaced with the new treatment.
     *
     * @param treatment The treatment to add.
     */
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
	
    /**
     * Gets the patient's list of diagnoses as a semicolon-separated string.
     *
     * @return A string representation of the patient's diagnoses.
     */
	public String getPatientdiagnoses(){
		return String.join(";", this.diagnoses);
	}

    /**
     * Gets the patient's list of treatments as a semicolon-separated string.
     *
     * @return A string representation of the patient's treatments.
     */
	public String getPatienttreatment() {
		return String.join(";", this.treatment);
	}
}
