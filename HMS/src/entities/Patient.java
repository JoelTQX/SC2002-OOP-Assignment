package entities;

public class Patient extends User{
	private String bloodtype;
	private String contactinfomation;
	private String dob;
	//private Appointment appointment;
	// need to add apptment
	public Patient(String userID,String password, String name, String dob,String gender, String bloodtype, String contactinfomation) {
		super(userID,password, name, gender);
		this.bloodtype = bloodtype;
		this.contactinfomation = contactinfomation;
		this.dob = dob;
	}
}
