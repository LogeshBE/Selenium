package Waits;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ExplicitWait {
	WebDriver driver;
  @Test
  public void Explicit() {
	  System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  driver=new ChromeDriver();

	  driver.manage().window().maximize();
	  driver.get("https://login.yahoo.com/?.src=ym&.lang=en-IN&.intl=in&.done=https%3A%2F%2Fmail.yahoo.com%2Fd%3F.lang%3Den-IN");
	  driver.findElement(By.id("login-username")).sendKeys("slogeshbe");
	  driver.findElement(By.id("login-signin")).click();
	  WebDriverWait wait=new WebDriverWait(driver, 10);
	  WebElement pass=wait.until(ExpectedConditions.
			 presenceOfElementLocated(By.cssSelector("input#login-passwd"))) ;
	  pass.sendKeys("ABC");
  }
}
