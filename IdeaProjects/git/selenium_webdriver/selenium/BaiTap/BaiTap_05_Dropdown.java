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


public class BaiTap_05_Dropdown {
    WebDriver driver;
    Select select;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_HTML_Dropdown_List_01(){
        // Step 1: mo web
        driver.get("https://www.rode.com/wheretobuy");

        // Step 2: check dropdown multiple
        select = new Select(driver.findElement(By.name("where_country")));
        Assert.assertFalse(select.isMultiple());

        // Step 3: select Viet Nam
        select.selectByVisibleText("Vietnam");

        // Step 4: kiem tra thong tin dropdown
        Assert.assertEquals(select.getFirstSelectedOption().getText(),"Vietnam");

        // Step 5: click button submit & kiem tra gia tri tra ve
        driver.findElement(By.name("search_loc_submit")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result_count']/span[text()='29']")).isDisplayed());

        // Step 6: print all gia tri tra ve
        List <WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
        Assert.assertEquals(storeName.size(),29);
        for (WebElement store:storeName){
            System.out.println(store.getText());
        }
    }

    @Test
    public void TC_02_HTML_Dropdown_List_02(){
        // Step 1: truy cap web
        driver.get("https://demo.nopcommerce.com/");

        // Khai bao bien dung chung
        String firstName = "Automation";
        String lastName = "FC";
        String emailAddress = "automaiton" + randomData() + "@gmail.net";
        String day = "5";
        String month = "September";
        String year = "1996";
        String company = "VCCorp";
        String password = "123456";

        By genderMaleBy = By.id("gender-male");
        By firstNameBy = By.name("FirstName");
        By lassNameBy = By.name("LastName");
        By dateDropdowmBy = By.name("DateOfBirthDay");
        By monthDropdownBy = By.name("DateOfBirthMonth");
        By yearDropdownBy = By.name("DateOfBirthYear");
        By emailBy = By.name("Email");
        By companyBy = By.name("Company");

        // Step 2: click Button Register
        driver.findElement(By.xpath("//a[text()='Register']")).click();
        // Step 3: dien thong tin hop le
        driver.findElement(By.xpath("//input[@value='M']")).click();
        driver.findElement(firstNameBy).sendKeys(firstName);
        driver.findElement(lassNameBy).sendKeys(lastName);
        driver.findElement(emailBy).sendKeys(emailAddress);

        select = new Select(driver.findElement(dateDropdowmBy));
        select.selectByVisibleText(day);
        Assert.assertEquals(select.getOptions().size(), 32);

        select = new Select(driver.findElement(monthDropdownBy));
        select.selectByVisibleText(month);
        Assert.assertEquals(select.getOptions().size(), 13);

        select = new Select(driver.findElement(yearDropdownBy));
        select.selectByVisibleText(year);
        Assert.assertEquals(select.getOptions().size(), 112);

        driver.findElement(companyBy).sendKeys(company);
        driver.findElement(By.name("Password")).sendKeys(password);
        driver.findElement(By.name("ConfirmPassword")).sendKeys(password);

        // Step 4: click button register
        driver.findElement(By.name("register-button")).click();

        // Step 5: Verify homePage
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");

        // Step 6: click button My Account
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();

        // Step 7: Verify thong tin trong dropdown
        select = new Select(driver.findElement(dateDropdowmBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
        select = new Select(driver.findElement(monthDropdownBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
        select = new Select(driver.findElement(yearDropdownBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
    }

    @Test
    public void TC_03_(){
    }


    public int randomData() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
