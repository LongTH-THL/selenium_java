package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_07_Textbox_TextArea {
    WebDriver driver;
    String loginPageUrl, userId, password, name, gender, dayOfBirthInput, dayOfBirthOutput, addressInput, addressOutput, city, state, pin, phone, email, customerID;
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
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();

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
        email ="trump" + randomData() + "@gmail.net";

        editAddressInput = "137/217\nTran Phu\nHa Dong";
        editAddressOutput = "137/217 Tran Phu Ha Dong";
        editCity = "Ha Tay";
        editState = "Ha Noi";
        editPin = "123456";
        editPhone = "0453465879";
        editEmail = "long" + randomData() + "@gmail.com";

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://demo.guru99.com/v4/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Registor() {
        loginPageUrl = driver.getCurrentUrl();
        driver.findElement(By.xpath("//a[text()='here']")).click();
        driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
        driver.findElement(By.name("btnLogin")).click();

        userId = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
        password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
    }

    @Test
    public void TC_02_Login() {
        driver.get(loginPageUrl);
        driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userId);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());

    }

    @Test
    public void TC_03_New_Customer() {
        driver.findElement(By.xpath("//a[text()='New Customer']")).click();
        driver.findElement(nameTextboxBy).sendKeys(name);
        driver.findElement(genderRadioBy).click();
        driver.findElement(dayofbirthTextboxBy).sendKeys(dayOfBirthInput);
        driver.findElement(addressTextareaBy).sendKeys(addressInput);
        driver.findElement(cityTextboxBy).sendKeys(city);
        driver.findElement(stateTextboxBy).sendKeys(state);
        driver.findElement(pinTextboxBy).sendKeys(pin);
        driver.findElement(phoneTextboxBy).sendKeys(phone);
        driver.findElement(emailTextboxBy).sendKeys(email);
        driver.findElement(passwordTextboxBy).sendKeys(password);
        driver.findElement(By.name("sub")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
        String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertEquals(customerName,name);
        String genderText = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        Assert.assertEquals(genderText,gender);
        String dayOfBirth = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        Assert.assertEquals(dayOfBirth,dayOfBirthOutput);
        String addressText = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertEquals(addressText,addressOutput);
        String cityText = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertEquals(cityText,city);
        String stateText = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertEquals(stateText,state);
        String pinText = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertEquals(pinText,pin);
        String phoneText = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertEquals(phoneText,phone);
        String emailText = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertEquals(emailText,email);

        customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

    }

    @Test
    public void TC_04_Edit_Customer() {
        driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
        driver.findElement(By.name("cusid")).sendKeys(customerID);
        driver.findElement(By.name("AccSubmit")).click();

        Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"),name);
        Assert.assertEquals(driver.findElement(genderTextboxBy).getAttribute("value"),gender);
        Assert.assertEquals(driver.findElement(dayofbirthTextboxBy).getAttribute("value"),dayOfBirthOutput);
        Assert.assertEquals(driver.findElement(addressTextareaBy).getAttribute("value"),addressInput);
        Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"),city);
        Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"),state);
        Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"),pin);
        Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"),phone);
        Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"),email);

        driver.findElement(addressTextareaBy).clear();
        driver.findElement(addressTextareaBy).sendKeys(editAddressInput);
        driver.findElement(cityTextboxBy).clear();
        driver.findElement(cityTextboxBy).sendKeys(editCity);
        driver.findElement(stateTextboxBy).clear();
        driver.findElement(stateTextboxBy).sendKeys(editState);
        driver.findElement(pinTextboxBy).clear();
        driver.findElement(pinTextboxBy).sendKeys(editPin);
        driver.findElement(phoneTextboxBy).clear();
        driver.findElement(phoneTextboxBy).sendKeys(editPhone);
        driver.findElement(emailTextboxBy).clear();
        driver.findElement(emailTextboxBy).sendKeys(editEmail);
        driver.findElement(By.name("sub")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());
        String customerIdText = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
        Assert.assertEquals(customerIdText, customerID);
        String customerName = driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText();
        Assert.assertEquals(customerName,name);
        String genderText = driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText();
        Assert.assertEquals(genderText,gender);
        String dayOfBirth = driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText();
        Assert.assertEquals(dayOfBirth,dayOfBirthOutput);
        String addressText = driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText();
        Assert.assertEquals(addressText,editAddressOutput);
        String cityText = driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText();
        Assert.assertEquals(cityText,editCity);
        String stateText = driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText();
        Assert.assertEquals(stateText,editState);
        String pinText = driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText();
        Assert.assertEquals(pinText,editPin);
        String phoneText = driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText();
        Assert.assertEquals(phoneText,editPhone);
        String emailText = driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText();
        Assert.assertEquals(emailText,editEmail);
    }


    public int randomData(){
        Random random = new Random();
        return random.nextInt(999999);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
