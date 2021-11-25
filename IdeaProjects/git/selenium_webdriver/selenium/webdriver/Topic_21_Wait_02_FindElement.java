package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_21_Wait_02_FindElement {
    WebDriver driver;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.facebook.com/");
    }

    /*
     1: Đều chịu ảnh hưởng bởi time out implicit
     2: 30s là thời gian chờ tối đa
     3: Nếu như xuất hiện nhanh hươn 30s ( thì thời gian còn lại k phải chờ nữa)
     4: Nếu như sau 30s vẫn không tìm thấy element -> Tuỳ thuộc vào findElement hay findElements sẽ trả về khác nhau
     5: Trong thời gian wait sẽ theo cơ chế 0.5s 1 lần tìm
    */

    @Test
    public void TC_01_Find_Element(){
        // find single element
        // WebElement emailTextbox = driver.findElement(By.xpath(""));
        // 1: Tim thay 1 element
        // Không cần chờ hết time out nên sẽ chạy rất nhanh
        System.out.println("Start:" + getDateTimeNow());
        driver.findElement(By.id("email")).sendKeys("long@gmai.com");
        System.out.println("End:" + getDateTimeNow());

        // 2: Khong tim thay element
        // Chờ hết timeout
        // Ném ra 1 ngoại lệ: NoSuchElementException
        // Đánh testcase đó fail ngay tại step đó
        // Không chạy step tiếp theo nữa
        System.out.println("Start:" + getDateTimeNow());
        try {
            driver.findElement(By.id("Tiki")).isDisplayed();
        } finally {
            System.out.println("End:" + getDateTimeNow());
        }

        // 3: Tim thay nhieu hon 1 element
        // Thao tác với element đầu tiên
        // Không quan tâm đến các element sau
        System.out.println("Start:" + getDateTimeNow());
        driver.findElement(By.xpath("//div[@id='pageFooter']//a")).click();
        System.out.println("End:" + getDateTimeNow());

    }
    @Test
    public void TC_02_Find_Elements(){
        List<WebElement> elements;

        // case 1: tìm thấy 1 element
        // trả về list chứa 1 element tìm đc -> size list = 1
        System.out.println("Start 1-:" + getDateTimeNow());
        elements = driver.findElements(By.xpath("//input[@id='email']"));
        System.out.println("List size = " + elements.size());
        System.out.println("End:" + getDateTimeNow());

        // case 2: tìm thấy nhiều element
        System.out.println("Start 1-:" + getDateTimeNow());
        elements = driver.findElements(By.xpath("//div[@id='pageFooter']//a"));
        System.out.println("List size = " + elements.size());
        System.out.println("End:" + getDateTimeNow());

        // case 3: không thấy element
        System.out.println("Start 3-:" + getDateTimeNow());
        elements = driver.findElements(By.xpath("//input[@id='Tiki']"));
        System.out.println("List size = " + elements.size());
        System.out.println("End 3-:" + getDateTimeNow());
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

    public String getDateTimeNow(){
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
