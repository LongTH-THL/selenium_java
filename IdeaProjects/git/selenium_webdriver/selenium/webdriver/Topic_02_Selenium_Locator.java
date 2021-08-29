package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
public class Topic_02_Selenium_Locator {

    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
    }

    @Test
    public void TC_01_TestErrorMessage(){
        //Buoc 1: click button dang ky
        WebElement buttonRegister = driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
        buttonRegister.click();
        //Buoc 2: gettext cac thong bao
        // Name
        WebElement notiName = driver.findElement(By.xpath("//label[@id='txtFirstname-error']"));
        String TextName = notiName.getText();
        Assert.assertEquals(TextName,"Vui lòng nhập họ tên");
        // Mail
        WebElement notiEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        String TextMail =notiEmail.getText();
        Assert.assertEquals(TextMail,"Vui lòng nhập email");
        // Confirm Mail
        WebElement notiConfirmMail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        String TextCMail = notiConfirmMail.getText();
        Assert.assertEquals(TextCMail,"Vui lòng nhập lại địa chỉ email");
        // Password
        WebElement notiPass = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        String TextPass = notiPass.getText();
        Assert.assertEquals(TextPass,"Vui lòng nhập mật khẩu");
        // Confirm Pass
        WebElement notiCPass =driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        String TextCPass = notiCPass.getText();
        Assert.assertEquals(TextCPass,"Vui lòng nhập lại mật khẩu");
        // Phone
        WebElement notiPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        String TextPhone = notiPhone.getText();
        Assert.assertEquals(TextPhone,"Vui lòng nhập số điện thoại.");
    }








    @AfterClass
    public void afterClass()
    {

        driver.quit();
    }
}
