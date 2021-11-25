package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_23_Explicit_Wait {
    WebDriver driver;
    WebDriverWait explicitWait;
    By startButton = By.xpath("//button[text()='Start']");
    By loadingIcon = By.xpath("//div[@id='loading']");
    By helloText = By.xpath("//div[@id='finish']");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Less_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");
        explicitWait = new WebDriverWait(driver,15);
        // Wait để kiểm tra xem button đã có thể click được chưa
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        // Nếu có thể click được r thì sẽ click vào button
        driver.findElement(startButton).click();

        explicitWait = new WebDriverWait(driver,3);
        // Business = loading icon biến mất -> text hiển thị hoặc ngược lại

        // Wait cho loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");

    }
    @Test
    public void TC_02_Enough(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver,15);
        // Wait để kiểm tra xem button đã có thể click được chưa
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        // Nếu có thể click được r thì sẽ click vào button
        driver.findElement(startButton).click();

        explicitWait = new WebDriverWait(driver,5);
        // Wait cho loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_03_Greater_Than(){
        driver.get("https://automationfc.github.io/dynamic-loading/");

        explicitWait = new WebDriverWait(driver,15);
        // Wait để kiểm tra xem button đã có thể click được chưa
        explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
        // Nếu có thể click được r thì sẽ click vào button
        driver.findElement(startButton).click();

        explicitWait = new WebDriverWait(driver,8);
        // Wait cho loading icon biến mất
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
        Assert.assertEquals(driver.findElement(helloText).getText(),"Hello World!");
    }
    @Test
    public void TC_04_Ajax_Loading(){
        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
        explicitWait = new WebDriverWait(driver,15);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"No Selected Dates to display.");

        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='1']")));
        driver.findElement(By.xpath("//a[text()='1']")).click();

        By loadingIcon = By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']");
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"Monday, November 1, 2021");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']/a[text()='1']")));
        Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']/a[text()='1']")).isDisplayed());
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
