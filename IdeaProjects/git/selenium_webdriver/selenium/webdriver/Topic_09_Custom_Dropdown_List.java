package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Topic_09_Custom_Dropdown_List {
    WebDriver driver;
    Select select;
    WebDriverWait explicitWait;
    JavascriptExecutor jsExcutor;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "/Users/long/Downloads/selenium/chromedriver");
        driver = new ChromeDriver();

        // ep kieu tuong minh
        jsExcutor = (JavascriptExecutor) driver;
        // driver UD
        explicitWait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_jQuery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

        By parentBy = By.xpath("//span[@id='number-button']");
        By chilBy = By.xpath("//ul[@id='number-menu']/li/div");

        selectItemDropdown(parentBy, chilBy, "18");
        sleepInSecond(2);

        Assert.assertTrue(isElementDisplay(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='18']")));
    }

    @Test
    public void TC_02_ReactJS_() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

        By parentBy = By.xpath("//i[@class='dropdown icon']");
        By chilBy = By.xpath("//div[@role='option']/span");

        selectItemDropdown(parentBy, chilBy, "Matt");
        sleepInSecond(2);

        Assert.assertTrue(isElementDisplay(By.xpath("//div[@class='divider text' and text()='Matt']")));

    }

    @Test
    public void TC_03_Vuey() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");

        By parentBy = By.xpath("//li[@class='dropdown-toggle']");
        By chilBy = By.xpath("//ul[@class='dropdown-menu']/li");

        selectItemDropdown(parentBy, chilBy, "First Option");
        sleepInSecond(2);

        Assert.assertTrue(isElementDisplay(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")));
    }

    @Test
    public void TC_04_KendoUI() {
        driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
        // Doi cho den khi icon loading bien mat trong 15 giay
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='kd-loader']"))));
        // Wait cho icon loading o dropdown list bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='k-icon k-i-arrow-60-down k-i-loading']"))));

        By parent1By = By.xpath("//span[@aria-owns='categories_listbox']");
        By chil1By = By.xpath("//ul[@id='categories_listbox']//li//h3");

        selectItemDropdown(parent1By, chil1By, "Condiments");
        sleepInSecond(2);

        // Wait cho icon loading o dropdown list bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='k-icon k-i-arrow-60-down k-i-loading']"))));
        By parent2By = By.xpath("//span[@aria-owns='products_listbox']");
        By chil2By = By.xpath("//ul[@id='products_listbox']/li");

        selectItemDropdown(parent2By, chil2By, "Genen Shouyu");
        sleepInSecond(2);

        // Wait cho icon loading o dropdown list bien mat
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='k-icon k-i-arrow-60-down k-i-loading']"))));
        By parent3By = By.xpath("//span[@aria-owns='shipTo_listbox']");
        By chil3By = By.xpath("//ul[@id='shipTo_listbox']/li");

        selectItemDropdown(parent3By, chil3By, "Starenweg 5");
        sleepInSecond(2);


    }

    @Test
    public void TC_05_Angula() {
        driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

        By parentBy = By.xpath("//span[@aria-owns='games_options']");
        By chilBy = By.xpath("//ul[@id='games_options']/li");

        selectItemDropdown(parentBy, chilBy, "Football");
        sleepInSecond(1);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@aria-owns='games_options']/input")).getAttribute("aria-label"), "Football");

        selectItemDropdown(parentBy, chilBy, "Snooker");
        sleepInSecond(2);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@aria-owns='games_options']/input")).getAttribute("aria-label"), "Snooker");
    }

    @Test
    public void TC_06_Editable_01() {
        driver.get("http://indrimuska.github.io/jquery-editable-select/");
        By parentBy = By.xpath("//div[@id='default-place']/input");
        By chilBy = By.xpath("//ul[@class='es-list' and @style]/li");

        selectItemEditableDropdown(parentBy, chilBy, "Nissan");
        sleepInSecond(1);

        selectItemEditableDropdown(parentBy, chilBy, "Volvo");
        sleepInSecond(1);
    }

    @Test
    public void TC_07_Editable_02() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        By parentBy = By.xpath("//input[@class='search']");
        By chilBy = By.xpath("//div[@role='option']//span");

        selectItemEditableDropdown(parentBy, chilBy, "Aland Islands");
        sleepInSecond(2);

        selectItemEditableDropdown(parentBy, chilBy, "Belarus");
        sleepInSecond(2);
    }

    @Test
    public void TC_08_Multiple_Dropdown() {
        driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

        // Case 1: < == 3
        // Case 2: > 3
        // Case 3: = 12 or select all
        String[] firstMonth = {"January", "April", "July"};
        String[] secondMonth = {"January", "April", "July", "December"};

        selectMultiItemDropdown("(//button[@class='ms-choice'])[1]","(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", firstMonth );
        sleepInSecond(2);
        Assert.assertTrue(areItemSelected(firstMonth));
        driver.navigate().refresh();

        selectMultiItemDropdown("(//button[@class='ms-choice'])[1]","(//button[@class='ms-choice'])[1]/following-sibling::div/ul//span", secondMonth );
        sleepInSecond(2);
        Assert.assertTrue(areItemSelected(secondMonth));
    }

    public int randomData() {
        Random random = new Random();
        return random.nextInt(999999);
    }

    public void sleepInSecond(long timeoutInSecond) {
        try {
            Thread.sleep(timeoutInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void selectItemDropdown(By parentBy, By chilBy, String expectedTextItem) {
        // Kiem tra xem element da co the click dc hay chua
        explicitWait.until(ExpectedConditions.elementToBeClickable(parentBy)).click();

        // Presence
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((chilBy)));

        // Store lai tat ca element ( item cua dropdown)
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((chilBy)));
        System.out.println("All items = " + allItems.size());
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                if (item.isDisplayed()) { // Neu item minh can chon nam trong view ( nhin thay duoc) thi click vao
                    item.click();
                } else { // Neu item minh can chon khong nam trong view ( k nhin thay duoc) thi scroll xuong va click vao
                    jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    item.click();
                }
                break;
            }
        }
    }

    public void selectItemEditableDropdown(By parentBy, By chilBy, String expectedTextItem) {
        // Parent: phai la textbox
        driver.findElement(parentBy).clear();
        driver.findElement(parentBy).sendKeys(expectedTextItem);
        sleepInSecond(1);
        // Presence
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((chilBy)));

        // Store lai tat ca element ( item cua dropdown)
        List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((chilBy)));
        System.out.println("All items = " + allItems.size());

        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedTextItem)) {
                if (item.isDisplayed()) { // Neu item minh can chon nam trong view ( nhin thay duoc) thi click vao
                    item.click();
                } else { // Neu item minh can chon khong nam trong view ( k nhin thay duoc) thi scroll xuong va click vao
                    jsExcutor.executeScript("arguments[0].scrollIntoView(true);", item);
                    item.click();
                }
                break;
            }
        }
    }

    public boolean isElementDisplay(By by) {
        WebElement element = driver.findElement(by);
        if (element.isDisplayed()) {
            System.out.println("Element[" + by + "] is Display");
            return true;
        } else {
            System.out.println("Element [" + by + "] is not Display");
            return false;
        }
    }

    //select multiple dropdown
    public void selectMultiItemDropdown(String parentBy, String chilBy, String[] expectedValueItem) {
        // Step 1: Click on the button -> show all item
        driver.findElement(By.xpath(parentBy)).click();
        // Step 2: Wait -> all item in dropdown success loading
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(chilBy)));

        List<WebElement> allItem = driver.findElements(By.xpath(chilBy));
        // Step 3: Iterate over all elements until condition is satisfied
        for (WebElement chilElement : allItem) {
            //" January", "April", " July"
            for (String item : expectedValueItem) {
                if (chilElement.getText().equals(item)) {
                    // Step 4: scroll to the item you want to select, if item display
                    jsExcutor.executeScript("arguments[0].scrollIntoView(true);", chilElement);
                    sleepInSecond(1);
                    // Step 5: click item want to select
                    chilElement.click();
                    sleepInSecond(1);

                    List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
                    System.out.println("Item selected = " + itemSelected.size());
                    if (expectedValueItem.length == itemSelected.size()) {
                        break;
                    }
                }
            }
        }
    }

    // Verify data
    public boolean areItemSelected(String[] month) {
        List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
        int numberItemSelected = itemSelected.size();

        String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
        System.out.println("Text da chon = " + allItemSelectedText);

        if (numberItemSelected <= 3 && numberItemSelected > 0) {
            boolean status = true;
            for (String item : month) {
                if (!allItemSelectedText.contains(item)) {
                    status = false;
                    return status;
                }
            }
            return status;
        } else if (numberItemSelected >= 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
        } else if (numberItemSelected > 3 && numberItemSelected < 12) {
            return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='"+numberItemSelected+" of 12 selected']")).isDisplayed();
        } else {
            return false;
        }
    }

    @AfterClass
    public void afterClass()
    {
        driver.quit();
    }


}
