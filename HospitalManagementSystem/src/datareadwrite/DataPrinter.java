package datareadwrite;

import java.io.IOException;
/**
 * The {@code DataPrinter} interface provides a contract for classes to 
 * implement data writing functionality. Implementing classes must define 
 * the {@link #print()} method to print data, typically to an output medium 
 * such as a console, file, or network stream.
 *
 * <p>
 * This interface is designed for scenarios where data needs to be printed 
 * with possible I/O operations that might throw exceptions.
 * </p>
 *
 *
 */
public interface DataPrinter {
	/**
     * Prints data to the respective CSV file.
     *
     * <p>
     * Implementing classes should provide the logic for writing data the corresponds to the objects 
     * This method may throw an {@link IOException} 
     * if an I/O error occurs during the operation.
     * </p>
     *
     * @throws IOException if an I/O error occurs during the printing process
     */
	public void print() throws IOException;

}
