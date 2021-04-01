
package seleniumbasics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class Multiplewindow {
    public void multiwindows() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.w3schools.com/js/js_popup.asp");
        driver.findElement(By.cssSelector("a.w3-btn[href=\"tryit.asp?filename=tryjs_alert\"]")).click();
        driver.findElement(By.cssSelector("a.w3-btn[href=\"tryit.asp?filename=tryjs_confirm\"]")).click();
        driver.findElement(By.cssSelector("a.w3-btn[href=\"tryit.asp?filename=tryjs_prompt\"]")).click();
        
        int i=1;
        Set <String> windows=driver.getWindowHandles();
        ArrayList<String> list=new ArrayList<>();
        for(String s : windows){
            String val=driver.switchTo().window(s).getWindowHandle();
            System.out.println("Window name "+i+" : "+val);
            list.add(val);
            i++;
        }
        i-=2;
        while(i!=0){
            driver.navigate().back();
            System.out.println("Window name "+i+" : "+list.get(i-1));
            i--;
        }
//        driver.close();
        driver.quit();
       
                
    }
}
