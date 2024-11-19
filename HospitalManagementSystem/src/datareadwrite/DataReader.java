package datareadwrite;

import java.io.IOException;
import datastorage.DataStorage;
/**
 * Interface {@code DataReader} provides a contract for classes that implement this interface.
 * Classes that implement {@code DataReader} must provide implementation for {@link #populateData(DataStorage dataStorage)} method.
 * Classes implementing this interface must provide implementations for the following methods:
 * 
 * <p>
 * Classes implementing this interface must provide implementations for the following methods:
 * </p>
 * <ul>
 *
 *     <li>{@link #populateData(DataStorage)} - Create objects using the data in CSV file and upon startup.</li>
 * </ul>
 * 
 */
public interface DataReader {
	/**
     * Populates the given {@link datastorage.DataStorage} instance of objects.
     *
     * <p>
     * Implementing classes should provide logic to read data from respective CSV 
     *  
     * {@link datastorage.DataStorage} object with the retrieved data.
     * </p>
     *
     * @param dataStorage the {@code DataStorage} instance to populate with data
     * @throws IOException if an I/O error occurs during data reading
     */
	public void populateData(DataStorage dataStorage) throws IOException;

}
