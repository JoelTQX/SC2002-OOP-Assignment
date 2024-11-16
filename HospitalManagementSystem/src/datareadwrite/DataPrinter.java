package datareadwrite;

import java.io.IOException;
/*
 * Interface {@code DataPrinter} provides a contract for classes that implement this interface.
 * Classes that implement {@code DataPrinter} must provide implementation for {@link #print()} method.
 */
public interface DataPrinter {
	
	public void print() throws IOException;

}
