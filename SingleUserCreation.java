package singleusercreation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author logesh-pt4110
 */
public class SingleUserCreation {

    public static void main(String[] args) throws IOException, InterruptedException {
            SingleUserCreation user=new SingleUserCreation();
            String[][] dataset=user.Datafields();
            for(int i=0;i<dataset.length;i++)
                user.UserCreation(dataset[i]);
    }
    private String[][] Datafields() throws FileNotFoundException, IOException{
        FileInputStream file=new FileInputStream("D:\\WPS Office\\singleusercreation.xlsx");
       XSSFWorkbook workbook=new XSSFWorkbook(file);
       XSSFSheet sheet=workbook.getSheetAt(0);
       int lastrow=sheet.getLastRowNum();
       int lastcell=sheet.getRow(0).getLastCellNum();
       String arr[][]=new String[lastrow][lastcell];
       for(int i=0;i<lastrow;i++)
               for(int j=0;j<lastcell;j++)
                       arr[i][j] = sheet.getRow(i).getCell(j).getStringCellValue();
       return arr;
    }
    private void UserCreation(String[] data) throws InterruptedException{
        
        String FirstName=data[0], Ini=data[1], LastName=data[2], Logonmail=data[3], Logon=data[4], FullName=data[5], Display=data[6], id=data[7],  Description=data[8], Phone=data[9], Email=data[10],  WebPage=data[11], Office=data[12],
		 Password=data[13], Cpassword=data[14], 
            Home_phone=data[15], pager=data[16],  Mobile=data[17], fax=data[18], ipphone=data[19], Notes=data[20], Street=data[21], POBOX=data[22], city= data[23], state=data[24], zip=data[25], value=data[26];
        
       System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
       WebDriver driver;
      driver=new ChromeDriver();
      JavascriptExecutor js= (JavascriptExecutor) driver;
      WebDriverWait wait=new WebDriverWait(driver, 20);
      Actions action =new Actions(driver);
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.get("https://demo.admanagerplus.com/");
      driver.findElement(By.xpath("//a[text()=\" Administrator\"]")).click();
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=\"Management\"]"))).click();
      driver.findElement(By.xpath("//span[@data-original-title=\"Create Single User\"]")).click();
      Thread.sleep(2000);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id=\"orgSelected_2010\"]"))).click();
        driver.findElement(By.xpath("//*[@id=\"OrgSearch_2010\"]")).sendKeys(Office);
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[orgattrindex=\"7\"]"))).click();
            driver.findElement(By.xpath("//input[@id=\"input2001\"]")).sendKeys(FirstName);
            driver.findElement(By.xpath("//input[@id=\"input2002\"]")).sendKeys(Ini);
            driver.findElement(By.xpath("//input[@id=\"input2003\"]")).sendKeys(LastName);
            driver.findElement(By.xpath("//input[@id=\"input2004\"]")).sendKeys(Logonmail);
            driver.findElement(By.xpath("//input[@id=\"input2005\"]")).sendKeys(Logon);
            driver.findElement(By.xpath("//input[@id=\"input2006\"]")).sendKeys(FullName);
            driver.findElement(By.xpath("//input[@id=\"input2007\"]")).sendKeys(Display);
            driver.findElement(By.xpath("//input[@id=\"input2008\"]")).sendKeys(id);
            driver.findElement(By.xpath("//input[@id=\"input2009\"]")).sendKeys(Description);
            driver.findElement(By.cssSelector("input#input2011")).sendKeys(Phone);
            driver.findElement(By.xpath("//input[@id=\"input2012\"]")).sendKeys(Email);
            driver.findElement(By.cssSelector("input#input2013")).sendKeys(WebPage);
            driver.findElement(By.cssSelector("i[onclick=\"javascript:FcSelectContainer.selectContainer('input2014',true)\"]")).click();
