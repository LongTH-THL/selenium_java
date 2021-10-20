package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_18_JsExcutor {
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    String email;
    Select select;

    String loginPageUrl, userId, password, name, gender, dayOfBirthInput, dayOfBirthOutput, addressInput, addressOutput, city, state, pin, phone, email01, customerID;
    String editAddressInput,editAddressOutput, editCity, editState, editPin, editPhone, editEmail;

    By nameTextboxBy = By.name("name");
    By genderRadioBy = By.xpath("//input[@value='f']");
    By genderTextboxBy = By.name("gender");
    By dayofbirthTextboxBy = By.name("dob");
    By addressTextareaBy = By.name("addr");
    By cityTextboxBy = By.name("city");
    By stateTextboxBy = By.name("state");
    By pinTextboxBy = By.name("pinno");
    By phoneTextboxBy = By.name("telephoneno");
    By emailTextboxBy = By.name("emailid");
    By passwordTextboxBy = By.name("password");

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        jsExecutor = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        email01 = "automationFC" + randomData() +"@gmail.com";

        name = "Donal Trump";
        gender = "female";
        dayOfBirthInput = "01/01/1990";
        dayOfBirthOutput = "1990-01-01";
        addressInput = "136\nTrieu Khuc\nHa Noi";
        addressOutput = "136 Trieu Khuc Ha Noi";
        city = "Ha Noi";
        state = "Viet Nam";
        pin = "226644";
        phone = "0387625378";
        email01 ="trump" + randomData() + "@gmail.net";
    }

    @Test
    public void TC_01_Live_Guru_99(){
        navigateToUrlByJS("http://live.techpanda.org/");
        String liveGuruDomain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(liveGuruDomain,"live.techpanda.org");

        String liveGuruURL = (String) executeForBrowser("return document.URL;");
        Assert.assertEquals(liveGuruURL,"http://live.techpanda.org/");


        hightlightElement("//a[text()='Mobile']");
        clickToElementByJS("//a[text()='Mobile']");

        clickToElementByJS("//a[@title='Samsung Galaxy']/following-sibling::div//span[text()='Add to Cart']");

        scrollToBottomPage();
        clickToElementByJS("//a[text()='Customer Service']");

        String cutomerServiceTitle = (String) executeForBrowser("return document.title;");
        Assert.assertEquals(cutomerServiceTitle,"Customer Service");

        hightlightElement("//input[@id='newsletter']");
        scrollToElementOnDown("//input[@id='newsletter']");

        sendkeyToElementByJS("//input[@id='newsletter']", email01);
        hightlightElement("//button[@title='Subscribe']");
        clickToElementByJS("//button[@title='Subscribe']");

        Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

        navigateToUrlByJS("http://demo.guru99.com/v4/");
        String bankGuru99Domain = (String) executeForBrowser("return document.domain;");
        Assert.assertEquals(bankGuru99Domain,"demo.guru99.com");

    }
    @Test
    public void TC_02_HTML5_1(){
        driver.get("https://automationfc.github.io/html5/index.html");
        String validationMess;
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
        // Name
        validationMess = getElementValidationMessage("//input[@name='fname']");
        Assert.assertEquals(validationMess,"Please fill out this field.");
        // Sendkey Name
        driver.findElement(By.xpath("//input[@name='fname']")).sendKeys("Automaiton");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
        // Pass
        validationMess = getElementValidationMessage("//input[@name='pass']");
        Assert.assertEquals(validationMess,"Please fill out this field.");
        // Sendkey Pass
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
        // Email
        validationMess = getElementValidationMessage("//input[@name='em']");
        Assert.assertEquals(validationMess,"Please fill out this field.");
        // Sendkey Email
        driver.findElement(By.xpath("//input[@name='em']")).sendKeys("tranhailong@");
        driver.findElement(By.xpath("//input[@name='submit-btn']")).click();
        // Email
        validationMess = getElementValidationMessage("//input[@name='em']");
        Assert.assertEquals(validationMess,"Please enter a part following '@'. 'tranhailong@' is incomplete.");
        // Sendkey Email
        driver.findElement(By.xpath("//input[@name='em']")).clear();
        driver.findElement(By.xpath("//input[@name='em']")).sendKeys(email01);
        // Address
        validationMess = getElementValidationMessage("//select");
        Assert.assertEquals(validationMess,"Please select an item in the list.");

        select = new Select(driver.findElement(By.xpath("//select")));
        select.selectByVisibleText("HA NOI");

    }
    @Test
    public void TC_03_HTML_5_2(){
        driver.get("https://login.ubuntu.com/");

        if(driver.findElement(By.xpath("//div[@aria-labelledby='cookie-policy-title']")).isDisplayed()){
            driver.findElement(By.xpath("//button[text()='Accept all and visit site']")).click();
        }

        driver.findElement(By.xpath("//div[@class='login-form']//input[@name='email']")).sendKeys("a");
        driver.findElement(By.xpath("//div[@class='login-form']//button[@type='submit']")).click();

        String validationMess = getElementValidationMessage("//div[@class='login-form']//input[@name='email']");
        Assert.assertEquals(validationMess,"Please include an '@' in the email address. 'a' is missing an '@'.");
    }

    @Test
    public void TC_04_RemoveAttribute(){
        driver.get("http://demo.guru99.com/v4/");
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email01);
        driver.findElement(By.name("btnLogin")).click();

        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

        driver.get(loginPageUrl);
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(nameTextboxBy).sendKeys(name);
        driver.findElement(genderRadioBy).click();
        removeAttributeInDOM("//input[@id='dob']","type");
        sleepInSecond(5);
        driver.findElement(dayofbirthTextboxBy).sendKeys(dayOfBirthInput);
        driver.findElement(addressTextareaBy).sendKeys(addressInput);
        driver.findElement(cityTextboxBy).sendKeys(city);
        driver.findElement(stateTextboxBy).sendKeys(state);
        driver.findElement(pinTextboxBy).sendKeys(pin);
        driver.findElement(phoneTextboxBy).sendKeys(phone);
        driver.findElement(emailTextboxBy).sendKeys(email01);
        driver.findElement(passwordTextboxBy).sendKeys(password);
        driver.findElement(By.name("sub")).click();

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

    public Object executeForBrowser(String javaScript) {
        return jsExecutor.executeScript(javaScript);
    }

    public String getInnerText() {
        return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
    }

    public boolean areExpectedTextInInnerText(String textExpected) {
        String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage() {
        jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(String url) {
        jsExecutor.executeScript("window.location = '" + url + "'");
    }

    public void hightlightElement(String locator) {
        WebElement element = getElement(locator);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(String locator) {
        jsExecutor.executeScript("arguments[0].click();", getElement(locator));
    }

    public void scrollToElementOnTop(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
    }

    public void scrollToElementOnDown(String locator) {
        jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
    }

    public void sendkeyToElementByJS(String locator, String value) {
        jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
    }

    public void removeAttributeInDOM(String locator, String attributeRemove) {
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
    }

    public String getElementValidationMessage(String locator) {
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
    }

    public boolean isImageLoaded(String locator) {
        boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getElement(locator));
        if (status) {
            return true;
        }
        return false;
    }

    public WebElement getElement(String locator) {
        return driver.findElement(By.xpath(locator));
    }
}
