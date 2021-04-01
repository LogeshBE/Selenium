package Filedata;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Screenshot {
  @Test
  public void TakeScreenshot() throws IOException {
	  System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  ChromeDriver driver=new ChromeDriver();
	  
	  driver.get("https://en.wikipedia.org/wiki/Bird");
	  TakesScreenshot pic=(TakesScreenshot)driver;
	  File picfile=pic.getScreenshotAs(OutputType.FILE);
	  File Destnation=new File("D://pic.png");
	  FileUtils.copyFile(picfile,Destnation );
	  
  }
}
