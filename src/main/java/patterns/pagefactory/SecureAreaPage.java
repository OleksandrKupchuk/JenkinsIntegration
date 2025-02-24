package patterns.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import patterns.BrowserFactory;

public class SecureAreaPage {
    private WebDriver driver;
    @FindBy(xpath = "//div[@id='content']//h2")
    private WebElement title;

    public SecureAreaPage(){
        this.driver = BrowserFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public WebElement getTitle() {
        return title;
    }
}
