package yahoomail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.eventusermodel.dummyrecord.LastCellOfRowDummyRecord;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test; 

public class sendmail {
	WebDriver driver;
  @Test(priority = 1)
  public void login() {
	  System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.get("https://login.yahoo.com/?.src=ym&.lang=en-IN&.intl=in&.done=https%3A%2F%2Fmail.yahoo.com%2Fd%3F.lang%3Den-IN");
	  driver.findElement(By.id("login-username")).sendKeys("slogeshbe");  
	  driver.findElement(By.id("login-signin")).click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.cssSelector("input#login-passwd")).sendKeys("*******");  // instead of '*' provide password
	  driver.findElement(By.id("login-signin")).click();
  } 
  @Test(priority = 2, dataProvider = "Maildetails",dataProviderClass = DataSet.class, dependsOnMethods = "login")
  public void sendingmail(String sendermail,String subject,String Message) throws IOException, Exception {
	  
	  WebElement compose=driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div[2]/div/div[1]/nav/div/div[1]/a"));
	  compose.click();
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.cssSelector("input#message-to-field")).sendKeys(sendermail);
	  driver.findElement(By.cssSelector("input[placeholder=\"Subject\"]")).sendKeys(subject);
	  driver.findElement(By.cssSelector("div.rte[role=\"textbox\"]")).sendKeys(Message);
	  driver.findElement(By.cssSelector("button.q_Z2aVTcY")).click();
	  Thread.sleep(1000);
	  
  	} 
  @Test(priority = 3)
  public void logout()
  {
	  driver.findElement(By.cssSelector("label.rapid-nonanchor-lt[data-ylk=\"slk:profile;elm:menu;elmt:user-info;sec:ybar;subsec:accounts;itc:1;\"]")).click();
	  driver.findElement(By.xpath("/html/body/header/div/div/div[2]/div/div[3]/div[1]/div/div/div/a[3]/span[2]")).click();
	  driver.close();
  }
  	
}
