package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_20_Wait_01_Element_Status {
    WebDriver driver;
    WebDriverWait explicitWait;
    By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");
    By usernameTextbox = By.id("email");
    By passwordTextbox = By.id("pass");
    By registerButton = By.xpath("//a[text()='Create New Account']");


    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,15);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // case 1: Element hien thi tren UI va co trong DOM
        // case 2: Element khong hien thi tren UI va co trong DOM
        // case 3: Element khong hien thi va khong co trong DOM

        // Cac trang thai cua element
        // Visible -> visibility ( ham dung de Wait element -> mong muon hien thi) =  isDisplay ( verify element hien thi )
        // Invisible -> invisibility
        // Presence -> presence
        // Staleness -> staleness
    }

    @Test
    public void TC_01_Visible(){
        driver.get("https://www.facebook.com/");

        // Visible -> Thoa man dieu kien case 1

        driver.findElement(registerButton).click();
        // Cho cho element hien thi
        // Hien thi tren UI
        // Hien thi trong DOM
        driver.findElement(By.name("reg_email__")).sendKeys("automationFc@gmail.com");
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));
    }

    @Test
    public void TC_02_Invisible(){
        // Invisible -> Thoa mam case 2 vs case 3
        // Dieu kien bat buoc: khong hien thi tren UI

        driver.navigate().refresh();
        // Khong hien thi tren UI, co trong DOM
        driver.findElement(registerButton).click();
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));

        // Khong co tren UI, khong co trong DOM
        driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
        sleepInSecond(2);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
    }

    @Test
    public void TC_03_Presence(){
        // Thoa man case 1 va case 2
        // Dieu kien bat buoc -> Phai co trong DOM, khong quan tam co tren UI hay k

        driver.get("https://www.facebook.com/");
        driver.findElement(registerButton).click();

        // In DOM - Khong co tren UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));

        driver.findElement(confirmEmailTextbox).sendKeys("automation@gmail.com");
        // Wait Presence
        // In DOM - Co tren UI
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));

    }

    @Test
    public void TC_04_Staleness(){
        // Thoa man case 3
        // Khi thao tac tren UI, Eelment ben duoi DOM bi update lai trang thai
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        driver.findElement(By.id("SubmitCreate")).click();
        // 1: tim duoc 1 element
        WebElement errorMessage = driver.findElement(By.xpath("//div[@id='create_account_error']"));
        // 2: element tai buoc 1 bi update lai -> no longer attach to the DOM
        driver.navigate().refresh();
        // Wait element staleness
        explicitWait.until(ExpectedConditions.stalenessOf(errorMessage));

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
