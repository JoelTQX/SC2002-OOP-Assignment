package hospital_management_system;

import java.io.IOException;

public interface DataWriter {
	public void write(int x, int y,String message) throws IOException;
}
