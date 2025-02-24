package patterns.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import patterns.BrowserFactory;

public class SecureAreaPage {
    private WebDriver driver;
    private By title = By.xpath("//div[@id='content']//h2");

    public SecureAreaPage(){
        this.driver = BrowserFactory.getDriver();
    }

    public WebElement getTitle(){
        return driver.findElement(title);
    }
}
