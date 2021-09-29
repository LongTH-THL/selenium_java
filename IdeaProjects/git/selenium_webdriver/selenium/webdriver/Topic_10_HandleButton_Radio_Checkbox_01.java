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
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_10_HandleButton_Radio_Checkbox_01 {
    WebDriver driver;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        jsExcutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button(){
        driver.get("https://www.fahasa.com/customer/account/create?attempt=1");

        By loginButton = By.xpath("//button[@class='fhs-btn-login']");

        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();
        // Verify login button disable
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());

        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("long@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("123456789");
        sleepInSecond(1);
        // Verify login button enable
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        driver.navigate().refresh();
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        // Remove disable attribute of login button
        jsExcutor.executeScript("arguments[0].removeAttribute('disabled');",driver.findElement(loginButton));
        sleepInSecond(2);

        // Verify login button with background = red
        String rgbaColor = driver.findElement(loginButton).getCssValue("background-color");
        System.out.println("RGBA = " + rgbaColor);
        String hexColor = Color.fromString(rgbaColor).asHex().toUpperCase(Locale.ROOT);
        System.out.println("Hex = " + hexColor);
        Assert.assertEquals(hexColor,"#C92127");

        driver.findElement(loginButton).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']" +
                "//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']" +
                "//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
    }
    @Test
    public void TC_02_Radio_Button_Default(){
        // the Input: click & verify with this card -> Default
        // nonclick by input because it's hidden -> Custom
        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        By petronTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        // Verify button petroTwo deselected
        Assert.assertFalse(driver.findElement(petronTwo).isSelected());
        driver.findElement(petronTwo).click();
        sleepInSecond(2);
        // Verify button petroTwo selected
        Assert.assertTrue(driver.findElement(petronTwo).isSelected());

        driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")).click();
        Assert.assertFalse(driver.findElement(petronTwo).isSelected());
        Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Diesel, 103kW']/preceding-sibling::input")).isSelected());

        Assert.assertFalse(driver.findElement(By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input")).isEnabled());

    }
    @Test
    public void TC_03_Checkbox_Default(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By rearsideCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
        CheckToCheckbox(rearsideCheckbox);
        Assert.assertTrue(driver.findElement(rearsideCheckbox).isSelected());

        By lugageCheckbox = By.xpath("//label[text()='Luggage compartment cover']/preceding-sibling::input");
        // Verify checkbox is deselected
        Assert.assertFalse(driver.findElement(lugageCheckbox).isSelected());

        driver.findElement(lugageCheckbox).click();
        sleepInSecond(2);
        // Verify checkbox is selected
        Assert.assertTrue(driver.findElement(lugageCheckbox).isSelected());

        UnCheckToCheckbox(lugageCheckbox);
        sleepInSecond(2);
        // Verify checkbox is deselected
        Assert.assertFalse(driver.findElement(lugageCheckbox).isSelected());

    }

    public void  CheckToCheckbox(By by){
        if(!driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    public void  UnCheckToCheckbox(By by){
        if(driver.findElement(by).isSelected()){
            driver.findElement(by).click();
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
