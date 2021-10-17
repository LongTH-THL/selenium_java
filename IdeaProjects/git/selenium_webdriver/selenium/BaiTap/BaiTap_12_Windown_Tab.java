package BaiTap;

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

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class BaiTap_12_Windown_Tab {
    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Windown() {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        // Switch sang Google
        switchToWindownByTitle("Google");
        // Switch ve parent
        switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");
        // click to FB
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        // Kiem tra title cua windown moi
        switchToWindownByTitle("Facebook - Đăng nhập hoặc đăng ký");
        Assert.assertEquals(driver.getTitle(),"Facebook - log in or sign up");
        // Switch ve parent
        switchToWindownByTitle("SELENIUM WEBDRIVER FORM DEMO");

        driver.findElement(By.xpath("//a[text()='TIKI']")).click();
        switchToWindownByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
        Assert.assertEquals(driver.getTitle(),"Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        closeAllTabWithoutParent("SELENIUM WEBDRIVER FORM DEMO");
        Assert.assertEquals(driver.getTitle(),"SELENIUM WEBDRIVER FORM DEMO");
    }

    @Test
    public void TC_02_Kyna(){
        driver.get("https://kyna.vn/");

        // Click to Facebook page
        clickToElementByJS("//div[@id='k-footer']//div[@class='social']/a[@href='https://www.facebook.com/kyna.vn']");
        switchToWindownByTitle("(4) Kyna.vn | Facebook");
        // Verify title
        Assert.assertEquals(driver.getTitle(),"Kyna.vn - Home | Facebook");
        sleepInSecond(2);

        // Switch to parent windown
        switchToWindownByTitle("Kyna.vn - Học online cùng chuyên gia");
        sleepInSecond(2);

        // Click to Youtube
        clickToElementByJS("//div[@id='k-footer']//a[@href='https://www.youtube.com/user/kynavn']");
        switchToWindownByTitle("Kyna.vn - YouTube");
        // Verify title
        Assert.assertEquals(driver.getTitle(),"Kyna.vn - YouTube");
        sleepInSecond(2);

        closeAllTabWithoutParent("Kyna.vn - Học online cùng chuyên gia");
        sleepInSecond(2);
        Assert.assertEquals(driver.getTitle(),"Kyna.vn - Học online cùng chuyên gia");

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

    public void switchToWindownByTitle(String expectedTitle){
        Set<String> allWindown = driver.getWindowHandles();
        for (String id:allWindown){
            driver.switchTo().window(id);
            String windownTitle = driver.getTitle();
            if(windownTitle.equals(expectedTitle)){
                break;
            }
        }
    }

    public void closeAllTabWithoutParent(String parentID){
        Set<String> allWindow = driver.getWindowHandles();
        for (String id:allWindow){
            driver.switchTo().window(id);
            String windownTitle = driver.getTitle();
            if (!windownTitle.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    public void clickToElementByJS(String locator){
        WebElement element = driver.findElement(By.xpath(locator));
        JavascriptExecutor jsExcutor = (JavascriptExecutor) driver;
        jsExcutor.executeScript("arguments[0].click();",element);
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}