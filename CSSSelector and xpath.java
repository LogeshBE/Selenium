
package seleniumbasics;

import org.openqa.selenium.By;
import static org.openqa.selenium.By.id;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.SendKeysAction;
import org.openqa.selenium.support.ui.Select;

public class CSSSelector {
    public void fun1(){
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        
//        driver.get("https://github.com/login");
//        driver.findElement(By.id("[id^='login_']")).sendKeys("Hello");
//        driver.findElement(By.id("[id$='field']")).sendKeys("Good");
//        driver.findElement(By.id("[id*='_fiel']")).sendKeys("Mrg");
        
        driver.navigate().to("https://en-gb.facebook.com/r.php?locale=en_GB&display=page");
//        Select dropdown=new Select(driver.findElement(By.id("day")));
//        dropdown.selectByIndex(20);
//        
        driver.findElement(By.cssSelector("select#day>option[value='20']")).click();
        driver.findElement(By.cssSelector("form#reg select#month option[value='8']")).click();
        driver.findElement(By.cssSelector("select#year option[value='2001']+option")).click();
        
//        driver.findElement(By.cssSelector("select#year>:first-child")).click();
//        driver.findElement(By.cssSelector("select#year>:first-child")).click();
//        driver.findElement(By.cssSelector("select#year>:nth-child(20)")).click();

        driver.findElement(By.xpath("//input[starts-with(@name,'first')]")).sendKeys("Logesh");
        driver.findElement(By.xpath("//input[contains(@name,'astnam')]")).sendKeys("S");
//        driver.findElement(By.xpath("//a[text()='Data Policy']")).click();
//       //input[@name='lastname' and @type='text']
//       //input[@name='lastname' or @type='text']
//      //select[@id='day']//child::option[3]
//      //select[@id='day']//parent::span
//      //select[@id='day']//descendant-or-self::option
//      //select//ancestor-or-self::span
        
    }
}
