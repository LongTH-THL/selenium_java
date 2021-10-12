package BaiTap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaiTap_10_Popup_Dialog {
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
    public void TC_01_Fixed_Popup(){
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.cssSelector("button.login_")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='modal-login-v1']/div")).isDisplayed());

        driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("AutomationFc");
        driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("AutomationFc");

        driver.findElement(By.xpath("//button[@data-text='Đăng nhập']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText(),"Tài khoản không tồn tại!");
        driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
        sleepInSecond(2);
        Assert.assertFalse(driver.findElement(By.xpath("//div[@id='modal-login-v1']/div")).isDisplayed());
    }
    @Test
    public void TC_02_Random_In_DOM(){
        driver.get("https://blog.testproject.io/");
        sleepInSecond(30);

        WebElement popup = driver.findElement(By.xpath("//div[@class='mailch-wrap']/img"));
        if(popup.isDisplayed()){
            System.out.print("Popup is Display");
            driver.findElement(By.xpath("//div[@class='close-mailch']"));
        } else{
            System.out.print("Popup is not Display");
        }

        driver.findElement(By.xpath("//section//input[@class='search-field']")).sendKeys("Selenium");
        driver.findElement(By.xpath("//section//span[@class='glass']")).click();
        sleepInSecond(2);

        List<WebElement> articleTitle = driver.findElements(By.xpath("//h3[@class='post-title']"));
        for (WebElement article:articleTitle){
            Assert.assertTrue(article.getText().contains("Selenium"));
        }

    }

    @Test
    public void TC_03_Random_Not_In_DOM(){
        driver.get("https://dehieu.vn/");
        sleepInSecond(10);

        List<WebElement> popup = driver.findElements(By.xpath("//section//div[@class='popup-content']"));
        if (popup.size() != 0 && popup.get(0).isDisplayed()){
            System.out.print("Popup is Display");
            driver.findElement(By.xpath("//div[@class='popup-content']/button[@class='close']")).click();
        } else {
            System.out.print("Popup is not Display");
        }
    }

    public void TC_04_Random_Not_In_DOM(){
        driver.get("https://shopee.vn/");
        sleepInSecond(2);

        List<WebElement> pop_up = driver.findElements(By.xpath("//div[@class='shopee-popup__container']//img"));
        if (pop_up.size() > 0 && pop_up.get(0).isDisplayed()) {
            driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
            sleepInSecond(2);
        } else {
            System.out.print(" Popup is not display");
        }
        driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys("hub type C");
        driver.findElement(By.xpath("//button[@class='btn btn-solid-primary btn--s btn--inline']")).click();
        sleepInSecond(5);
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