package controllers;

import datastorage.AppointmentRecords;
import datastorage.DataStorage;
import datastorage.Inventory;
import entities.Appointment;
import entities.Medicine;
import entities.Pharmacist;
import entities.Replenishment;
import entities.User;

public class PharmacistController {
	
	private User user;
	private DataStorage dataStorage;
	private AppointmentRecords appointmentRecords; 
	
	public PharmacistController(User user, DataStorage dataStorage) {
		this.user = (Pharmacist) user;
		this.dataStorage = dataStorage;
		this.appointmentRecords = dataStorage.getAppointmentRecords(); 
	}
	public Inventory getInventory() {
		return this.dataStorage.getInventory();
	}

	public void getOutcomeRecords() {
		
		
	}
// Update the status of a prescription for a specified medication in an appointment
public boolean updatePrescriptionStatus(String appointmentId, String medicationName) {
		// TODO Auto-generated method stub
		MedicineController medicineControl = new MedicineController(dataStorage.getInventory());
		Medicine medicineToAdjust = dataStorage.getInventory().getMedicineByName(medicineName);
		int quantityToDispense = 0;
		medicineControl.dispenseMedicine(medicineToAdjust, quantityToDispense);
		//Set Status to dispensed...
        Appointment appointment = appointmentRecords.getAppointmentByID(appointmentId);
        if (appointment != null && appointment.getStatus() == Appointment.AppointmentStatus.COMPLETED) {
            for (PrescribedMedication medication : appointmentcont.getPrescribedMedications()) {
                if (medication.getMedicationName().equalsIgnoreCase(medicationName)) {
                    medication.setStatus("Dispensed");
                    return true; // Successfully updated
                }
            }
        }
        return false; // Medicine not found or invalid appointment ID

	}

	
	//Method to create Replenishment Request
	public void createReplenishmentRequest(int medicineChoice, int medicineQuantity) {
		// TODO Auto-generated method stub
		String medicineName = dataStorage.getInventory().getMedicineRecords().get(medicineChoice).getMedicineName();
		Replenishment replenishment = new Replenishment( medicineName, medicineQuantity);
		
		ReplenishmentController replenishControl = new ReplenishmentController(dataStorage);
		replenishControl.addReplenishment(replenishment);
	}
	
	
}
