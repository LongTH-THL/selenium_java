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
public class BaiTap_01_Selenium_Locator {

    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://alada.vn/tai-khoan/dang-ky.html");
        driver.manage().window().maximize();
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

    @Test
    public void TC_02_Register_With_Invalid_Email(){
        // click to Name
        WebElement Name = driver.findElement(By.xpath("//input[@name='txtFirstname']"));
        Name.click();
        Name.sendKeys("Tran Hai Long");
        // click to Email
        WebElement Email = driver.findElement(By.xpath("//input[@name='txtEmail']"));
        Email.click();
        Email.sendKeys("1234@1234.12@");
        // click to CEmail
        WebElement CEmail = driver.findElement(By.xpath("//input[@name='txtCEmail']"));
        CEmail.click();
        CEmail.sendKeys("1234@1234.12@");
        // click to Pass
        WebElement Pass = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        Pass.click();
        Pass.sendKeys("123456789");
        // clickCPass
        WebElement CPass = driver.findElement(By.xpath("//input[@name='txtCPassword']"));
        CPass.click();
        CPass.sendKeys("123456789");
        // click Phone
        WebElement Phone = driver.findElement(By.xpath("//input[@name='txtPhone']"));
        Phone.click();
        Phone.sendKeys("0384453261");
        // click Button dang ky
        WebElement buttonRegister = driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
        buttonRegister.click();
        // kiem tra noti thong bao
        // Mail
        WebElement notiEmail = driver.findElement(By.xpath("//label[@id='txtEmail-error']"));
        String TextMail =notiEmail.getText();
        Assert.assertEquals(TextMail,"Vui lòng nhập email hợp lệ");
        // Confirm Mail
        WebElement notiConfirmMail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        String TextCMail = notiConfirmMail.getText();
        Assert.assertEquals(TextCMail,"Email nhập lại không đúng");
    }
    @Test
    public void TC_03_Register_With_Incorect_Confirm_Email(){

        // click to Email
        WebElement Email = driver.findElement(By.xpath("//input[@name='txtEmail']"));
        Email.clear();
        Email.click();
        Email.sendKeys("tranhailong_t59@hus.edu.vn");
        // click to CEmail
        WebElement CEmail = driver.findElement(By.xpath("//input[@name='txtCEmail']"));
        CEmail.clear();
        CEmail.click();
        CEmail.sendKeys("1234@gmail.com");

        // click Button dang ky
        WebElement buttonRegister = driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
        buttonRegister.click();
        // Kiem tra noti thong bao
        // Confirm Mail
        WebElement notiConfirmMail = driver.findElement(By.xpath("//label[@id='txtCEmail-error']"));
        String TextCMail = notiConfirmMail.getText();
        Assert.assertEquals(TextCMail,"Email nhập lại không đúng");
    }

    @Test
    public void TC_04_TC_03_Register_With_Password(){

        // click to CEmail
        WebElement CEmail = driver.findElement(By.xpath("//input[@name='txtCEmail']"));
        CEmail.clear();
        CEmail.click();
        CEmail.sendKeys("tranhailong_t59@hus.edu.vn");
        // click to Pass
        WebElement Pass = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        Pass.clear();
        Pass.click();
        Pass.sendKeys("12345");
        // clickCPass
        WebElement CPass = driver.findElement(By.xpath("//input[@name='txtCPassword']"));
        CPass.clear();
        CPass.click();
        CPass.sendKeys("12345");

        // click Button dang ky
        WebElement buttonRegister = driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
        buttonRegister.click();
        // Kiem tra noti thong bao
        // Pass < 6
        WebElement notiPass = driver.findElement(By.xpath("//label[@id='txtPassword-error']"));
        String TextPass = notiPass.getText();
        Assert.assertEquals(TextPass,"Mật khẩu phải có ít nhất 6 ký tự");
        // Confirm Pass < 6
        WebElement notiConfirmPass = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        String TextCPass = notiConfirmPass.getText();
        Assert.assertEquals(TextCPass,"Mật khẩu phải có ít nhất 6 ký tự");
    }

    @Test
    public void TC_05_Register_With_Incorect_Confirm_Password(){
        // click to Pass
        WebElement Pass = driver.findElement(By.xpath("//input[@name='txtPassword']"));
        Pass.clear();
        Pass.click();
        Pass.sendKeys("123456789");
        // clickCPass
        WebElement CPass = driver.findElement(By.xpath("//input[@name='txtCPassword']"));
        CPass.clear();
        CPass.click();
        CPass.sendKeys("1234567");

        // click Button dang ky
        WebElement buttonRegister = driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
        buttonRegister.click();
        // Kiem tra noti thong bao
        // Confirm Pass
        WebElement notiConfirmPass = driver.findElement(By.xpath("//label[@id='txtCPassword-error']"));
        String TextCPass = notiConfirmPass.getText();
        Assert.assertEquals(TextCPass,"Mật khẩu bạn nhập không khớp");
    }

    @Test
    public void TC_06_Register_With_Invalid_Phone_Number(){
        // clickCPass
        WebElement CPass = driver.findElement(By.xpath("//input[@name='txtCPassword']"));
        CPass.clear();
        CPass.click();
        CPass.sendKeys("123456789");
        // click Phone
        WebElement Phone = driver.findElement(By.xpath("//input[@name='txtPhone']"));
        Phone.click();
        Phone.sendKeys("0384abcd");
        // click Button dang ky
        WebElement buttonRegister = driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']"));
        buttonRegister.click();
        // Kiem tra noti thong bao
        // Noti phone
        WebElement notiPhone = driver.findElement(By.xpath("//label[@id='txtPhone-error']"));
        String TextCPass = notiPhone.getText();
        Assert.assertEquals(TextCPass,"Vui lòng nhập con số");
    }




    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
