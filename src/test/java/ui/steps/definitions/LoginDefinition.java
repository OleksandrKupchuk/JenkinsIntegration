package ui.steps.definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import patterns.BrowserFactory;
import waitelement.WaitElement;

public class LoginDefinition {
    private WebDriver driver;
    private WaitElement waitElement;
    private By username = By.xpath("//input[@name='username']");
    private By password = By.name("password");
    private By loginButton = By.xpath("//button[@type='submit']");
    private By dashboardTitle = By.xpath("//h6");

    public LoginDefinition(){
        this.driver = BrowserFactory.getDriver();
        waitElement = new WaitElement(this.driver);
    }

    @When("[UI] User login")
    public LoginDefinition login(){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        waitElement.waitVisibilityOfElementLocated(this.username).sendKeys("Admin");
        driver.findElement(this.password).sendKeys("admin123");
        driver.findElement(loginButton).click();
        return this;
    }

    @When("\\[UI] User login with (.*) and (.*)$")
    public LoginDefinition login(String username, String password){
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        waitElement.waitVisibilityOfElementLocated(this.username).sendKeys(username);
        driver.findElement(this.password).sendKeys(password);
        driver.findElement(loginButton).click();
        return this;
    }
}
