package Filedata;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.annotations.Test;

import com.opencsv.CSVReader;

public class CSVfile {
  @Test
  public void readcsv() throws IOException {
	  CSVReader reader=new CSVReader(new FileReader("D:\\WPS Office\\name.csv"));
	  String data[];
	  while((data=reader.readNext())!= null) {
		  for(int i=0;i<data.length;i++)	System.out.print(data[i]+" ");
		  System.out.println();
	  }
  }
}
