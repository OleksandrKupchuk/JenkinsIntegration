package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import waitelement.WaitElement;

import java.util.List;

public class ActionsTest {
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
    public void checkDragAndDrop(){
        chromeDriver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(chromeDriver);
        WebElement squareA = chromeDriver.findElement(By.id("column-a"));
        WebElement squareB = chromeDriver.findElement(By.id("column-b"));

        Assert.assertEquals(squareA.getText(), "A");
        Assert.assertEquals(squareB.getText(), "B");

        actions.dragAndDrop(squareA, squareB)
                .build()
                .perform();

        Assert.assertEquals(squareA.getText(), "B");
        Assert.assertEquals(squareB.getText(), "A");
    }

    @Test
    public void checkDragAndDropWithMouse(){
        chromeDriver.get("https://the-internet.herokuapp.com/drag_and_drop");
        Actions actions = new Actions(chromeDriver);
        WebElement squareA = chromeDriver.findElement(By.id("column-a"));
        WebElement squareB = chromeDriver.findElement(By.id("column-b"));

        Assert.assertEquals(squareA.getText(), "A");
        Assert.assertEquals(squareB.getText(), "B");

        actions.clickAndHold(squareA)
                .moveToElement(squareB)
                .release()
                .build()
                .perform();

        Assert.assertEquals(squareA.getText(), "B");
        Assert.assertEquals(squareB.getText(), "A");
    }

    @Test
    public void checkSetFieldByImitationKeyboard(){
        chromeDriver.get("https://the-internet.herokuapp.com/login");

        WebElement username = chromeDriver.findElement(By.id("username"));

        username.sendKeys("user");

        username.click();
        username.sendKeys(Keys.chord(Keys.CONTROL, "A"));
        username.sendKeys(Keys.chord(Keys.CONTROL, "C"));
        username.click();
        username.sendKeys(Keys.chord(Keys.CONTROL, "V"));
        username.clear();
    }

    @Test
    public void checkScroll(){
        chromeDriver.get("https://the-internet.herokuapp.com/");
        Actions actions = new Actions(chromeDriver);

        WebElement editorMenu = chromeDriver.findElement(By.linkText("WYSIWYG Editor"));

        actions.scrollToElement(editorMenu).build().perform();
        System.out.println();
    }

    @Test
    public void checkHover(){
        chromeDriver.get("https://the-internet.herokuapp.com/hovers");
        Actions actions = new Actions(chromeDriver);

        List<WebElement> figures = chromeDriver.findElements(By.className("figure"));

        actions.moveToElement(figures.get(0)).build().perform();
        System.out.println();
    }
}
