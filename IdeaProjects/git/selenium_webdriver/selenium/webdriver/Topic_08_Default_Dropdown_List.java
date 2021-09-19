package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_08_Default_Dropdown_List {
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
    public void TC_01_NopCommerce(){
        driver.get("https://demo.nopcommerce.com/");

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

        driver.findElement(By.xpath("//a[text()='Register']")).click();
        driver.findElement(By.xpath("//input[@value='M']")).click();
        driver.findElement(firstNameBy).sendKeys(firstName);
        driver.findElement(lassNameBy).sendKeys(lastName);

        select = new Select(driver.findElement(dateDropdowmBy));
        // chon 1 item
        select.selectByVisibleText(day);

        List<WebElement> allItems = select.getOptions();
        List<String> allItemsText = new ArrayList<String>();
        for (WebElement item:allItems){
            allItemsText.add(item.getText());
        }

        // kiem tra dropdown co phai multiple select hay khong ???
        Assert.assertFalse(select.isMultiple());
        // kiem tra xem da chon dung cai item chua ???
        Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
        // get ra tong so item trong dropdown -> verify = ???
        Assert.assertEquals(select.getOptions().size(), 32);

        select = new Select(driver.findElement(monthDropdownBy));
        select.selectByVisibleText(month);

        select = new Select(driver.findElement(yearDropdownBy));
        select.selectByVisibleText(year);


        driver.findElement(emailBy).sendKeys(emailAddress);
        driver.findElement(companyBy).sendKeys(company);
        driver.findElement(By.name("Password")).sendKeys(password);
        driver.findElement(By.name("ConfirmPassword")).sendKeys(password);

        driver.findElement(By.name("register-button")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
        driver.findElement(By.xpath("//a[@class='ico-account']")).click();

        Assert.assertTrue(driver.findElement(genderMaleBy).isSelected());
        Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"),lastName);
        Assert.assertEquals(driver.findElement(lassNameBy).getAttribute("value"),lastName);

        select = new Select(driver.findElement(dateDropdowmBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
        select = new Select(driver.findElement(monthDropdownBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
        select = new Select(driver.findElement(yearDropdownBy));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
        Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"),emailAddress);
        Assert.assertEquals(driver.findElement(companyBy).getAttribute("value"),company);
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
