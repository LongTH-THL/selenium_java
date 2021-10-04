package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_12_Alert {
    WebDriver driver;
    Alert alert;
    WebDriverWait explicitWait;

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
    public void TC_01_Accept_Alert(){
        driver.get("http://demo.guru99.com/v4/index.php");
        driver.findElement(By.name("btnLogin")).click();
        sleepInSecond(2);

        // Wait cho alert xuất hiện
        // Wait cho alert xuất hiện + switch qua alert luôn
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        //alert = driver.switchTo().alert();

        // Get text alert
        String alertText = alert.getText();
        Assert.assertEquals(alertText,"User or Password is not valid");

        // Accept alert => alert biến mất
        alert.accept();
    }
    @Test
    public void TC_02_Accept_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        sleepInSecond(2);
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Get text alert
        String alertText = alert.getText();
        // Verify
        Assert.assertEquals(alertText,"I am a JS Alert");
        alert.accept();
        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
    }
    @Test
    public void TC_03_Confirm_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
        sleepInSecond(2);
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());

        // Get text alert
        String alertText = alert.getText();
        // Verify
        Assert.assertEquals(alertText,"I am a JS Confirm");
        alert.dismiss();
        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
    }
    @Test
    public void TC_04_Prompt_Alert(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(2);
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        String textInput = "Tran Hai Long";
        alert.sendKeys(textInput);
        sleepInSecond(5);
        alert.accept();
        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: " + textInput);

        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        sleepInSecond(2);
        alert = explicitWait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(textInput);
        sleepInSecond(5);
        alert.dismiss();
        // Verify
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: " + "null");
    }

    @Test
    public void TC_05_Authentication_Alert(){
        String username = "admin";
        String password = "admin";
        String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
        sleepInSecond(5);

        driver.get(url);
        Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
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
