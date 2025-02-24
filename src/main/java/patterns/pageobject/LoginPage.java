package patterns.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import patterns.BrowserFactory;

public class LoginPage {
    private WebDriver driver;
    private By userName = By.id("username");
    private By password = By.id("password");
    private By loginButton = By.xpath("//button");

    public LoginPage(){
        this.driver = BrowserFactory.getDriver();
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        return this;
    }

    public LoginPage setUserName(String userName) {
        driver.findElement(this.userName).sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password){
        driver.findElement(this.password).sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        driver.findElement(loginButton).click();
        return this;
    }
}
