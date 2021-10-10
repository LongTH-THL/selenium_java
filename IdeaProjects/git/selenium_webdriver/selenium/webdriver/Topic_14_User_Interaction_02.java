package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_14_User_Interaction_02 {
    WebDriver driver;
    WebDriverWait explicitWait;
    Actions actions;
    JavascriptExecutor jsExcutor;
    String projectPath = System.getProperty("user.dir");


    String jsHelperPath = projectPath + "/git/selenium_webdriver/dragAndDrop/drag_and_drop_helper.js";


    @BeforeClass
    public void beforeClass()
    {
        System.setProperty("webdriver.chrome.driver","/Users/macbook/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();
        explicitWait = new WebDriverWait(driver,15);
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Rigth_Click_to_Element(){
        driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
        sleepInSecond(2);

        actions.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();
        sleepInSecond(2);

        Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());

        actions.click(driver.findElement(By.cssSelector("li.context-menu-icon-quit"))).perform();

        driver.switchTo().alert().accept();
        sleepInSecond(2);

        Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
    }
    @Test
    public void TC_02_Drag_And_Drop_HTML4() {
        driver.get("https://automationfc.github.io/kendo-drag-drop/");
        WebElement smallCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement bigCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));

        Assert.assertEquals(bigCircle.getText(),"Drag the small circle here.");

        actions.dragAndDrop(smallCircle,bigCircle).perform();
        sleepInSecond(3);

        Assert.assertEquals(bigCircle.getText(),"You did great!");
    }
    @Test
    public void TC_03_Drag_And_Drop_HTML5() throws IOException {
        driver.get("https://automationfc.github.io/drag-drop-html5/");
        String jsHelperFileContent = getContentFile(jsHelperPath);

        String soucreCSS = "#column-a";
        String targetCss = "#column-b";
        // A to B
        jsHelperFileContent = jsHelperFileContent + "$(\"" + soucreCSS + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
        jsExcutor.executeScript(jsHelperFileContent);
        sleepInSecond(3);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());

        // B to A
        jsExcutor.executeScript(jsHelperFileContent);
        sleepInSecond(2);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='A']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

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

    public String getContentFile(String filePath) throws IOException {
        Charset cs = Charset.forName("UTF-8");
        FileInputStream stream = new FileInputStream(filePath);
        try {
            Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
            StringBuilder builder = new StringBuilder();
            char[] buffer = new char[8192];
            int read;
            while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
                builder.append(buffer, 0, read);
            }
            return builder.toString();
        } finally {
            stream.close();
        }
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }
}
