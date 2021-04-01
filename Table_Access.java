package Test;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test1 {
//	@Test
	public void table() {
		System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
			ChromeOptions option=new ChromeOptions();
			option.setHeadless(true);
		  
		  ChromeDriver driver=new ChromeDriver(option);
		  driver.get("https://www.w3schools.com/html/html_tables.asp");
		 int row =  driver.findElements(By
				 .xpath("/html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr")).size();
		 System.out.println("row = "+row);
		 int col=  driver.findElements(By
				 .xpath("/html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr/th")).size();
		 System.out.println("col  = "+col);
		  for(int i=2;i<=row;System.out.println(),i++)
			  	for(int j=2;j<=col;j++)
			  		System.out.print(driver.findElement(
			  				By.xpath("/html/body/div[6]/div[1]/div[1]/div[3]/div/table/tbody/tr["+i+"]/td["+j+"]")).getText()+"  ");
	}
	@Test
	public void numberfield() {
		System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  
	  ChromeDriver driver=new ChromeDriver();
	  
	  driver.get("file:///C:/Users/logesh-pt4110/Documents/number.html?");
	  driver.findElement(By.xpath("/html/body/form/input[1]")).sendKeys("123");
	  driver.findElement(By.xpath("/html/body/form/input[2]")).click();
	  driver.close();
	
		
	}

}
