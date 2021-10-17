package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class Topic_17_Windows_Tab {
    WebDriver driver;
    String childID;


    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Windows(){
        driver.get("https://automationfc.github.io/basic-form/index.html");

        // Get ra id cua tab/windows dang active (driver dang dung) -> 1 id
        String parentID = driver.getWindowHandle();
        System.out.print("Home ID = " + parentID + "\n" );

        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();

        // Switch vao FB bang title
        SwitchToWindowsByTitle("Facebook - log in or sign up ");


    }
    @Test
    public void TC_02_(){
    }
    @Test
    public void TC_03_(){
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

    // Dung cho 2 windows hoac 2 tab
    // Kiem tra id truoc
    // Khac voi parent ==> Switch
    public void SwitchToWindowsByID(String parentID){
        // get ra tat ca cac tab hoac windows dang co
        Set<String> allWindows = driver.getWindowHandles();
        for (String id:allWindows){
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
            }
        }
    }

    // Dung cho nhieu hon 2 windows/tab
    // Switch vao tung windows
    // Get ra title cua windows do
    // Kiem tra voi title mong muon
    // Neu nhu = thi stop khong kiem tra nua
    public void SwitchToWindowsByTitle(String expectedTitle){
        Set<String> allWindows = driver.getWindowHandles();
        for (String id:allWindows){
            driver.switchTo().window(id);
            String widowsTitle = driver.getTitle();
            if(widowsTitle.equals(expectedTitle)){
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(String parentID){
        Set<String> allWindows = driver.getWindowHandles();
        for (String id:allWindows){
            if(!id.equals(parentID)){
                driver.switchTo().window(id);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
