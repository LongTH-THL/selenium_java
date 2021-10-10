package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.swing.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_13_User_Interaction_01 {
    WebDriver driver;
    Alert alert;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExtutor;
    Actions action;
    String osName = System.getProperty("os.name");
    Keys key;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        action = new Actions(driver);
        jsExtutor = (JavascriptExecutor) driver;
        explicitWait = new WebDriverWait(driver,15);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),"We ask for your age only for statistical purposes.");
    }

    @Test
    public void TC_01_Hover_Menu(){
        driver.get("https://www.myntra.com/");
        action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
        sleepInSecond(2);

        // Move to element + click
        action.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
    }
    @Test
    public void TC_02_Click_And_Hover() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        // click and hover from 1 to 4
        // Click vao 1 va giu chuot -> di chuot den 4 -> bo chuot
        action.clickAndHold(allItems.get(0)).moveToElement(allItems.get(3)).release().perform();

        Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(),4);
    }

    @Test
    public void TC_03_Click_And_Hover_Random() {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        if(osName.contains("Windows")){
            key = Keys.CONTROL;
        } else {
            key = Keys.COMMAND;
        }

        action.keyDown(key).perform();
        // chon random cac so
        action.click(allItems.get(2)).click(allItems.get(4)).click(allItems.get(6)).click(allItems.get(8)).perform();

        action.keyUp(key).perform();
        Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(),4);
    }
    @Test
    public void TC_04_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // scroll xuống button
        jsExtutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']")));

        action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
    }
    @Test
    public void TC_05_(){
    }

    @Test
    public void TC_06_(){
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
