package bulkusercreation;

import com.opencsv.CSVReader;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Bulkusercreation {
    
    static int rowNumber=1,fileNumber=1;
    
    public static void main(String[] args) throws Exception {
        
        String[] fileSet = ListOfFiles();
        for(String file : fileSet){
            FileData(file);
        } 
    }
    
    private static String[] ListOfFiles(){
        File list = new File("D:\\UserCreation\\InputFiles");
        return list.list();
    }
    
    private static void ReportFile(String fileName,String userName, String message) throws Exception{
        FileWriter fw = new FileWriter("D:\\UserCreation\\ReportFiles\\ReportingFiles.txt", true);
        fw.append("\nFile Name : "+fileName+"\nUserName  : "+userName+"\nReport    : "+message+"\n");
        fw.append("_______________________________________________________________________________________________________\n");
        fw.close();
    }
    
    private static void FileData(String file) throws Exception{
        CSVReader reader=new CSVReader(new FileReader("D:\\UserCreation\\InputFiles\\"+file));
        HashMap<String,String> map =new HashMap<>();
        String[] header = reader.readNext();
        ArrayList<String> headings = new ArrayList<>();
        for(String data : header){
            String Key = data.substring(1, data.length()-1);
            map.put(Key, "");
            headings.add(Key);
        }
        String[] data;
        while((data = reader.readNext()) != null){
            for(int i=0; i<data.length; i++){
                String Key = headings.get(i);
                String Value = data[i].substring(1, data[i].length()-1);
                map.put(Key, Value);
            }
            UserCreation(map,file);
        }
    }
    
    private static ArrayList<String> ResultCheckingArray(){
         ArrayList<String> list = new ArrayList<>();
         list.add("Full Name");
         list.add("Telephone Number");
         list.add("Employee ID");
         list.add("Container Name");
         list.add("Display Name");
         list.add("Initials");
         list.add("Mobile");
         list.add("SAM Account Name");
         list.add("Last Name");
         list.add("Email Address");
         list.add("First Name");
         list.add("Logon Name");

         return list;
    }
    
    private static void TakeScreenshot(WebDriver driver) throws IOException, InterruptedException{
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        String filename = "File-"+fileNumber+"-"+date.toString()+"-"+time.getHour()+"-"+time.getMinute()+"-"+time.getSecond();
        fileNumber++;
        File Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File Destination = new File("D:\\UserCreation\\ReportFiles\\Screenshot\\"+filename+".jpg");
        FileUtils.copyFile(Screenshot,Destination);
    }
    
    private static void CheckingReportGeneration(HashMap<String,String> map1, HashMap<String,String> map2, String time) throws FileNotFoundException, IOException{
        FileInputStream file = new FileInputStream("D:\\UserCreation\\ReportFiles\\CheckingReport.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Cell cell = null;
        XSSFRow sheetrow = sheet.createRow(rowNumber);
        cell = sheetrow.createCell(0);
        cell.setCellValue(rowNumber);
        cell = sheetrow.createCell(1);
        
        
        if(map1.get("FullName").equals(map2.get("Full Name"))) cell.setCellValue(map2.get("Full Name"));
        else cell.setCellValue("false");
        cell = sheetrow.createCell(2);
        if(map1.get("FirstName").equals(map2.get("First Name"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(3);
        if(map1.get("LastName").equals(map2.get("Last Name"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(4);
        if(map1.get("Initial").equals(map2.get("Initials"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(5);
        if(map1.get("Logon").equals(map2.get("SAM Account Name"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(6);
        if(map2.get("Logon Name").contains(map1.get("Logonmail"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(7);
        if(map2.get("Email Address").contains(map1.get("Email"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(8);
        if(map2.get("Container Name").contains(map1.get("Value"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(9);
        if(map1.get("Mobile").equals(map2.get("Mobile"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(10);
        if(map1.get("Display").equals(map2.get("Display Name"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(11);
        if(map1.get("id").equals(map2.get("Employee ID"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(12);
        if(map1.get("Phone").equals(map2.get("Telephone Number"))) cell.setCellValue("true");
        else cell.setCellValue("false");
        cell = sheetrow.createCell(13);
        cell.setCellValue(time);
        file.close();
        FileOutputStream outFile =new FileOutputStream(new File("D:\\UserCreation\\ReportFiles\\CheckingReport.xlsx"));
        workbook.write(outFile);
        outFile.close();
        rowNumber++;
    }
    
    private static void UserCreation(HashMap<String,String> map, String file) throws InterruptedException, Exception{
        
        String FirstName=map.get("FirstName"), Ini=map.get("Initial"), LastName=map.get("LastName"), Logonmail=map.get("Logonmail"), Logon=map.get("Logon"), FullName=map.get("FullName"), Display=map.get("Display"), id=map.get("id"), Phone=map.get("Phone"), Email=map.get("Email"),
        Mobile=map.get("Mobile"), value=map.get("Value");
        
        Ini="Hello";
        
        LocalTime start=LocalTime.now();
        
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver;
        driver=new ChromeDriver();
        JavascriptExecutor js= (JavascriptExecutor) driver;
        WebDriverWait wait=new WebDriverWait(driver, 120);
        Actions action =new Actions(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        driver.get("https://demo.admanagerplus.com/");
        TakeScreenshot(driver);
        
        driver.findElement(By.xpath("//a[text()=\" Administrator\"]")).click();
        TakeScreenshot(driver);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=\"Management\"]"))).click();
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//span[@data-original-title=\"Create Single User\"]")).click();
        Thread.sleep(2000);
        TakeScreenshot(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@id=\"orgSelected_2010\"]"))).click();
        TakeScreenshot(driver); 

        driver.findElement(By.xpath("//*[@id=\"OrgSearch_2010\"]")).sendKeys("Docklands");
        TakeScreenshot(driver);
      
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("li[orgattrindex=\"7\"]"))).click();
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//input[@id=\"input2012\"]")).sendKeys(Email);
        TakeScreenshot(driver);
        
        driver.findElement(By.xpath("//input[@id=\"input2006\"]")).sendKeys(FullName);
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//input[@id=\"input2007\"]")).sendKeys(Display);
        TakeScreenshot(driver);
        
        driver.findElement(By.xpath("//input[@id=\"input2005\"]")).sendKeys(Logon);
        TakeScreenshot(driver);
        
        driver.findElement(By.xpath("//input[@id=\"input2004\"]")).sendKeys(Logonmail);
        TakeScreenshot(driver);
        
        driver.findElement(By.xpath("//input[@id=\"input2001\"]")).sendKeys(FirstName);
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//input[@id=\"input2002\"]")).sendKeys(Ini);
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//input[@id=\"input2003\"]")).sendKeys(LastName);
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//input[@id=\"input2008\"]")).sendKeys(id);
        TakeScreenshot(driver);

        driver.findElement(By.xpath("//input[@id=\"input2009\"]")).sendKeys("Description");
        TakeScreenshot(driver);

        driver.findElement(By.cssSelector("input#input2011")).sendKeys(Phone);
        TakeScreenshot(driver);

        driver.findElement(By.cssSelector("input#input2013")).sendKeys("WebPage");
        TakeScreenshot(driver);

        driver.findElement(By.cssSelector("i[onclick=\"javascript:FcSelectContainer.selectContainer('input2014',true)\"]")).click();
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id=\"adObjects\"]")));
        TakeScreenshot(driver);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".admp-fs-11 > img")));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div/div/form/div/ul/li[2]/i"))).click();
        TakeScreenshot(driver);

        WebElement radiobutton=driver.findElement(By.xpath("//a[@id=\""+value+",DC=com_anchor\"]"));
        action.click(radiobutton).build().perform();
        TakeScreenshot(driver);

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//*[@id=\"popupButtonVal\"]")).click(); 
        TakeScreenshot(driver);
            
        WebElement Account=driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/table/tbody/tr/td[1]/div/table[1]/tbody/tr/td[1]/table/tbody/tr/td/div/table/tbody/tr/td[2]/div/table/tbody/tr/td/div"));
        action.click(Account).build().perform();
        TakeScreenshot(driver);

        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading > span:nth-child(1) > img")));
        TakeScreenshot(driver);

         wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#ownPasswordDisplayString2015"))).click();
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//input[@id=\"enterPassword_2015\"]")).sendKeys("Password");
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//input[@id=\"confirmPassword_2015\"]")).sendKeys("Password");
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//span[@id=\"mustChangePwdDisplayName_2073\"]")).click();
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//span[@id=\"pwdNeverExpiresDisplayName_2073\"]")).click();
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//span[@id=\"accountDisabledDisplayName_2073\"]")).click();
         TakeScreenshot(driver);
             
         WebElement open = driver.findElement(By.xpath("//div[@onclick=\"javascript:FcMemberOfWithPG.openPopup('input2016',true,false,true)\"]")); 
         wait.until(ExpectedConditions.elementToBeClickable(open)).click();
         TakeScreenshot(driver);

         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@onclick=\"javascript:FcMemberOfWithPG.addGroup(this,FcMemberOfWithPG.actionId)\"]"))).click();
         Thread.sleep(500);
         TakeScreenshot(driver);

         WebElement check1=driver.findElement(By.cssSelector("input[id='CheckBoxList_CN=_new_ADGroup,OU=Demo Contacts,DC=admanagerplus,DC=com']"));
         action.click(check1).build().perform();
         TakeScreenshot(driver);

         WebElement check2=driver.findElement(By.xpath("//input[@id=\"CheckBoxList_CN=A100-1000,OU=AODB USERS,OU=DemoGroup,DC=admanagerplus,DC=com\"]"));
         action.click(check2).build().perform();
         TakeScreenshot(driver);

         WebElement check3=driver.findElement(By.xpath("//input[@id=\"CheckBoxList_CN=a5b5,OU=Demo Contacts,DC=admanagerplus,DC=com\"]"));
         action.click(check3).build().perform();
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//input[@id=\"popupButtonVal\"]")).click();
         TakeScreenshot(driver);

         wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//option[text()=\"A100-1000[admanagerplus.com/DemoGroup/AODB USERS]\"]"))).click();
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//label[text()=\"Set as primary group\"]")).click();
         TakeScreenshot(driver);

         driver.findElement(By.xpath("//input[@onclick=\"javascript:FcMemberOfWithPG.onPopupOK();\"]")).click();
         Thread.sleep(1000);
         TakeScreenshot(driver);

         WebElement contact=driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/table/tbody/tr/td[1]/div/table[1]/tbody/tr/td[1]/table/tbody/tr/td/div/table/tbody/tr/td[3]/div/table/tbody/tr/td/div"));
         js.executeScript("arguments[0].click()", contact);
         TakeScreenshot(driver);

         wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading > span:nth-child(1) > img")));
         Thread.sleep(1000);
         TakeScreenshot(driver);
      
         WebElement hp=driver.findElement(By.xpath("//input[@id=\"input2022\"]"));
         WebElement hpn=wait.until(ExpectedConditions.elementToBeClickable(hp));
         hpn.sendKeys("Home_phone");
         TakeScreenshot(driver);
        
          driver.findElement(By.xpath("//input[@id=\"input2023\"]")).sendKeys("pager");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2024\"]")).sendKeys(Mobile);
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2025\"]")).sendKeys("fax");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2026\"]")).sendKeys("ipphone");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//textarea[@id=\"input2027\"]")).sendKeys("Notes");
          TakeScreenshot(driver);

          WebElement dropdown1 = driver.findElement(By.xpath("//span[@id=\"addOrgBtn_2028\"]"));
          TakeScreenshot(driver);

          WebElement d1=wait.until(ExpectedConditions.elementToBeClickable(dropdown1));
          d1.click();
          TakeScreenshot(driver);
        
          WebElement dropdown1value = driver.findElement(By.xpath("//span[text()=\"chunky\"]"));
          WebElement d1v=wait.until(ExpectedConditions.elementToBeClickable(dropdown1value));
          d1v.click();
          TakeScreenshot(driver);
        
          WebElement dropdown2 = driver.findElement(By.xpath("//span[@id=\"orgCaret_2029_span\"]"));
          WebElement d2=wait.until(ExpectedConditions.elementToBeClickable(dropdown2));
          d2.click();
          TakeScreenshot(driver);
        
          WebElement dropdown2value=driver.findElement(By.xpath("//span[text()=\"Toto\"]"));
          action.click(dropdown2value).build().perform();
          TakeScreenshot(driver);
        
          WebElement dropdown3=driver.findElement(By.xpath("//span[@id=\"addOrgBtn_2030\"]"));
          WebElement d3=wait.until(ExpectedConditions.elementToBeClickable(dropdown3));
          d3.click();
          TakeScreenshot(driver);
        
          WebElement dropdown3value = driver.findElement(By.xpath("//span[text()=\"HS\"]"));
          WebElement d3v=wait.until(ExpectedConditions.elementToBeClickable(dropdown3value));
          d3v.click();
          TakeScreenshot(driver);
        
          WebElement dropdown4 = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/table/tbody/tr/td[1]/div/table[2]/tbody/tr/td/div/div[3]/div/div/div/div/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/div[7]/ul/li[4]/div[1]/span/i"));
          WebElement d4=wait.until(ExpectedConditions.elementToBeClickable(dropdown4));
          d4.click();
          TakeScreenshot(driver);
        
          driver.findElement(By.xpath("//i[@onclick=\"$('.left-flow').toggleClass('open');$('#searchText_310').focus();\"]")).click();
          TakeScreenshot(driver);
        
          driver.findElement(By.xpath("//input[@id=\"searchText_310\"]")).sendKeys("jonny bravo");
          Thread.sleep(2000);
          TakeScreenshot(driver);

          WebElement radiobtn = driver.findElement(By.xpath("//input[@id=\"RadioBoxList_CN=jonny bravo,OU=Asesuisa,OU=Demo Contacts,DC=admanagerplus,DC=com\"]"));
          action.click(radiobtn).build().perform();
          TakeScreenshot(driver);
              
          driver.findElement(By.xpath("//input[@id=\"popupButtonVal\"]")).click();
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//textarea[@id=\"input2032\"]")).sendKeys("Street");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2033\"]")).sendKeys("POBOX");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2034\"]")).sendKeys("city");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2035\"]")).sendKeys("state");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//input[@id=\"input2036\"]")).sendKeys("zip");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//button[@data-id=\"input2037\"]")).click();
          TakeScreenshot(driver);
      
          driver.findElement(By.xpath("//div[@class=\"bs-searchbox\"]//child::input")).sendKeys("India");
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//span[text()=\"India\"]")).click();
          TakeScreenshot(driver);

          driver.findElement(By.cssSelector("input[onclick=\"FcLayouts.submitExecuteLayoutWithDuplicateCheck('false')\"]")).click();
          TakeScreenshot(driver);

          wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loading > span:nth-child(1) > img")));
          TakeScreenshot(driver);

          String finaltext=driver.findElement(By.xpath("/html/body/div[8]/div[2]/table[1]/tbody/tr/td[2]/div/table/tbody/tr/td/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/span[1]/b")).getText();	  

          ReportFile(file,FullName,finaltext);

          driver.findElement(By.xpath("//a[text()=\"Delegation\"]")).click();
          TakeScreenshot(driver);

          wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#module_7003 > a"))).click();
          Thread.sleep(2000);
          TakeScreenshot(driver);

          WebElement loading = driver.findElement(By.cssSelector(".loading > img"));

          wait.until(ExpectedConditions.invisibilityOf(loading));
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//*[@id=\"colBasedSearch_auditReport\"]")).click();
          TakeScreenshot(driver);

          WebElement searchbox = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[6]/div/div[4]/div/div[3]/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[8]"));
          js.executeScript("arguments[0].scrollIntoView();",searchbox );
          TakeScreenshot(driver);

          wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id=\"searchValue_auditReport_OBJECT_NAME\"]"))).sendKeys(Logon);
          js.executeScript("arguments[0].click();", searchbox);
          Thread.sleep(1000);
          TakeScreenshot(driver);

          Robot robort= new Robot();
          robort.keyPress(KeyEvent.VK_ENTER);
          robort.keyRelease(KeyEvent.VK_ENTER);
          
          wait.until(ExpectedConditions.invisibilityOf(loading));
          Thread.sleep(1000);
          TakeScreenshot(driver);

          driver.findElement(By.xpath("//*[@id=\"ResultDataRows_auditReport\"]/tbody/tr[2]/td[9]/a")).click();
          TakeScreenshot(driver);

          wait.until(ExpectedConditions.invisibilityOf(loading));
          TakeScreenshot(driver);
          
          HashMap<String,String> resultmap =new HashMap<>();
          ArrayList<String> list = ResultCheckingArray();
      
          List<WebElement> allRows = driver.findElements(By.xpath("/html/body/div[1]/div[1]/div/div[1]/div[6]/div/div[6]/div/div/div[2]/div[2]/div/div/div[2]/div/div[1]/table/tbody/tr"));
          
          for (WebElement r : allRows) { 
            js.executeScript("arguments[0].scrollIntoView();", r);
            TakeScreenshot(driver);
            
            List<WebElement> cells = r.findElements(By.tagName("td")); 
            String key="",val="";
            int i=0;
            for (WebElement cell : cells) { 
                if(i==0) key=cell.getText();
                if(i==1) val=cell.getText();
                i++;
            }
           if(list.contains(key)) resultmap.put(key, val);
          }
          
          LocalTime end = LocalTime.now();
          Duration diff = Duration.between(start, end);
          String time = diff.toHours()+" : "+diff.toMinutes()+" : "+diff.getSeconds();
          
          CheckingReportGeneration(map, resultmap,time);
          
          driver.close();
    }  
}
