package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_11_HandleButton_Radio_Checkbox_02 {
    WebDriver driver;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        jsExcutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Custom_Radio(){
        driver.get("https://material.angular.io/components/radio/examples");
        // Cach 1: sử dụng thẻ input nhưng k click đc - có thể dùng verify được
        //By winterRadioButton = By.xpath("//input[@value='Winter']");
        //driver.findElement(winterRadioButton).click();
        //sleepInSecond(2);
        //Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());

        // Cách 2: Sử dụng thẻ span vừa click vừa verify
        // By winterRadioButton = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class,'outer')]");
        // driver.findElement(winterRadioButton).click();
        // sleepInSecond(2);
        // Assert.assertTrue(driver.findElement(winterRadioButton).isSelected());

        // Cách 3: Dùng span để click và dùng input để verify
        // By winterRadioSpan = By.xpath("//input[@value='Winter']/preceding-sibling::span[contains(@class,'outer')]");
        // By winterRadioInput = By.xpath("//input[@value='Winter']");

        // driver.findElement(winterRadioSpan).click();
        // Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());

        // làm dự án thực tế nếu dùng cách 3 sẽ mất thời gian bảo trì
        // 1 element phải defien 2 lần, rất mất thời gian và gây hiểu nhầm (input/span)

        // Cách 4: Trong dự án thực tế, sẽ dùng hàm click của JAVAScript để click vào thẻ input (bị ẩn)

        By winterRadioInput = By.xpath("//input[@value='Winter']");
        ClickToElementByJS(winterRadioInput);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(winterRadioInput).isSelected());
    }

    @Test
    public void TC_02_Custom_Checkbox(){
        driver.get("https://material.angular.io/components/checkbox/examples");

        By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
        By indeterminateCheckbox = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");
        CheckToCheckboxByJS(checkedCheckbox);
        sleepInSecond(2);
        CheckToCheckboxByJS(indeterminateCheckbox);
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        UnCheckToCheckboxByJS(checkedCheckbox);
        sleepInSecond(2);
        UnCheckToCheckboxByJS(indeterminateCheckbox);
        sleepInSecond(2);

        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void  TC_03_Radio_Button_Checkbox_GoogleDocs(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

        By canthoRadio = By.xpath("//div[@aria-label='Cần Thơ']");
        Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"),"false");
        CheckToCheckbox(canthoRadio);
        Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"), "true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Cần Thơ' and @aria-checked='true']")).isDisplayed());
        sleepInSecond(2);

        List<WebElement> checkboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
        for (WebElement checkbox:checkboxes) {
            checkbox.click();
            sleepInSecond(2);
        }

        // verify
        for (WebElement checkbox:checkboxes){
            Assert.assertEquals(checkbox.getAttribute("aria-checked"),"true");
        }
    }

    public void ClickToElementByJS(By by){
        jsExcutor.executeScript("arguments[0].click();",driver.findElement(by));
    }

    public void  CheckToCheckbox(By by){
        if(!driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    public void  CheckToCheckboxByJS(By by){
        if(!driver.findElement(by).isSelected()){
            jsExcutor.executeScript("arguments[0].click();",driver.findElement(by));
        }
    }

    public void  UnCheckToCheckbox(By by){
        if(driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    public void  UnCheckToCheckboxByJS(By by){
        if(driver.findElement(by).isSelected()){
            jsExcutor.executeScript("arguments[0].click();",driver.findElement(by));
        }
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