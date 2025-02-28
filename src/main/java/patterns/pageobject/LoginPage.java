package patterns.pageobject;

import logger.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import patterns.BrowserFactory;

public class LoginPage {
    private WebDriver driver;
    private Logger logger;
    private By userName = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.xpath("//button");

    public LoginPage(){
        this.driver = BrowserFactory.getDriver();
        logger = new Logger();
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        return this;
    }

    public LoginPage setUserName(String userName) {
        logger.log(String.format("Set username '%s'", userName));
        driver.findElement(this.userName).sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password){
        logger.log(String.format("Set password '%s'", userName));
        driver.findElement(this.password).sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        logger.log("Click login button");
        driver.findElement(loginButton).click();
        return this;
    }
}
