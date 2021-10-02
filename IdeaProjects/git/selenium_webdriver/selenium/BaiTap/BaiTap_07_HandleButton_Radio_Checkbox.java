package BaiTap;

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

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaiTap_07_HandleButton_Radio_Checkbox {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Button_JS(){
        driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
        By loginButton = By.xpath("//button[@class='fhs-btn-login']");
        // Verify button Login Disable
        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        By usernameTextbox = By.id("login_username");
        By passwordTextbox = By.id("login_password");

        driver.findElement(usernameTextbox).sendKeys("automationFc@gmail.com");
        driver.findElement(passwordTextbox).sendKeys("123456789");
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());

        driver.navigate().refresh();
        driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();

        // remove disable attribute login button by Js
        jsExecutor.executeScript("arguments[0].removeAttribute('disabled');",driver.findElement(loginButton));
        sleepInSecond(2);
        // verify button log co background = red
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
    public void TC_02_Checkbox_RadioButton(){
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        By dualzoneCheckbox = By.xpath("//input[@id='eq5']");
        // verify checkbox deselected
        Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());

        CheckToCheckbox(dualzoneCheckbox);
        Assert.assertTrue(driver.findElement(dualzoneCheckbox).isSelected());
        sleepInSecond(2);
        UnCheckToCheckbox(dualzoneCheckbox);
        Assert.assertFalse(driver.findElement(dualzoneCheckbox).isSelected());
        sleepInSecond(2);

        driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
        sleepInSecond(2);
        By petroTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
        Assert.assertFalse(driver.findElement(petroTwo).isSelected());
        driver.findElement(petroTwo).click();
        Assert.assertTrue(driver.findElement(petroTwo).isSelected());
    }

    @Test
    public void TC_03_Custom_Checkbox_RadioButton(){
        driver.get("https://material.angular.io/components/radio/examples");
        // Su dung click cua JavaScript
        By summerRadioInput = By.xpath("//input[@value='Summer']");
        ClickToElementByJs(summerRadioInput);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(summerRadioInput).isSelected());

        driver.get("https://material.angular.io/components/checkbox/examples");
        By checkedCheckbox = By.xpath("//span[text()='Checked']/preceding-sibling::span/input");
        By indeterminateCheckbox =By.xpath("//span[text()='Indeterminate']/preceding-sibling::span/input");

        CheckToCheckboxByJs(checkedCheckbox);
        CheckToCheckboxByJs(indeterminateCheckbox);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertTrue(driver.findElement(indeterminateCheckbox).isSelected());

        UnCheckToCheckboxByJs(checkedCheckbox);
        UnCheckToCheckboxByJs(indeterminateCheckbox);
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(checkedCheckbox).isSelected());
        Assert.assertFalse(driver.findElement(indeterminateCheckbox).isSelected());
    }

    @Test
    public void TC_04_Custom_Checkbox(){
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
        By canthoRadio = By.xpath("//div[@data-value='Cần Thơ']");
        Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"),"false");
        CheckToCheckbox(canthoRadio);
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(canthoRadio).getAttribute("aria-checked"),"true");
        Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());

        List<WebElement> checkboxes = driver.findElements(By.xpath("//div[contains(@data-answer-value,'Quảng ')]"));
        for (WebElement checkbox:checkboxes){
            checkbox.click();
            sleepInSecond(2);
        }

        // verify
        for (WebElement checkbox:checkboxes){
            Assert.assertEquals(checkbox.getAttribute("aria-checked"),"true");
        }
    }

    public void CheckToCheckbox(By by){
        if(!driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    public void UnCheckToCheckbox(By by){
        if(driver.findElement(by).isSelected()){
            driver.findElement(by).click();
        }
    }

    public void CheckToCheckboxByJs(By by){
        if(!driver.findElement(by).isSelected()){
            jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
        }
    }

    public void UnCheckToCheckboxByJs(By by){
        if (driver.findElement(by).isSelected()){
            jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
        }
    }

    public void ClickToElementByJs(By by){
        jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
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
