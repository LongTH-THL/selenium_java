package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_19_Upload_File {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir") + "/IdeaProjects/git/selenium_webdriver";
    String meoName = "Meo.jpeg";
    String lanhName = "LAnh.jpg";

    String uploadFilePath = projectPath + File.separator + "uploadFilePath" + File.separator;

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
        sleepInSecond(2);
        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(lanhUploadFile);
        sleepInSecond(2);

        // Verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + meoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lanhName + "']")).isDisplayed());

        // Click to start button ==> upload
        List<WebElement> startButton = driver.findElements(By.xpath("//td/button[contains(@class,'start')]"));
        for (WebElement start:startButton){
            start.click();
            sleepInSecond(2);
        }

        // Verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + meoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + lanhName + "']")).isDisplayed());
    }
    @Test
    public void TC_02_Sendkey_Multiple_file(){
        driver.get("https://blueimp.github.io/jQuery-File-Upload/");

        driver.findElement(By.xpath("//input[@type='file']")).sendKeys(meoUploadFile + "\n" + lanhUploadFile);
        sleepInSecond(2);

        // Verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + meoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + lanhName + "']")).isDisplayed());

        // Click to start button ==> upload
        List<WebElement> startButton = driver.findElements(By.xpath("//td/button[contains(@class,'start')]"));
        for (WebElement start:startButton){
            start.click();
            sleepInSecond(2);
        }
        sleepInSecond(2);
        // Verify file upload success
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + meoName + "']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + lanhName + "']")).isDisplayed());
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

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
