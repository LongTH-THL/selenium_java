package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
public class BaiTap_02_Selenium_Locator {

    WebDriver driver;
    String email;
    String firstName = "Hai";
    String lastName = "Long";
    String passWord = "123456789";

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.manage().window().maximize();
        email = "tranhailong" + randomData() + "@gmail.com";
    }
    @Test
    public void TC_01_LoginWithEmpty_Email_and_Password(){
        // Click to My Account Button
        WebElement myaccountButton = driver.findElement(By.xpath("(//a[@title='My Account'])[2]"));
        myaccountButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@title='Login']")).isDisplayed());
        // Click to login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
        loginButton.click();
        // Noti Email
        WebElement notiEmail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']"));
        Assert.assertTrue(notiEmail.isDisplayed());
        String errorMail = notiEmail.getText();
        Assert.assertEquals(errorMail,"This is a required field.");
        //NotiPassWord
        WebElement notiPassword = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']"));
        Assert.assertTrue(notiPassword.isDisplayed());
        String errorPass = notiPassword.getText();
        Assert.assertEquals(errorPass,"This is a required field.");
    }
    @Test
    public void TC_02_LoginWith_Invalid_Email(){
        // Invalid Email
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@title='Email Address']"));
        emailTextbox.click();
        emailTextbox.sendKeys("123@1234.123");
        // Valid Password
        WebElement passwordTexbox =driver.findElement(By.xpath("//input[@title='Password']"));
        passwordTexbox.click();
        passwordTexbox.sendKeys(passWord);
        // Click to login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
        loginButton.click();
        // Noti Email
        WebElement notiEMail = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']"));
        Assert.assertTrue(notiEMail.isDisplayed());
        String errorMail = notiEMail.getText();
        Assert.assertEquals(errorMail,"Please enter a valid email address. For example johndoe@domain.com.");
    }

    @Test
    public void TC_03_LoginWithPassword_5_Character(){
        // Valid Email
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@title='Email Address']"));
        emailTextbox.clear();
        emailTextbox.click();
        emailTextbox.sendKeys(email);

        // Invalid Password <5 character
        WebElement passwordTextbox =driver.findElement(By.xpath("//input[@title='Password']"));
        passwordTextbox.clear();
        passwordTextbox.click();
        passwordTextbox.sendKeys("12345");
        // Click to login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
        loginButton.click();
        //Noti Pass
        WebElement errorPassword = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']"));
        Assert.assertTrue(errorPassword.isDisplayed());
        String errorPass = errorPassword.getText();
        Assert.assertEquals(errorPass,"Please enter 6 or more characters without leading or trailing spaces.");
    }

    @Test
    public void TC_04_LoginWithIncorrect_Email_Password(){
        // Valid Email
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@title='Email Address']"));
        emailTextbox.clear();
        emailTextbox.click();
        emailTextbox.sendKeys("automaiton@gmail.com");

        // Invalid Password <5 character
        WebElement passwordTextbox =driver.findElement(By.xpath("//input[@title='Password']"));
        passwordTextbox.clear();
        passwordTextbox.click();
        passwordTextbox.sendKeys("123123123");
        // Click to login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
        loginButton.click();
        // Noti
        WebElement errorPage = driver.findElement(By.xpath("//li[@class='error-msg']//span"));
        Assert.assertTrue(errorPage.isDisplayed());
        String errorPageText = errorPage.getText();
        Assert.assertEquals(errorPageText,"Invalid login or password.");
    }

    @Test
    public void TC_05_CreatNew_Account(){
        // Click button Creat account
        WebElement creataccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        creataccountButton.click();
        // FirstName
        WebElement firstnameTextbox = driver.findElement(By.xpath("//input[@title='First Name']"));
        firstnameTextbox.click();
        firstnameTextbox.sendKeys(firstName);
        // Lastname
        WebElement lastnameTextbox = driver.findElement(By.xpath("//input[@title='Last Name']"));
        lastnameTextbox.click();
        lastnameTextbox.sendKeys(lastName);
        // Email
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='email_address']"));
        emailTextbox.click();
        emailTextbox.sendKeys(email);
        // Password
        WebElement passwordTextbox = driver.findElement(By.xpath("//input[@id='password']"));
        passwordTextbox.click();
        passwordTextbox.sendKeys("123456789");
        // ComfirmPass
        WebElement cpasswordTextbox = driver.findElement(By.xpath("//input[@id='confirmation']"));
        cpasswordTextbox.click();
        cpasswordTextbox.sendKeys("123456789");
        // Click button Register
        WebElement registerButton = driver.findElement(By.xpath("//button[@title='Register']"));
        registerButton.click();
        // Verify noti
        WebElement notiText = driver.findElement(By.xpath("//li[@class='success-msg']//span"));
        Assert.assertTrue( notiText.isDisplayed());
        String noti = notiText.getText();
        Assert.assertEquals(noti,"Thank you for registering with Main Website Store.");

        // Verify user
        WebElement fullName = driver.findElement(By.xpath("(//div[@class='box-content']//p)[1]"));
        String fullNameActual = fullName.getText();
        System.out.println("full name ==" + fullName.getText());
        Assert.assertTrue(fullNameActual.contains(firstName));
        Assert.assertTrue(fullNameActual.contains(lastName));
        Assert.assertTrue(fullNameActual.contains(email));

        // Logout
        WebElement accountButton = driver.findElement(By.xpath("//a[@class='skip-link skip-account']"));
        accountButton.click();
        WebElement logoutButton = driver.findElement(By.xpath("//a[@title='Log Out']"));
        logoutButton.click();
        //
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String homePageTitle = driver.getTitle();
        Assert.assertEquals(homePageTitle,"Home page");
    }

    @Test
    public void TC_06_LoginWithValid_Email_Password(){
        // Click to My Account Button
        WebElement myaccountButton = driver.findElement(By.xpath("(//a[@title='My Account'])[2]"));
        myaccountButton.click();
        // Valid Email TC_05
        WebElement emailTextbox = driver.findElement(By.xpath("//input[@title='Email Address']"));
        emailTextbox.clear();
        emailTextbox.click();
        emailTextbox.sendKeys(email);
        // Valid Password TC_05
        WebElement passwordTexbox =driver.findElement(By.xpath("//input[@title='Password']"));
        passwordTexbox.click();
        passwordTexbox.sendKeys(passWord);
        // Click to login button
        WebElement loginButton = driver.findElement(By.xpath("//button[@title='Login']"));
        loginButton.click();
        // Verify user
        WebElement fullName = driver.findElement(By.xpath("(//div[@class='box-content']//p)[1]"));
        String fullNameActual = fullName.getText();
        System.out.println("full name ==" + fullName.getText());
        Assert.assertTrue(fullNameActual.contains(firstName));
        Assert.assertTrue(fullNameActual.contains(lastName));
        Assert.assertTrue(fullNameActual.contains(email));


    }

    public int randomData(){
        Random random = new Random();
        return random.nextInt(999999);
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}