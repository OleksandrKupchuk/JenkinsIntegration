package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import waitelement.WaitElement;

public class AlertTest {
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
    public void checkConfirmAlert(){
        chromeDriver.get("https://the-internet.herokuapp.com/javascript_alerts");

        chromeDriver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();

        WebElement result = chromeDriver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You successfully clicked an alert");
    }

    @Test
    public void checkPromptAlert(){
        chromeDriver.get("https://the-internet.herokuapp.com/javascript_alerts");

        chromeDriver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = chromeDriver.switchTo().alert();
        alert.sendKeys("Message");
        alert.accept();

        WebElement result = chromeDriver.findElement(By.id("result"));
        Assert.assertEquals(result.getText(), "You entered: Message");
    }
}
