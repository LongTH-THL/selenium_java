package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_01_Check_Environment {

    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {   //comment tung step vao
        // Bước 1: Lấy đường dẫn chromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/long/Downloads/selenium/chromedriver");
        // Bước 2: Khởi tạo
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // Bước 3: Truy cập URL
        driver.get("https://demo.guru99.com/v4/");
    }

    @Test
    public void TC_01_ValidateCurrentUrl() throws InterruptedException {
        // Bước 1: Đợi trang web load 3s
        Thread.sleep(3000);
        // Bước 2: Lấy URL hiện tại
        String loginPageUrl = driver.getCurrentUrl();
        System.out.println("login page" + loginPageUrl);
        // Bước 3: So sánh URL hiển thị với URL chính xác
        Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
    }

    @Test
    public void TC_02_ValidatePageTitle()
    {
        // Bước 1: Lấy Title của màn hình
        String loginPageTitle = driver.getTitle();
        // Bước 2: So sánh Title với Title đúng
        Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
    }

    @Test
    public void TC_03_LoginFormDisplayed()
    {
        // Kiểm tra xem form Login có hiển thị hay không
        Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
    }

    @AfterClass
    public void afterClass()
    {

        driver.quit();
    }
}