package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_19_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String meoName = "Meo.jpg";
    String lanhName = "LAnh.jpg";

    String uploadFilePath = projectPath + "/git/selenium_webdriver" + File.separator + "uploadFilePath" + File.separator;

    String meoUploadFile = uploadFilePath + meoName;
    String lanhUploadFile = uploadFilePath + lanhName;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Sendkey_One_File(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(meoUploadFile);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(lanhUploadFile);
    }
    @Test
    public void TC_02_(){
    }
    @Test
    public void TC_03_(){
    }

    public int randomData() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    public void sleepInSecond(long timeoutInSecond){
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //@AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
