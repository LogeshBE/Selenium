
package reportchecking;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author logesh-pt4110
 */
public class Reportchecking {
    
    public static void main(String[] args) throws InterruptedException, IOException {
        exportReport();
    }
    
    public static String[][] getGeneratedData(WebDriver driver){
        int[] rows = {2,3};
        int[] col =  {3,8};
        String[][] data = new String[rows.length][col.length];
        for(int i=0;i<rows.length;i++){
            int x = rows[i];
            for(int j=0;j<col.length;j++){
                int y = col[j];
                String value = driver.findElement(By.xpath("//*[@id=\"resultRows_0\"]/tbody/tr["+x+"]/td["+y+"]")).getText();
                data[i][j]=value;
            }
        }
        return data;
    }
    
    public static String[][] getHTMLData(WebDriver driver,String[][] generateddata) throws InterruptedException{
        
        String[][] htmldata = new String[generateddata.length][generateddata[0].length];
        int m=0,n=0;
        Thread.sleep(8000);
        ArrayList<String> header = new ArrayList();
        ArrayList<String> key = new ArrayList();
        key.add("SAM Account Name");
        key.add("Email Address");
        
        ArrayList<String> refereance = new ArrayList<>();
        for(int i = 0 ; i < generateddata.length ; i++){
            refereance.add(generateddata[i][1]);
        }
                        
        List<WebElement> htmlheaders = driver.findElements(By.xpath("/html/body/table/tbody/tr/td[2]/table[2]/tbody/tr[5]/td"));
        for(WebElement head : htmlheaders){
            header.add(head.getText());
        }
        
        int emailindex = header.indexOf("Email Address");
        emailindex++;
        
        List<WebElement> page = driver.findElements(By.xpath("/html/body/table/tbody/tr/td[2]/table"));
        
        for(int i=1;i<=page.size();i++){
            List<WebElement> rows = driver.findElements(By.xpath("//html/body/table/tbody/tr/td[2]/table["+i+"]/tbody/tr"));
            for(int j=1;j<=rows.size();j++){
                String email;
                try{ 
                    email = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table["+i+"]/tbody/tr["+j+"]/td["+emailindex+"]")).getText();
                }
                catch(Exception e){
                   continue;
                }
                if(refereance.contains(email)){
                    n=0;
                    for(int x=0;x<key.size();x++){
                        String keyvalue = key.get(x);
                        int index = header.indexOf(keyvalue);
                        index++;
                        String value = driver.findElement(By.xpath("/html/body/table/tbody/tr/td[2]/table["+i+"]/tbody/tr["+j+"]/td["+index+"]")).getText();
                        htmldata[m][n]=value;
                        n++;
                    }
                    m++;
                } 
            }
        }
        return htmldata;
           
    }
    
    public static String[][] getFileData(String[][] generatedData) throws FileNotFoundException, IOException{
        CSVReader reader=new CSVReader(new FileReader("C:\\Users\\logesh-pt4110\\Downloads\\ADMPReport.csv"));
        String[][] fileData = new String[generatedData.length][generatedData[0].length];
        int x=0,y=0;
        String[] header = reader.readNext();
        
        ArrayList<String> refereance = new ArrayList<>();
        for(int i = 0 ; i < generatedData.length ; i++){
            refereance.add(generatedData[i][1]);
        }
        
        ArrayList<String> headings = new ArrayList<>();
        for(String data : header) headings.add(data);
        ArrayList<String> key = new ArrayList();
        key.add("SAM Account Name");
        key.add("Email Address");
        int emailindex = headings.indexOf("Email Address");
        String[] data;
        while((data = reader.readNext()) != null){
            if(refereance.contains(data[emailindex])){
                y=0;
                for(int i=0;i<key.size();i++){
                    String keyvalue = key.get(i);
                    int index = headings.indexOf(keyvalue);
                    fileData[x][y]=data[index];
                    y++;
                }
                x++;
            }  
        }
        return fileData;
    }
    
    public static void checkingReports(String[][] generatedData, String[][] htmlData, String[][] fileData) throws IOException{
        
        String[] Key = {"SAM Account Name","Email Address"};
        FileWriter fw = new FileWriter("D:\\UserCreation\\ReportFiles\\ReportingFiles.txt", true);
        fw.append("Report Generation :- \n");
        for(int i=0;i<generatedData.length;i++){
            
            fw.append("\nSo. no     :"+(i+1)+"\n");
            for(int j=0;j<generatedData[i].length;j++){
                fw.append("\nField name :"+Key[j]);
               fw.append("\n            Genearated data - "+generatedData[i][j]+
                       "\n            HTML Data       - " +htmlData[i][j]+
                       " \n            CSV Data        - "+fileData[i][j]+"\n");
               if(generatedData[i][j].equals(htmlData[i][j]) && generatedData[i][j].equals(fileData[i][j]))
                   fw.append("            Result          - true\n");
               else fw.append("           Result          - false\n");
            }
        }
        fw.append("_______________________________________________________________________________________________________\n");
        fw.close();
    }
    public static void exportReport() throws InterruptedException, IOException{
        System.setProperty("webdriver.chrome.driver","D:\\selenium\\Browser_Driver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();  
        JavascriptExecutor js= (JavascriptExecutor) driver;
        WebDriverWait wait=new WebDriverWait(driver, 120);
        driver.manage().window().maximize();
        driver.get("https://demo.admanagerplus.com/");
        driver.findElement(By.xpath("//a[text()=\" Administrator\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=\"Reports\"]"))).click();
        Thread.sleep(2000);
        WebElement passreport = driver.findElement(By.xpath("//*[@id=\"reportCategory_2\"]/a"));
        js.executeScript("arguments[0].click()", passreport);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()=\"Password Unchanged Users\" and @data-toggle=\"tooltip\"]"))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value=\"Generate\"]"))).click();
        Thread.sleep(5000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()=\"Export as\"]"))).click();
        Thread.sleep(5000);
        WebElement csv = driver.findElement(By.xpath("//a[text()=\"CSV\"]"));
        js.executeScript("arguments[0].click()", csv);
        Thread.sleep(5000);
        WebElement html = driver.findElement(By.xpath("//a[text()=\"HTML\"]"));
        js.executeScript("arguments[0].click()", html);
        
        String[][] generatedData=getGeneratedData(driver);
        
        Set<String> set = driver.getWindowHandles();
        String htmlwindow="";
        for(String s : set ){
            htmlwindow=s;
        }
        driver.switchTo().window(htmlwindow);
        
        String[][] htmlData = getHTMLData(driver, generatedData);
        
        String[][] fileData = getFileData(generatedData);
       
        checkingReports(generatedData, htmlData, fileData);
        
        driver.quit();
    }
}