package datastorage;
import entities.Appointment;
import entities.Medicine;
import entities.Patient;
import entities.Replenishment;
import entities.Staff;
import java.io.IOException;

import datareadwrite.AppointmentReader;
import datareadwrite.DataReader;
import datareadwrite.DataWriter;
import datareadwrite.MedicineReader;
import datareadwrite.MedicineWriter;
import datareadwrite.PatientListReader;
import datareadwrite.PatientWriter;
import datareadwrite.ReplenishmentReader;
import datareadwrite.ReplenishmentWriter;
import datareadwrite.AppointmentWriter;
import datareadwrite.StaffListReader;
import datareadwrite.StaffWriter;

/**
 * The `DataStorage` class handles the loading and saving of records for various entities
 * such as medicine, staff, patients, appointments, and replenishments. It provides methods
 * to read data from CSV files and save records back to the respective CSV files.
 */
public class DataStorage {
	private Inventory inventory;
	private StaffRecords staffRecords;
	private PatientRecords patientRecords;
	private AppointmentRecords appointmentRecords;
	private ReplenishmentRecords replenishmentRecords;
	
    /**
     * Constructs a new `DataStorage` object and initializes all records.
     */
	public DataStorage(){
		this.inventory = new Inventory();
		this.patientRecords = new PatientRecords();
		this.staffRecords = new StaffRecords();
		this.appointmentRecords = new AppointmentRecords();
		this.replenishmentRecords = new ReplenishmentRecords();
	}
	
    /**
     * Reads data from CSV files and populates the respective records for inventory,
     * staff, patients, appointments, and replenishments.
     *
     * @throws IOException If an error occurs during file reading.
     */
	public void readCSVs() throws IOException {
		DataReader medicineStartUp = new MedicineReader();
		DataReader patientStartUp = new PatientListReader();
		DataReader staffStartUp = new StaffListReader();
		DataReader replenishmentStartUp = new ReplenishmentReader();
		DataReader appointmentRecordsStartUp = new AppointmentReader();
		
		medicineStartUp.populateData(this);
		patientStartUp.populateData(this);
		staffStartUp.populateData(this);
		replenishmentStartUp.populateData(this);
		appointmentRecordsStartUp.populateData(this);
		System.out.println("Data Ready");
	}
	
    /**
     * Saves all the records back into their respective CSV files.
     */
	public void saveRecords(){
		DataWriter<Medicine> medicineWriter = new MedicineWriter();
		DataWriter<Patient> patientWriter = new PatientWriter();
		DataWriter<Staff> staffWrite = new StaffWriter();
		DataWriter<Replenishment> replenishmentWrite = new ReplenishmentWriter();
		DataWriter<Appointment> appointmentWrite = new AppointmentWriter();
		
		medicineWriter.saveRecords(this);
		patientWriter.saveRecords(this);
		staffWrite.saveRecords(this);
		replenishmentWrite.saveRecords(this);
		appointmentWrite.saveRecords(this);
		System.out.println("Records Saved");
	}
	
    /**
     * Returns the inventory of medicines.
     *
     * @return The inventory object containing medicine records.
     */
	public Inventory getInventory() {
		return this.inventory;
	}
	

    /**
     * Returns the records of staff members.
     *
     * @return The staff records.
     */
	public StaffRecords getStaffRecords() {
		return this.staffRecords;
	}
	
    /**
     * Returns the records of patients.
     *
     * @return The patient records.
     */
	public PatientRecords getPatientRecords() {
		return this.patientRecords;
	}

    /**
     * Returns the records of appointments.
     *
     * @return The appointment records.
     */
	public AppointmentRecords getAppointmentRecords() {
		return this.appointmentRecords;
	}

    /**
     * Returns the records of replenishment requests.
     *
     * @return The replenishment records.
     */
	public ReplenishmentRecords getReplenishmentRecords() {
		return this.replenishmentRecords;
	}
}
