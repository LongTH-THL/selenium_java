package BaiTap;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class BaiTap_09_User_Interaction {
    WebDriver driver;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExcutor;
    Actions actions;
    String osName = System.getProperty("os.name");
    Keys key;

    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,15);
        jsExcutor = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Hover(){
        driver.get("https://automationfc.github.io/jquery-tooltip/");

        actions.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
    }
    @Test
    public void TC_02_HovertoElement_01(){
        driver.get("https://www.myntra.com/");

        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
        sleepInSecond(2);
        driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).isDisplayed());

        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='title-title']")).getText(),"Kids Home Bath");
    }
    @Test
    public void TC_03_HovertoElement_02(){
    }
    @Test
    public void TC_04_ClickAndHold(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        actions.clickAndHold(allItem.get(0)).moveToElement(allItem.get(3)).release().perform();

        Assert.assertEquals(driver.findElement(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).getSize(),4);
    }

    @Test
    public void TC_05_ClickAndHold_Random(){
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> allItem = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        if (osName.contains("Window")){
            key = Keys.CONTROL;
        } else {
            key = Keys.CONTROL;
        }

        actions.keyDown(key).perform();
        actions.click(allItem.get(0)).click(allItem.get(3)).click(allItem.get(8)).perform();
        Assert.assertEquals(driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]")).size(),3);
    }

    @Test
    public void TC_06_Double_Click(){
        driver.get("https://automationfc.github.io/basic-form/index.html");
        // scroll xuống button
        jsExcutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']")));

        actions.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
    }

    @Test
    public void TC_07_Rigth_Click(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

        actions.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
        Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='context-menu-list context-menu-root']")).isDisplayed());
        sleepInSecond(2);
        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        driver.switchTo().alert().accept();
        sleepInSecond(2);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }

    @Test
    public void DragAndDrop_HTML4(){
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

        Assert.assertEquals(bigCircle.getText(),"Drag the small circle here.");
        actions.dragAndDrop(smallCircle,bigCircle).perform();

        Assert.assertEquals(bigCircle.getText(),"You did great!");

        // verify color
        String rgbaColor = bigCircle.getCssValue("background-color");
        System.out.println("RGBA = " + rgbaColor);
        String hexColor = Color.fromString(rgbaColor).asHex().toUpperCase(Locale.ROOT);
        System.out.println("Hex = " + hexColor);
        Assert.assertEquals(hexColor,"#03a9f4");
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
