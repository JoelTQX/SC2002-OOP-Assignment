package helper;

import java.io.IOException;
import DataStorage.DataStorage;

public interface DataReader {
	
	public void populateData(DataStorage dataStorage) throws IOException;

}
