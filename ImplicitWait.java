package Waits;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ImplicitWait {
	WebDriver driver;
  @Test
  public void implicit() {
	  System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  driver=new ChromeDriver();

	  driver.manage().window().maximize();
	  driver.get("https://login.yahoo.com/?.src=ym&.lang=en-IN&.intl=in&.done=https%3A%2F%2Fmail.yahoo.com%2Fd%3F.lang%3Den-IN");
	  driver.findElement(By.id("login-username")).sendKeys("slogeshbe");
	  driver.findElement(By.id("login-signin")).click();
//	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.findElement(By.cssSelector("input#login-passwd")).sendKeys("ABC");
  }
}
