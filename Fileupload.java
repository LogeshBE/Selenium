package Fileimport;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Fileupload {
	WebDriver driver;
  @Test
  public void fileupload() throws AWTException, Exception {
	  System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.get("https://careers.zohorecruit.com/recruit/WebFormServeServlet?rid=162060bf19ef5bfd9c408621bb66328b6c49a8d3a80058b2d65120b6b5591867gidf62d65585721aed69c3d0236e3a70183");
	  driver.manage().window().maximize();
	  driver.findElement(By.xpath("//a[@id=\"theFile_link(Resume)\"]")).click();
	  
	  StringSelection selector=new StringSelection("Documents\\number");
	  java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selector,null);
	  Thread.sleep(2000);
	  Robot robot=new Robot();
	  robot.keyPress(KeyEvent.VK_CONTROL);
	  robot.keyPress(KeyEvent.VK_V);
	  robot.keyRelease(KeyEvent.VK_V);
	  robot.keyRelease(KeyEvent.VK_CONTROL);
	  Thread.sleep(2000);
	  robot.keyPress(KeyEvent.VK_ENTER);
	  robot.keyRelease(KeyEvent.VK_ENTER);
	  
	  
  }
}
