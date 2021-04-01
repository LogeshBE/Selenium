package yahoomail;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataSet {
	
	@DataProvider
  	public String[][] Maildetails() throws IOException {
  		FileInputStream file=new FileInputStream("D:\\WPS Office\\Maildetails.xlsx");
  	   XSSFWorkbook workbook=new XSSFWorkbook(file);
  	   XSSFSheet sheet=workbook.getSheetAt(0);
  	   int lastrow=sheet.getLastRowNum();
  	   int lastcell=sheet.getRow(0).getLastCellNum();
  	   String arr[][]=new String[lastrow][lastcell];
  	   for(int i=0;i<lastrow;i++) {
  		   arr[i][0] = sheet.getRow(i).getCell(0).getStringCellValue();
  		   arr[i][1] = sheet.getRow(i).getCell(1).getStringCellValue();
  		   arr[i][2] = sheet.getRow(i).getCell(2).getStringCellValue();
  	   }
  	   return arr;
  	}
}
