package datareadwrite;

import datastorage.DataStorage;
/**
 * The {@code DataWriter} interface provides a contract for classes that handle writing data
 * to CSV files to provide persistent storage. The class provides implementation for  methods that formats object attributes
 * into a String arrays and then writing it into CSV file 
 * 
 * <p>
 * Classes implementing this interface must provide implementations for the following methods:
 * </p>
 * <ul>
 *     <li>{@link #saveRecords(DataStorage)} - Writes all object of a class in dataStorage into CSV file.</li>
 *     <li>{@link #createHeader()} - Generates the header row for the CSV file of class type.</li>
 *     <li>{@link #createCells(Object)} - Formats an individual object's attribute into an array of strings for output.</li>
 * </ul>
 *
 * @param <T> the type of object that this {@code DataWriter} handles, such as an Appointment or Patient ..etc
 */
public interface DataWriter<T> {
	/**
     * Write the records within {@code DataStorage} into CSV file, CSV file path and dataStorage attribute can be defined.
     * 
     * @param dataStorage the data storage object containing the records to be saved
     */
	public void saveRecords(DataStorage dataStorage);
	/**
     * Creates the header row for the data output.
     * 
     * @return an array of strings representing the header columns
     */
	abstract String[] createHeader();
	 /**
     * Formats a given object of type {@code T} into an array of strings for output to easier populate CSV.
     * 
     * @param object the object to be formatted into cells
     * @return an array of strings representing the object's attributes as cells
     */
	abstract String[] createCells(T object);
}