//			  	WebElement frame=driver.findElement(By.xpath("//iframe[@id=\"adObjects\"]"));
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id=\"adObjects\"]")));

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".admp-fs-11 > img")));

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/form/div/ul/li[2]/i"))).click();
            WebElement radiobutton=driver.findElement(By.xpath("//a[@id=\""+value+",DC=com_anchor\"]"));
            action.click(radiobutton).build().perform();
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("//*[@id=\"popupButtonVal\"]")).click(); 
            WebElement Account=driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/table/tbody/tr/td[1]/div/table[1]/tbody/tr/td[1]/table/tbody/tr/td/div/table/tbody/tr/td[2]/div/table/tbody/tr/td/div"));
            action.click(Account).build().perform();

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading > span:nth-child(1) > img")));

//				 WebElement radio=driver.findElement( By.cssSelector("span#ownPasswordDisplayString2015"));
             wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#ownPasswordDisplayString2015"))).click();
             driver.findElement(By.xpath("//input[@id=\"enterPassword_2015\"]")).sendKeys(Password);
             driver.findElement(By.xpath("//input[@id=\"confirmPassword_2015\"]")).sendKeys(Cpassword);
             driver.findElement(By.xpath("//span[@id=\"mustChangePwdDisplayName_2073\"]")).click();
             driver.findElement(By.xpath("//span[@id=\"pwdNeverExpiresDisplayName_2073\"]")).click();
             driver.findElement(By.xpath("//span[@id=\"accountDisabledDisplayName_2073\"]")).click();
    WebElement open = driver.findElement(By.xpath("//div[@onclick=\"javascript:FcMemberOfWithPG.openPopup('input2016',true,false,true)\"]")); 
     wait.until(ExpectedConditions.elementToBeClickable(open)).click();
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@onclick=\"javascript:FcMemberOfWithPG.addGroup(this,FcMemberOfWithPG.actionId)\"]"))).click();
     Thread.sleep(500);
     WebElement check1=driver.findElement(By.cssSelector("input[id='CheckBoxList_CN=_new_ADGroup,OU=Demo Contacts,DC=admanagerplus,DC=com']"));
     action.click(check1).build().perform();
     WebElement check2=driver.findElement(By.xpath("//input[@id=\"CheckBoxList_CN=A100-1000,OU=AODB USERS,OU=DemoGroup,DC=admanagerplus,DC=com\"]"));
     action.click(check2).build().perform();
     WebElement check3=driver.findElement(By.xpath("//input[@id=\"CheckBoxList_CN=a5b5,OU=Demo Contacts,DC=admanagerplus,DC=com\"]"));
     action.click(check3).build().perform();
     driver.findElement(By.xpath("//input[@id=\"popupButtonVal\"]")).click();

     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()=\"A100-1000[admanagerplus.com/DemoGroup/AODB USERS]\"]"))).click();
     driver.findElement(By.xpath("//label[text()=\"Set as primary group\"]")).click();
     driver.findElement(By.xpath("//input[@onclick=\"javascript:FcMemberOfWithPG.onPopupOK();\"]")).click();
     WebElement contact=driver.findElement(By.xpath("//*[@id=\"15304_tabNameDiv\"]/table/tbody/tr/td/div"));
//			 Thread.sleep(2000); 
//			 WebElement con=driver.findElement(By.cssSelector("#\\31 5304_tabNameDiv > table > tbody > tr > td > div"));
      js.executeScript("arguments[0].click()", contact);
