package datareadwrite;

import java.io.IOException;
import datastorage.DataStorage;
/*
 * Interface {@code DataReader} provides a contract for classes that implement this interface.
 * Classes that implement {@code DataReader} must provide implementation for {@link #populateData()} method.
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
	
	public void populateData(DataStorage dataStorage) throws IOException;

}
