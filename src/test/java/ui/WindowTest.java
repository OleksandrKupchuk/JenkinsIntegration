package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import waitelement.WaitElement;

import java.util.ArrayList;

public class WindowTest {
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
    public void checkSwitchBetweenWindow(){
        chromeDriver.get("https://the-internet.herokuapp.com/windows");

        ArrayList<String> windows = new ArrayList<>(chromeDriver.getWindowHandles());
        Assert.assertEquals(windows.size(), 1);

        chromeDriver.findElement(By.linkText("Click Here")).click();
        windows.clear();
        windows = new ArrayList<>(chromeDriver.getWindowHandles());
        Assert.assertEquals(windows.size(), 2);

        chromeDriver.switchTo().window(windows.get(1));
        WebElement newWindowTitle = chromeDriver.findElement(By.xpath("//h3"));
        Assert.assertEquals(newWindowTitle.getText().trim(), "New Window");
        chromeDriver.close();

        chromeDriver.switchTo().window(windows.get(0));
        WebElement firstWindowTitle = chromeDriver.findElement(By.xpath("//h3"));
        Assert.assertEquals(firstWindowTitle.getText(), "Opening a new window");
    }
}
