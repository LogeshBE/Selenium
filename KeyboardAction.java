
package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class KeyboardAction {
    public void fun() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://paraphrasing-tool.com/");
        WebElement source=driver.findElement(By.id("formNameLabelTextBefore"));
        WebElement des=driver.findElement(By.id("formNameLabelTextAfter"));
        Actions action=new Actions(driver);
        source.sendKeys("Logesh");
        action.keyDown(source,Keys.CONTROL).sendKeys("a").sendKeys("x").build().perform();
        Thread.sleep(2000);
        action.keyDown(des,Keys.CONTROL).sendKeys("v").build().perform();
        
    }
}
