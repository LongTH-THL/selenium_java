package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_24_Mixing_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");
    By usernameTextbox = By.id("email");
    By passwordTextbox = By.id("pass");
    By registerButton = By.xpath("//a[text()='Create New Account']");


    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,15);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    @Test
    public void TC_01_Element_Found() {
        driver.get("https://vi-vn.facebook.com/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver,5);

        // Implicit
        System.out.println("Start Implicit = " + getDateTimeNow());
        driver.findElement(By.xpath("//input[@id='email']"));
        System.out.println("End Implicit = " + getDateTimeNow());

        System.out.println("Start Explicit = " + getDateTimeNow());
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
        System.out.println("End Explicit = " + getDateTimeNow());
    }

    // Khong tìm thấy element
    // TH1: Chỉ sử dụng Implicit -> chờ hết timeout, tìm lại sau mỗi 0,5s. hết Timeout đánh fail testcase ==> Throw NoSuchElementExeption
    // TH2: Chỉ sử dụng Explicit -> chờ hết timeout, tìm lại sau mỗi 0,5s. hết Timeout đánh fail testcase ==> Throw TimeoutExeption
    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.get("https://facebook.com/");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        // Implicit
        System.out.println("Start Implicit = " + getDateTimeNow());
        try {
            driver.findElement(By.xpath("//input[@id='testing']"));
        } finally {
            System.out.println("End Implicit = " + getDateTimeNow());
        }

    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit() {
            driver.get("https://facebook.com/");

            explicitWait = new WebDriverWait(driver,5);

            // Explicit
            System.out.println("Start Explicit = " + getDateTimeNow());
            try {
                explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='testing']")));
            } finally {
                System.out.println("End Explicit = " + getDateTimeNow());
            }

    }

    @Test
    public void TC_04_Element_Not_Found_Implicit_Explicit(){
        driver.get("https://facebook.com/");

        // Implicit > Explicit
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver,8);

        // Implicit
        System.out.println("Start Implicit = " + getDateTimeNow());
        try {
            driver.findElement(By.xpath("//input[@id='testing']"));
        } catch (Exception e1){

        }
        System.out.println("End Implicit = " + getDateTimeNow());

        // Explicit
        System.out.println("Start Explicit = " + getDateTimeNow());
        try {

            WebElement element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='testing']")));
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("End Explicit = " + getDateTimeNow());
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

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }
}
