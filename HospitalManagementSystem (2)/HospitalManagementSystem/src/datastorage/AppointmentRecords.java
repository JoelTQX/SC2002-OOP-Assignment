package datastorage;

import java.util.ArrayList;
import java.util.List;

import entities.Appointment;

public class AppointmentRecords {
	static int lastAppointmentID = 0;
	private List<Appointment> AppointmentRecords;
	
	public AppointmentRecords() {
		AppointmentRecords = new ArrayList<Appointment>();
	}
	
	public Appointment getAppointmentByID(String appointmentID) {
		for(Appointment appointment : AppointmentRecords) {
			if(appointment.getAppointmentID().equals(appointmentID)) {
				return appointment;
			}
		}
		return null; //Patient Not Found
	}
	
	public int getLastAppointmentID() {
		return lastAppointmentID;
	}
	
	public void addAppointment(Appointment appointment) {
		AppointmentRecords.add(appointment);
		lastAppointmentID++;
	}
	
	public void removeAppointment(Appointment appointment) {
		AppointmentRecords.remove(appointment);
	}
}
