package Waits;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

public class FluentWait {
	WebDriver driver;
  @Test
  public void fluent() {
	  System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  WebDriver driver=new ChromeDriver();

	  driver.manage().window().maximize();
	  driver.get("https://login.yahoo.com/?.src=ym&.lang=en-IN&.intl=in&.done=https%3A%2F%2Fmail.yahoo.com%2Fd%3F.lang%3Den-IN");
	  driver.findElement(By.id("login-username")).sendKeys("slogeshbe");
	  driver.findElement(By.id("login-signin")).click();
	  Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)							
				.withTimeout(30, TimeUnit.SECONDS) 			
				.pollingEvery(5, TimeUnit.SECONDS) 			
				.ignoring(NoSuchElementException.class);

	 WebElement pass=wait.until(new Function<WebDriver, WebElement>(){
             public WebElement apply(WebDriver driver){
                 return driver.findElement(By.cssSelector("input#login-passwd"));
             }
         }    
         );
          pass.sendKeys("ABC");
  }
}
