import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataReader reading=new MedicineListReader();
		reading.read();
		DataReader readpatientlist=new PatientListReader();
		readpatientlist.read();
		DataWriter writing= new PatientListWriter();
		// (row, column, message)
		writing.write(5, 0, "");
		readpatientlist.read();
		
		
	}

}