//			action.click(contact).perform(); 
//			  wait.until(ExpectedConditions.elementToBeClickable (
//						 (By.xpath("//*[@id=\"15304_tabNameDiv\"]/table/tbody/tr/td/div")))).click(); 
//			  action.moveToElement(driver.findElement(By.cssSelector("#\\31 5304_tabNameDiv > table > tbody > tr > td > div"))).click().build().perform();			  

      wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading > span:nth-child(1) > img")));

      Thread.sleep(1000);
      WebElement hp=driver.findElement(By.xpath("//input[@id=\"input2022\"]"));
      WebElement hpn=wait.until(ExpectedConditions.elementToBeClickable(hp));
        hpn.sendKeys(Home_phone);
      driver.findElement(By.xpath("//input[@id=\"input2023\"]")).sendKeys(pager);
      driver.findElement(By.xpath("//input[@id=\"input2024\"]")).sendKeys(Mobile);
      driver.findElement(By.xpath("//input[@id=\"input2025\"]")).sendKeys(fax);
      driver.findElement(By.xpath("//input[@id=\"input2026\"]")).sendKeys(ipphone);
      driver.findElement(By.xpath("//textarea[@id=\"input2027\"]")).sendKeys(Notes);
      WebElement dropdown1 = driver.findElement(By.xpath("//span[@id=\"addOrgBtn_2028\"]"));
      WebElement d1=wait.until(ExpectedConditions.elementToBeClickable(dropdown1));
        d1.click();
      WebElement dropdown1value = driver.findElement(By.xpath("//span[text()=\"chunky\"]"));
      WebElement d1v=wait.until(ExpectedConditions.elementToBeClickable(dropdown1value));
        d1v.click();
      WebElement dropdown2 = driver.findElement(By.xpath("//span[@id=\"orgCaret_2029_span\"]"));
      WebElement d2=wait.until(ExpectedConditions.elementToBeClickable(dropdown2));
        d2.click();
      WebElement dropdown2value=driver.findElement(By.xpath("//span[text()=\"Toto\"]"));
        action.click(dropdown2value).build().perform();
//			    wait.until(ExpectedConditions.presenceOfElementLocated(
//			    		By.cssSelector("#OrgList_2029 > li:nth-child(10) > label > span"))).click();
      WebElement dropdown3=driver.findElement(By.xpath("//span[@id=\"addOrgBtn_2030\"]"));
      WebElement d3=wait.until(ExpectedConditions.elementToBeClickable(dropdown3));
        d3.click();
      WebElement dropdown3value = driver.findElement(By.xpath("//span[text()=\"HS\"]"));
      WebElement d3v=wait.until(ExpectedConditions.elementToBeClickable(dropdown3value));
        d3v.click();
      WebElement dropdown4 = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/table/tbody/tr/td[1]/div/table[2]/tbody/tr/td/div/div[3]/div/div/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div[7]/ul/li[4]/div[1]/span/i"));
      WebElement d4=wait.until(ExpectedConditions.elementToBeClickable(dropdown4));
        d4.click();
        driver.findElement(By.xpath("//i[@onclick=\"$('.left-flow').toggleClass('open');$('#searchText_310').focus();\"]")).click();
              driver.findElement(By.xpath("//input[@id=\"searchText_310\"]")).sendKeys("jonny bravo");
              Thread.sleep(2000);
              WebElement radiobtn = driver.findElement(By.xpath("//input[@id=\"RadioBoxList_CN=jonny bravo,OU=Asesuisa,OU=Demo Contacts,DC=admanagerplus,DC=com\"]"));
              action.click(radiobtn).build().perform();
      driver.findElement(By.xpath("//input[@id=\"popupButtonVal\"]")).click();
      driver.findElement(By.xpath("//textarea[@id=\"input2032\"]")).sendKeys(Street);
      driver.findElement(By.xpath("//input[@id=\"input2033\"]")).sendKeys(POBOX);
      driver.findElement(By.xpath("//input[@id=\"input2034\"]")).sendKeys(city);
      driver.findElement(By.xpath("//input[@id=\"input2035\"]")).sendKeys(state);
      driver.findElement(By.xpath("//input[@id=\"input2036\"]")).sendKeys(zip);
      driver.findElement(By.xpath("//button[@data-id=\"input2037\"]")).click();
      driver.findElement(By.xpath("//div[@class=\"bs-searchbox\"]//child::input")).sendKeys("India");
      driver.findElement(By.xpath("//span[text()=\"India\"]")).click();
      driver.findElement(By.xpath("//input[@onclick=\"FcLayouts.submitExecuteLayoutWithDuplicateCheck('false')\"]")).click();
      Thread.sleep(2000);   
    }
    
}
