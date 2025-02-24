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

import java.util.List;

public class FrameTest {
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
    public void checkNestedFrame(){
        chromeDriver.get("https://the-internet.herokuapp.com/nested_frames");

        chromeDriver.switchTo().frame("frame-top");
        chromeDriver.switchTo().frame("frame-middle");

        WebElement content = chromeDriver.findElement(By.id("content"));
        Assert.assertEquals(content.getText(), "MIDDLE");
    }

    @Test
    public void checkAmountFrames(){
        chromeDriver.get("https://the-internet.herokuapp.com/nested_frames");

        List<WebElement> frames = chromeDriver.findElements(By.tagName("frame"));

        Assert.assertEquals(frames.size(), 2);
    }

    @Test
    public void checkFrameByIndex(){
        int frameBottomIndex = 1;
        chromeDriver.get("https://the-internet.herokuapp.com/nested_frames");

        chromeDriver.switchTo().frame(frameBottomIndex);

        WebElement body = chromeDriver.findElement(By.xpath("//body"));

        Assert.assertEquals(body.getText().trim(), "BOTTOM");
    }

    @Test
    public void checkIFrame(){
        chromeDriver.get("https://the-internet.herokuapp.com/iframe");

        chromeDriver.switchTo().frame("mce_0_ifr");

        WebElement body = chromeDriver.findElement(By.xpath("//p"));

        Assert.assertEquals(body.getText().trim(), "Your content goes here.");
    }
}
