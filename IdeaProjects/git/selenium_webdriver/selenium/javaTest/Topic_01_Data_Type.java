package javaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Set;

public class Topic_01_Data_Type {
    public static void main(String[] args){

        // 2 loại
        // Kiểu nguyên thủy - Primitive Type
        // Number
        // Integer: số nguyên k dấu
        // byte/ short/ int/ long
        byte bNumber = 5;
        short sNumber = 100;
        int studentNumber = 1000;
        long lTimeout = 20000;

        // Số thực
        // float/ double
        float studentPoint = 8.5f;
        double employeeSalary = 28.5d;

        // Char
        // char (ký tự)
        char c = 'Q';
        char special = '$';

        // Boolean
        // boolean
        boolean status = true;


        // Kiểu tham chiếu - Reference Type
        // String
        String studentName = "AutomationFC";
        String studentAddress = new String("HN");

        // Array ( Tập hợp kiểu dữ liệu giống nhau)
        String[] studentNames = {" Nguyễn Văn A" , "Lê Văn B" };

        // Class
        WebDriver driver = new ChromeDriver();

        Actions actions = new Actions(driver);
        // Interface
        JavascriptException jsException = (JavascriptException) driver;
        // Collection (Set/Queue/ List)
        // 1 element
        WebElement emailTextbox = driver.findElement(By.id(""));

        // nhiều element
        List<WebElement> buttons = driver.findElements(By.xpath(""));

        Set <String> allWindown = driver.getWindowHandles();
    }
}
