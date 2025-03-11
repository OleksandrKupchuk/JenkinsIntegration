package ui;

import org.testng.Assert;
import org.testng.annotations.*;
import patterns.pageobject.LoginPage;
import patterns.pageobject.SecureAreaPage;
import setup.TestSetup;

public class LoginTest extends TestSetup {
    @Test(groups = {"smoke"})
    public void loginUsingPageObject() {
        new LoginPage()
                .open()
                .setUserName("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        String title = new SecureAreaPage()
                .getTitle()
                .getText();

        Assert.assertEquals(title.trim(), "Secure Area");
    }

    @Test
    public void loginUsingPageFactory() {
        new patterns.pagefactory.LoginPage()
                .open()
                .setUserName("tomsmith")
                .setPassword("SuperSecretPassword!")
                .clickLoginButton();

        String title = new patterns.pagefactory.SecureAreaPage()
                .getTitle()
                .getText();

        Assert.assertEquals(title.trim(), "Secure Area");
    }
}
