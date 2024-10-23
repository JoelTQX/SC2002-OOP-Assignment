
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.*;  // Common classes for both .xls and .xlsx
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PatientListReader implements DataReader {
	
	private String filePath;
	
	public PatientListReader(String filePath) {
        this.filePath = filePath;
    }
	
	public void read() throws FileNotFoundException, IOException {
        	try (FileInputStream file = new FileInputStream(new File(this.filePath))) {
        	
            // Create Workbook instance for .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Get the first sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            // Iterate through rows
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                // Iterate through all cells in the current row
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    // Check the type of each cell and print accordingly
                    switch (cell.getCellType()) {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + "\t");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + "\t");
                            break;
                        case BOOLEAN:
                            System.out.print(cell.getBooleanCellValue() + "\t");
                            break;
                        case FORMULA:
                            System.out.print(cell.getCellFormula() + "\t");
                            break;
                        default:
                            System.out.print("Unknown Value\t");
                    }
                }
                System.out.println();  // Print new line after each row
            }
           file.close();
           workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
