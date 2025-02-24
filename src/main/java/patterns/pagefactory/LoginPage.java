package patterns.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import patterns.BrowserFactory;

public class LoginPage {
    private WebDriver driver;

    @FindBy(id = "username")
    private WebElement userName;
    @FindBy(id = "password")
    private WebElement password;
    @FindBy(xpath = "//button")
    private WebElement loginButton;

    public LoginPage(){
        this.driver = BrowserFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        return this;
    }

    public LoginPage setUserName(String userName) {
        this.userName.sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }
}
