package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;


public class Topic_06_Web_Element_Command_1 {
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
    public void TC_01_(){
        // Webbrowser command/ Method/ API
        // se thong qua driver instance/ variable

        // WebElement Command/ Method/ API
        // se thong qua driver.findElement()

        // thao tac 1 lan: khong can khai bao bien
        // thao tac nhieu lan: khai bao bien - tranh lap lai code

        WebElement element = driver.findElement(By.id("email"));

        element.clear(); // xoa du lieu trong editable field (textbox/ textarea/ dropdown)

        element.sendKeys(); // nhap du lieu trong editable field ( nhap chuoi)
        element.sendKeys(Keys.ENTER);

        element.click(); // click vao 1 button/ link/ radio button/ ....

        element.getAttribute(""); // tra ve du lieu nam trong attribute cua element

        element.getCssValue("backgroud-color"); // tra ve thuoc tinh CSS cua element: color/ font size/ size/ font style/...

        // ham test giao dien - it dung
        element.getLocation();
        element.getRect();
        element.getSize();

        element.getScreenshotAs(OutputType.FILE); // check screenshot & attach vao HTML report

        // dung By. id/class/css/nam
        element.getTagName(); // get ra ten the HTML
        // Dau ra cua step nay -> dau vao cua step kia
        element = driver.findElement(By.cssSelector("#email"));
        String emailButtonTagname = element.getTagName();
        driver.findElements(By.xpath("//" +emailButtonTagname + "[@name='email']"));

        element.getText(); // lay ra text cua element

        element.isDisplayed(); // kiem tra element co hien thi hay k ( hien thi tren man hinh/ user co the thao tac duoc)
        element.isEnabled(); // kiem tra element co thao tac duoc hay k ( k bị disable/ k phai la readonly field)
        element.isSelected(); // kiem tra element da duoc chon hay chua ( radio/ textbox/ dropdown)

        element.submit(); // submit vao 1 form

    }


    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
