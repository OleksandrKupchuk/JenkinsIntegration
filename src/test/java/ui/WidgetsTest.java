package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import waitelement.WaitElement;

import java.io.File;
import java.io.IOException;

public class WidgetsTest {
    private ChromeDriver chromeDriver;
    private WaitElement waitElement;

    @BeforeMethod
    public void setup(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        waitElement = new WaitElement(chromeDriver);
    }

    @AfterMethod
    public void tearDown(){
        chromeDriver.quit();
    }

    @Test
    public void checkScreenshot(){
        chromeDriver.get("https://the-internet.herokuapp.com/tables");

        File file = ((TakesScreenshot)chromeDriver).getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(file, new File("target/page.jpeg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkWebElementScreenshot(){
        chromeDriver.get("https://the-internet.herokuapp.com/tables");

        WebElement table = chromeDriver.findElement(By.id("table1"));

        File file = table.getScreenshotAs(OutputType.FILE);

        try {
            FileHandler.copy(file, new File("target/table.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
