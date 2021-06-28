
package fileupdate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author logesh-pt4110
 */
public class FileUpdate {

    
    public static void main(String[] args) throws IOException {
        update(2);
    }
    private static void update(int row) throws FileNotFoundException, IOException{
        FileInputStream file = new FileInputStream("D:\\WPS Office\\fileupdate.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell = null;
        XSSFRow sheetrow = sheet.getRow(row);
        if(sheetrow == null){
            sheetrow = sheet.createRow(row);
        }
        //Update the value of cell
        cell = sheetrow.getCell(1);
        if(cell == null){
            cell = sheetrow.createCell(1);
        }
        cell.setCellValue("Pass");

        file.close();


        FileOutputStream outFile =new FileOutputStream(new File("D:\\WPS Office\\fileupdate.xlsx"));
        workbook.write(outFile);
        outFile.close();
    }
    
}
