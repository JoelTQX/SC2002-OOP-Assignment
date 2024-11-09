package entities;

public class Patient extends User{
	private String dob;
	private String bloodType;
	private String contactInfo;
	
	
	
	
	public Patient(String userID, String userName,String userPass, boolean firstLogin,String dob,String bloodType,String contactInfo, String gender) {
		super(userID,userName, userPass, firstLogin,gender);
		this.dob=dob;
		this.bloodType=bloodType;
		this.contactInfo=contactInfo;
	}
	
	public String getPatientDOB() {
		return this.dob;
	}
	
	public String getPatientContactInfo() {
		return this.contactInfo;
	}

	public String getPatientBloodType() {
		// TODO Auto-generated method stub
		return this.bloodType;
	}
	
	public void setPatientContactInfo(String email) {
		this.contactInfo=(email);
	}
}
