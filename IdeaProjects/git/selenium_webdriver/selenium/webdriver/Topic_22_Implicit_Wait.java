package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_22_Implicit_Wait {
    WebDriver driver;
    By startButton = By.xpath("//button[text()='Start']");
    By loadingIcon = By.xpath("//div[@id='loading']");
    By helloText = By.xpath("//div[@id='finish']");

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Less_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);

        driver.findElement(startButton).click();
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_02_Enough(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);

        driver.findElement(startButton).click();
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Great_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        driver.manage().timeouts().implicitlyWait(8,TimeUnit.SECONDS);

        driver.findElement(startButton).click();
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
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

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
