package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Topic_05_Web_Browser_Command {
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
    public void TC_01_Browser(){
        // mo ra page UI
        driver.get("https://www.facebook.com/");

        // Handle Windows/Tab
        // dong 1 tab dang active
        driver.close();

        // dong trinh duyet ( k quan tam trinh duyet co bao nhieu tab hay bao nhieu windows)
        driver.quit();

        // lay ID hien tai cua window/tab dang active
        String messengerID = driver.getWindowHandle();

        // lay tat ca ID cua window/tab
        Set<String> allIDs = driver.getWindowHandles();

        // Switch den 1 tab/window nao do
        driver.switchTo().window(messengerID);

        // Tim ra 1 element dung voi locator nao do
        WebElement emailTextbox = driver.findElement(By.id(""));

        // Tim ra tat ca cac element dung voi locator nao do
        List<WebElement> textboxes = driver.findElements(By.id(""));

        // Tra ve URL cua page hien tai
        String allPageUrl = driver.getCurrentUrl();

        // Tra ve HTML Source cua page hien tai
        String homePageSource = driver.getPageSource();

        // Tra ve title cua page hien tai
        String homePageTitle = driver.getTitle();

        // Get/xoa cookie cua page
        // Build framework: Share state of Class
        // Get cookie sau khi login -> Truyen vao cac class khac -> giam thoi gian login cho tung class
        driver.manage().deleteAllCookies();

        // Get ra log cua browser
        driver.manage().logs().getAvailableLogTypes();

        // Ham Wait dung de cho cho viec tim element ( findElement/ findElements)
        // WebDriver Wait
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(15000,TimeUnit.MILLISECONDS);

        // Cho 1 page dc load thanh cong
        driver.manage().timeouts().pageLoadTimeout(15,TimeUnit.SECONDS);

        // Cho 1 script duoc execute thanh cong
        // Thu vien JavaScriptExecutor
        driver.manage().timeouts().setScriptTimeout(15,TimeUnit.SECONDS);

        // Browser full man hinh
        driver.manage().window().fullscreen();

        // Browser maximize man hinh
        driver.manage().window().maximize();

        // Lay ra vi tri hien tai cua browser
        driver.manage().window().getPosition();
        // Set vao cho browser tai vi tri nao do
        driver.manage().window().setPosition(new Point(0,0));

        // Lay ra kich thuoc hien tai cua browser
        driver.manage().window().getSize();
        // Set ra size cua browser
        driver.manage().window().setSize(new Dimension(1920,1080));

        // Back page
        driver.navigate().back();

        // forward to Page
        driver.navigate().forward();

        // Tai lai page
        driver.navigate().refresh();

        // Keep dc history
        driver.navigate().to("https://www.facebook.com/");


        // Windows/ Tab
        // Alert
        // Frame/iFrame
        driver.switchTo().window("");
        driver.switchTo().alert();
        driver.switchTo().frame("");
    }


    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
