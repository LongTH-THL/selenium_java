package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.mustache.Value;

import java.util.concurrent.TimeUnit;

public class BaiTap_04_Element {
    WebDriver driver;
    // AutomatinFC
    By emailTextboxBy = By.xpath("//input[@name='user_email']");
    By ageUnder18RadioBy = By.xpath("//input[@id='under_18']");
    By educationTextAreaBy = By.xpath("//textarea[@name='user_edu']");
    By user5TextBy = By.xpath("//h5[text()='Name: User1']");
    By jobRule01DropdownBy = By.xpath("//select[@name=\"user_job1\"]");
    By jobRule02DropdownBy = By.xpath("//select[@name=\"user_job2\"]");
    By sliderBar01By = By.xpath("//input[@name='slider-1']");
    By developmentCheckboxBy = By.xpath("//input[@id='development']");

    By passwordTextboxBy = By.xpath("//input[@name='user_pass']");
    By ageRadioDisableBy = By.xpath("//input[@id='radio-disabled']");
    By biographyTextAreaBy = By.xpath("//textarea[@name='user_bio']");
    By jobRule03DropdownBy = By.xpath("//select[@name='user_job3']");
    By sliderBar02By = By.xpath("//input[@name='slider-2']");

    // MailChimp
    By emailTextboxMailChimpBy = By.xpath("//input[@type='email']");
    By usernameTextboxMailChimpBy = By.xpath("//input[@name='username']");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.manage().window().maximize();
    }


    @Test
    public void TC_01_Verify_Element_IsDisplay(){
        // Check email display on page
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@name='user_email']"));
        if (emailTextbox.isDisplayed()){
            emailTextbox.sendKeys("Automation Testing");
            System.out.println("Email textbox is display");
        }else {
            System.out.println("Email textbox is not display");
        };

        // Check Age (under 18) display on page
        WebElement ageUnder18Radio = driver.findElement(By.xpath("//input[@id='under_18']"));
        if (ageUnder18Radio.isDisplayed()){
            ageUnder18Radio.click();
            System.out.println("Age under 18 is display");
        } else {
            System.out.println("Age under 18 is not display");
        }

        // Check Education display on page
        WebElement educationTextbox = driver.findElement(By.xpath("//textarea[@name='user_edu']"));
        if (educationTextbox.isDisplayed()){
            educationTextbox.sendKeys("Automation Testing");
            System.out.println("Education textbox is display");
        } else {
            System.out.println("Educaiton textbox is not display");
        }

        // Check JobRule 01 display to page
        WebElement jobRule01Dropdown = driver.findElement(By.xpath("//select[@name=\"user_job1\"]"));
        Assert.assertTrue(jobRule01Dropdown.isDisplayed());

        // Check JobRule 02 display to page
        WebElement jobRule02Dropdown = driver.findElement(By.xpath("//select[@name=\"user_job2\"]"));
        Assert.assertTrue(jobRule02Dropdown.isDisplayed());

        // Check User5 not display to page
        WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User1']"));
        if (user5Text.isDisplayed()){
            System.out.println("User 5 text is display");
        } else {
            System.out.println("User 5 text is not display");
        }

    }

    @Test
    public void TC_02_Verify_Element_IsDisplay_Refactor(){

        if (isElementDisplay(emailTextboxBy)){
            sendKeyToElement(emailTextboxBy,"Automation Testing");
        }
        if (isElementDisplay(ageUnder18RadioBy)){
            clickToElement(ageUnder18RadioBy);
        }
        if (isElementDisplay(educationTextAreaBy)){
            sendKeyToElement(educationTextAreaBy,"Automation Testing");
        }
        if (isElementDisplay(developmentCheckboxBy)){
            clickToElement(developmentCheckboxBy);
        }

        Assert.assertFalse(isElementDisplay(user5TextBy));
    }

    @Test
    public void TC_03_Verify_Elament_IsEnablr(){
        Assert.assertTrue(isElementEnable(emailTextboxBy));
        Assert.assertTrue(isElementEnable(educationTextAreaBy));
        Assert.assertTrue(isElementEnable(jobRule01DropdownBy));
        Assert.assertTrue(isElementEnable(jobRule02DropdownBy));
        Assert.assertTrue(isElementEnable(user5TextBy));
        Assert.assertTrue(isElementEnable(sliderBar01By));
        Assert.assertTrue(isElementEnable(ageUnder18RadioBy));

        Assert.assertFalse(isElementEnable(passwordTextboxBy));
        Assert.assertFalse(isElementEnable(ageRadioDisableBy));
        Assert.assertFalse(isElementEnable(biographyTextAreaBy));
        Assert.assertFalse(isElementEnable(jobRule03DropdownBy));
        Assert.assertFalse(isElementEnable(sliderBar02By));
    }

    @Test
    public void TC_04_Verify_Element_IsSelected(){
        Assert.assertTrue(isSelected(ageUnder18RadioBy));
        Assert.assertTrue(isSelected(developmentCheckboxBy));

        clickToElement(developmentCheckboxBy);
        Assert.assertFalse(isSelected(developmentCheckboxBy));
    }

    @Test
    public void TC_05_Verify_All(){
        driver.get("https://login.mailchimp.com/signup/");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        By passwordTextbox = By.xpath("//input[@name='password']");
        By signinButton = By.id("create-account");
        By newletterCheckbox = By.xpath("//input[@name='marketing_newsletter']");

        sendKeyToElement(emailTextboxMailChimpBy,"Automation@gamil.com");
        sendKeyToElement(usernameTextboxMailChimpBy,"Automation Testing");

        // Lowercase
        driver.findElement(passwordTextbox).sendKeys("auto");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed' and text() = 'One lowercase character']")).isDisplayed());
        // Expected -> Disable
        Assert.assertFalse(driver.findElement(signinButton).isEnabled());

        // Uppercase
        driver.findElements(passwordTextbox).clear();
        sendKeyToElement(passwordTextbox,"AUTO");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed' and text() = 'One uppercase character']")).isDisplayed());
        Assert.assertFalse(driver.findElement(signinButton).isEnabled());

        // Number
        driver.findElements(passwordTextbox).clear();
        sendKeyToElement(passwordTextbox,"1234");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text() = 'One number']")).isDisplayed());
        Assert.assertFalse(driver.findElement(signinButton).isEnabled());

        // 8 Char
        driver.findElements(passwordTextbox).clear();
        sendKeyToElement(passwordTextbox,"12345678");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed' and text() = '8 characters minimum']")).isDisplayed());
        Assert.assertFalse(driver.findElement(signinButton).isEnabled());

        // Special
        driver.findElements(passwordTextbox).clear();
        sendKeyToElement(passwordTextbox,".");
        Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']")).isDisplayed());
        Assert.assertFalse(driver.findElement(signinButton).isEnabled());

        // Full vali data
        driver.findElements(passwordTextbox).clear();
        sendKeyToElement(passwordTextbox,"Auto1234.");
        Assert.assertTrue(driver.findElement(signinButton).isEnabled());

        // Checkbox
        driver.findElement(newletterCheckbox).click();
        Assert.assertTrue(driver.findElement(newletterCheckbox).isSelected());
    }




    public boolean isElementDisplay(By by){
        WebElement element = driver.findElement(by);
        if(element.isDisplayed()){
            System.out.println("Element["+ by +"] is Display");
            return true;
        } else {
            System.out.println("Element ["+ by +"] is not Display");
            return  false;
        }
    }

    public boolean isElementEnable(By by){
        WebElement element = driver.findElement(by);
        if(element.isEnabled()){
            System.out.println("Element["+ by +"] is Enable");
            return true;
        } else {
            System.out.println("Element ["+ by +"] is Disable");
            return  false;
        }
    }

    public boolean isSelected(By by){
        WebElement element = driver.findElement(by);
        if(element.isSelected()){
            System.out.println("Element["+ by +"] is Selected");
            return true;
        } else {
            System.out.println("Element ["+ by +"] is not Selected");
            return  false;
        }
    }

    public void sendKeyToElement(By by, String value){
        WebElement element = driver.findElement(by);
        element.clear();
        element.sendKeys(value);
    }

    public void clickToElement(By by){
        WebElement element = driver.findElement(by);
        element.click();
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}