package ui;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;
import waitelement.WaitElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class WaitTest {
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
    public void checkSetValueBySelectInDropdownByIndex() {
        int dropDownListIndex = 2;
        chromeDriver.get("https://the-internet.herokuapp.com/dropdown");

        String expectedResult = "Option 2";
        Assert.assertEquals(setDropDownValue(chromeDriver, dropDownListIndex).getText(), expectedResult);

        System.out.println();
    }

    @Test
    public void checkSetValue() {
        chromeDriver.get("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = chromeDriver.findElement(By.id("dropdown"));
        dropDown.click();
        WebElement element = chromeDriver.findElement(By.xpath("//option[@value=1]"));
        element.click();
        Assert.assertEquals(element.getAttribute("selected"), "selected");
        Assert.assertEquals(element.getText(), "Option 1");

        System.out.println();
    }

    public WebElement setDropDownValue(WebDriver driver, int index){
        WebElement dropDown = driver.findElement(By.id("dropdown"));
        Select select = new Select(dropDown);
        select.selectByIndex(index);
        return select.getFirstSelectedOption();
    }

    @Test
    public void checkImplicitWait(){
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.get("https://the-internet.herokuapp.com/login");
        chromeDriver.findElement(By.id("username")).sendKeys("tomsmith");
        chromeDriver.findElement(By.id("password1")).sendKeys("password");
    }

    @Test
    public void checkExplicitWait(){
        chromeDriver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        waitElement.waitInvisibilityOfElementLocated(By.id("loading"));

        chromeDriver.findElement(By.xpath("//button[text()='Start']")).click();

        waitElement.waitVisibilityOfElementLocated(By.id("loading"));
        waitElement.waitInvisibilityOfElementLocated(By.id("loading"));
        WebElement finish = chromeDriver.findElement(By.id("finish"));
        Assert.assertEquals(finish.getText(), "Hello World!");
    }

    @Test
    public void checkFluentWait(){
        chromeDriver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        chromeDriver.findElement(By.xpath("//button[text()='Start']")).click();
        String expectedResult = waitElement.fluentVisibilityOfElementLocated(By.id("loading")).getText();
        chromeDriver.findElement(By.xpath("xpath"));

        Assert.assertEquals(expectedResult, "Loading...");
    }

    @Test
    public void checkClickOnButtonByJSExecutor(){
        chromeDriver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        chromeDriver.findElement(By.xpath("//button[text()='Start']")).click();

        String expectedResult = waitElement.fluentVisibilityOfElementLocated(By.id("loading")).getText();

        Assert.assertEquals(expectedResult, "Loading...");
    }

    @Test
    public void checkClickOnButtonByJSExecutor2(){
        chromeDriver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        List<WebElement> elements = chromeDriver.findElements(By.cssSelector("button, .button"));
        for (WebElement element : elements) {
            System.out.println(element.getCssValue("padding-bottom"));
        }
    }
}
