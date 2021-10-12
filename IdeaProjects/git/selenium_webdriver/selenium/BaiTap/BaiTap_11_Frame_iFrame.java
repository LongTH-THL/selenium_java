package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaiTap_11_Frame_iFrame {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_iFrame(){
        driver.get("https://kyna.vn/");
        // verify facebook page = 168k luot like ==> Khong chay duoc theo cach thong thuong :))))

        // Phải switch qua iframe mới dùng được =)))
        WebElement iFrame = driver.findElement(By.xpath("//div[@class='face-content']/iframe"));
        driver.switchTo().frame(iFrame);
        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(),"168K lượt thích");

        // Switch lại về trang web
        driver.switchTo().defaultContent();
        WebElement iFrameChat = driver.findElement(By.xpath("//div[@id='cs-live-chat']/iframe"));
        driver.switchTo().frame(iFrameChat);
        driver.findElement(By.xpath("//div[contains(@class,'button_bar')]")).click();

        // Nhập dữ liệu
        driver.findElement(By.xpath("//input[contains(@class,'input_name')]")).sendKeys("AutomationFc");
        driver.findElement(By.xpath("//input[contains(@class,'input_phone')]")).sendKeys("0123456789");
        driver.findElement(By.xpath("//select[@id='serviceSelect']")).click();

        // drop down
        select = new Select(driver.findElement(By.xpath("//select[@id='serviceSelect']")));
        // chon 1 item
        select.selectByVisibleText("TƯ VẤN TUYỂN SINH");

        driver.findElement(By.xpath("//input[@value='Gửi tin nhắn']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@ng-show='requiredContent == true']")).getText(),"Tin nhắn chưa được nhập");

        driver.switchTo().defaultContent();
        // Nhập dữ liệu để tìm kiếm Excel
        driver.findElement(By.xpath("//input[@id='live-search-bar']")).sendKeys("Excel");
        driver.findElement(By.xpath("//button[@class='search-button']")).click();

        // Kiểm tra dữ liệu được tìm kiếm có Excel
        List<WebElement> articleTitle = driver.findElements(By.xpath("//ul[@class='k-box-card-list']/li"));
        for (WebElement article:articleTitle){
            Assert.assertTrue(article.getText().contains("Excel"));
        }
    }

    @Test
    public void TC_02_Frame(){
        driver.get("https://netbanking.hdfcbank.com/netbanking/");
        // Switch iFrame
        driver.switchTo().frame(driver.findElement(By.xpath("//frame[@name='login_page']")));
        // Sendkey in textbox
        driver.findElement(By.xpath("//input[@class='form-control text-muted']")).sendKeys("AutomationFC");
        driver.findElement(By.xpath("//div[@class='inputfield ibvt loginData']/a")).click();

        // Verify pass textbox isDisplay
        Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
        driver.findElement(By.xpath("//input[@name='fldPassword']")).sendKeys("123456789");

        driver.findElement(By.xpath("//div[@class='footer-btm']/a[text()='Terms and Conditions']")).click();

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