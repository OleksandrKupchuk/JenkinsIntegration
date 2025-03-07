package ui.steps.definitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import patterns.BrowserFactory;
import waitelement.WaitElement;

public class DashboardDefinition {
    private WebDriver driver;
    private WaitElement waitElement;
    private By dashboardTitle = By.xpath("//h6");

    public DashboardDefinition(){
        this.driver = BrowserFactory.getDriver();
        waitElement = new WaitElement(this.driver);
    }

    @Then("[UI] User should be on dashboard page")
    public DashboardDefinition assertDashboardTitle(){
        String title = waitElement.waitVisibilityOfElementLocated(dashboardTitle).getText();
        Assert.assertEquals(title, "Dashboard");
        return this;
    }
}
