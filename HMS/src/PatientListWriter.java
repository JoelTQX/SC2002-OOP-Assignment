import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;  // Common classes for both .xls and .xlsx
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PatientListWriter implements DataWriter {
	
	private String filePath;
	
	
	public PatientListWriter(String filePath) {
        this.filePath = filePath;
    }
	
	public void write(int x,int y,String message) throws FileNotFoundException, IOException {
        try (FileInputStream file = new FileInputStream(new File(this.filePath))) {
        	   // Create Workbook instance for .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get the first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Row row = sheet.getRow(y);
            if (row == null) {
                row = sheet.createRow(y);  // Create the row if it doesn't exist
            }
            Cell cell = row.getCell(x);
            if (cell == null) {
                cell = row.createCell(x);  // Create the cell if it doesn't exist
            }
            cell.setCellValue(message);
            
            try(FileOutputStream fileout= new FileOutputStream(new File("C:\\Users\\jingj\\Desktop\\Code\\SC2002-OOP-Assignment\\Patient_List.xlsx"))){
            	workbook.write(fileout);
            } 
            file.close();
            workbook.close();
        }}
        
	}
