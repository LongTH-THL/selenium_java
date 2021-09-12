package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class BaiTap_03_Browser{
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("http://live.demoguru99.com/");
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Verify_Url(){
        // Click to My Account Button
        WebElement myaccountButton = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myaccountButton.click();
        Assert.assertTrue(driver.findElement(By.xpath("//button[@title='Login']")).isDisplayed());
        // Verify LoginPage
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageUrl,"http://live.demoguru99.com/index.php/customer/account/login/");
        // Click button Creat account
        WebElement creataccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        creataccountButton.click();
        // Verify Register Page
        String registerUrl = driver.getCurrentUrl();
        Assert.assertEquals(registerUrl,"http://live.demoguru99.com/index.php/customer/account/create/");
    }

    @Test
    public void TC_02_Verify_Title(){
        driver.navigate().back();
        // Verify LoginPage
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageUrl,"http://live.demoguru99.com/index.php/customer/account/login/");
        // Verify LoginTitle
        String loginPageTitle = driver.getTitle();
        Assert.assertEquals(loginPageTitle,"Customer Login");
        // Click button Creat account
        WebElement creataccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        creataccountButton.click();
        // Verify CreatAccount Title
        String creatAccountPageTitle = driver.getTitle();
        Assert.assertEquals(creatAccountPageTitle,"Create New Customer Account");
    }

    @Test
    public void TC_03_Navigate_Function(){
        // Back to Login Page
        driver.navigate().back();
        // Click button Creat account
        WebElement creataccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        creataccountButton.click();
        // Verify Register Page
        String registerUrl = driver.getCurrentUrl();
        Assert.assertEquals(registerUrl,"http://live.demoguru99.com/index.php/customer/account/create/");
        // Back to Login Page
        driver.navigate().back();
        // Verify LoginPage
        String pageUrl = driver.getCurrentUrl();
        Assert.assertEquals(pageUrl,"http://live.demoguru99.com/index.php/customer/account/login/");
        // Forward to Register Page
        driver.navigate().forward();
    }

    @Test
    public void TC_04_Get_Page_Source_Code(){
        driver.navigate().back();
        // Click to My Account Button
        WebElement myAccountButton = driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']"));
        myAccountButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Get Page source LoginPage
        String loginPageSource = driver.getPageSource();
        // Verify LoginSourcePageTitle
        Assert.assertTrue(loginPageSource.contains("LOGIN OR CREATE AN ACCOUNT"));
        // Click button Creat account
        WebElement creatAccountButton = driver.findElement(By.xpath("//a[@title='Create an Account']"));
        creatAccountButton.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Get Page Source CreatAccount Page
        String creatAccountPageSource = driver.getPageSource();
        Assert.assertTrue(creatAccountPageSource.contains("Create an Account"));
    }


    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}