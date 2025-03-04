package patterns.pageobject.orangehrm;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private SelenideElement username = $(By.name("username"));
    private SelenideElement password = $(By.name("password"));
    private SelenideElement loginButton = $x("//button[@type='submit']");

    public LoginPage open(){
        WebDriverRunner.getWebDriver().get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        return this;
    }

    public LoginPage setUsername(String username){
        this.username.setValue(username);
        return this;
    }

    public LoginPage setPassword(String password){
        this.password.setValue(password);
        return this;
    }

    public LoginPage clickLoginButton(){
        loginButton.click();
        return this;
    }
}
