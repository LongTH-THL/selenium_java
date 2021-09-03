package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class Topic_00_Template {
    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_(){
    }
    @Test
    public void TC_02_(){
    }
    @Test
    public void TC_03_(){
    }
    @Test
    public void TC_04_(){
    }
    @Test
    public void TC_05_(){

    }
    @Test
    public void TC_06_(){

    }


    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
