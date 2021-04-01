
package seleniumbasics;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class iframe {
    public void frames(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
//       System.setProperty("webdriver.gecko.driver","D:\\selenium\\Browser_Driver\\geckodriver.exe");
//       WebDriver driver=new FirefoxDriver();
        driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_button_test");
        WebElement f=driver.findElement(By.id("iframeResult"));
        driver.switchTo().frame(f);
        driver.findElement(By.cssSelector("button[type='button'")).click();
    }
}
