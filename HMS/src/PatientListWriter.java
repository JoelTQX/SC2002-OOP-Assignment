import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;  // Common classes for both .xls and .xlsx
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PatientListWriter {
	public static void main(String[] args) throws FileNotFoundException, IOException {
        try (FileInputStream file = new FileInputStream(new File("C:\\Users\\jingj\\Desktop\\Code\\SC2002-OOP-Assignment\\Patient_List.xlsx"))) {
        	   // Create Workbook instance for .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get the first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Row row = sheet.getRow(0);
            if (row == null) {
                row = sheet.createRow(0);  // Create the row if it doesn't exist
            }
            Cell cell = row.getCell(7);
            if (cell == null) {
                cell = row.createCell(7);  // Create the cell if it doesn't exist
            }
            cell.setCellValue("joel is a cunt");
            
            try(FileOutputStream fileout= new FileOutputStream(new File("C:\\Users\\jingj\\Desktop\\Code\\SC2002-OOP-Assignment\\Patient_List.xlsx"))){
            	workbook.write(fileout);
            }
        }
	}
	}
