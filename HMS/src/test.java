import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		DataWriter writing=new PatientListWriter("C:\\\\Users\\\\jingj\\\\Desktop\\\\Code\\\\SC2002-OOP-Assignment\\\\Patient_List.xlsx");
		// x to set the column(column 0 is at the left)
		//y to set row(row 0 is at the top)
		writing.write(7,2,"lol");
		DataReader reading=new PatientListReader("C:\\\\Users\\\\jingj\\\\Desktop\\\\Code\\\\SC2002-OOP-Assignment\\\\Patient_List.xlsx");
		reading.read();
		DataReader readmeds=new MedicineListReader("C:\\\\Users\\\\jingj\\\\Desktop\\\\Code\\\\SC2002-OOP-Assignment\\\\Medicine_List.xlsx");
		readmeds.read();
	}

}
