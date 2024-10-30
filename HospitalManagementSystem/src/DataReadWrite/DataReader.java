package DataReadWrite;

import java.io.IOException;
import datastorage.DataStorage;

public interface DataReader {
	
	public void populateData(DataStorage dataStorage) throws IOException;

}
