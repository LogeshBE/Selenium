
package seleniumbasics;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import javax.swing.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class MouseAction {
    public void fun() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/js/js_popup.asp");
        WebElement target1=driver.findElement(By.xpath("//a[@id=\"topnavbtn_tutorials\"]"));
        Actions actions=new Actions(driver);
        actions.click(target1).build().perform();
        WebElement target2=driver.findElement(By.linkText("Learn Bootstrap"));
        actions.moveToElement(target2).click().build().perform();
        Thread.sleep(2000); 
        
//        driver.navigate().to("https://jqueryui.com/droppable/");
//        WebElement frame=driver.findElement(By.className("demo-frame"));
//        driver.switchTo().frame(frame);
//        WebElement drag=driver.findElement(By.xpath("//*[@id=\"draggable\"]"));
//        WebElement drop=driver.findElement(By.xpath("//*[@id=\"droppable\"]"));
//        actions.dragAndDrop(drag,drop).perform();
//        driver.navigate().to("https://www.seleniumeasy.com/test/drag-and-drop-demo.html");
//        WebElement drag=driver.findElement(By.xpath("//*[@id=\"todrag\"]/span[1]"));
//        WebElement drop=driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div/div[2]/div[2]/div"));
//        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//        actions.dragAndDrop(drag,drop).build().perform();
        
    }
}
