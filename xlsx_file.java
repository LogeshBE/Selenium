package Filedata;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class xlfile {
  @Test
  public void readxlfile() throws IOException {
	  FileInputStream file=new FileInputStream("D:\\WPS Office\\Book1.xlsx");
	  XSSFWorkbook workbook=new XSSFWorkbook(file);
	  XSSFSheet sheet=workbook.getSheetAt(0);
	  int lastrow=sheet.getLastRowNum();
	  int lastcell=sheet.getRow(0).getLastCellNum();
	  for(int i=0;i<lastrow;i++,System.out.println()) {
//			  	System.out.print(sheet.getRow(i).getCell(j).getStringCellValue()+"  ");
		  System.out.print(sheet.getRow(i).getCell(0).getStringCellValue()+" ");
		  System.out.print((int)sheet.getRow(i).getCell(1).getNumericCellValue());
	  }
  }
//  @Test
  public void writexlfile() throws IOException {
	  FileOutputStream file=new FileOutputStream("D:\\WPS Office\\Book1.xlsx");
	  XSSFWorkbook workbook=new XSSFWorkbook();
	  XSSFSheet sheet=workbook.createSheet("Demo");
	  int data=150;
	  for(int i=0;i<5;i++) {
		  XSSFRow row=sheet.createRow(i);
		  	row.createCell(0).setCellValue("Logesh");
		  	row.createCell(1).setCellValue(data);
		  	data++;
	  }
	  workbook.write(file);
  }
}
