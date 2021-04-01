
package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class alert {
    public void alert() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        
        driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert");
        driver.switchTo().frame(driver.findElement(By.id("iframeResult")));
        WebElement button=driver.findElement(By.cssSelector("button[onclick=\"myFunction()\"]"));
        button.click();
        String msg1 = driver.switchTo().alert().getText();
        System.out.println("Msg1 = "+msg1);
        driver.switchTo().alert().accept();
        
        driver.navigate().to("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
        driver.switchTo().frame(driver.findElement(By.id("iframeResult")));
        WebElement button2=driver.findElement(By.cssSelector("button[onclick=\"myFunction()\"]"));
        button2.click();
        driver.switchTo().alert().dismiss();
        String msg2 = driver.findElement(By.cssSelector("p#demo")).getText();
        System.out.println("Msg2 = "+msg2);
        
        driver.navigate().to("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
        driver.switchTo().frame(driver.findElement(By.id("iframeResult")));
        WebElement button3=driver.findElement(By.cssSelector("button[onclick=\"myFunction()\"]"));
        button3.click();
        driver.switchTo().alert().sendKeys("Logesh");
        driver.switchTo().alert().accept();
        String msg3 = driver.findElement(By.cssSelector("p#demo")).getText();
        System.out.println("Msg2 = "+msg3);
        
        driver.close();
        
        
    }
    
}
