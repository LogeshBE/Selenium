
package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Javascriptexecutor {
    public void fun1() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.get("https://login.yahoo.com/?.intl=in&.lang=en-IN&src=ym&activity=uh-mail&pspid=97684142&done=https%3A%2F%2Fmail.yahoo.com%2Fd%3F.intl%3Din%26.lang%3Den-IN%26.partner%3Dnone%26.src%3Dfp%26activity%3Duh-mail%26pspid%3D97684142&add=1");
//        WebDriverWait wait=new WebDriverWait(driver, 10);
//	  WebElement checkbox=wait.until(ExpectedConditions.elementToBeClickable(By.id("persistent")));
//          checkbox.click();
        JavascriptExecutor js= (JavascriptExecutor) driver;
        WebElement checkbox=driver.findElement(By.id("persistent"));
        js.executeScript("arguments[0].click()", checkbox);
        js.executeScript("alert('checkbox is clicked')");
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
        String userid="slogeshbe";
          WebElement email=driver.findElement(By.cssSelector("input#login-username"));
        js.executeScript("arguments[0].value='"+userid+"'",email);
       driver.navigate().to("https://www.javatpoint.com/javascript-tutorial");
       js.executeScript("window.scrollTo(0,500)");
       
       
        
            
        
    }
}
