package ui;

import org.testng.Assert;
import org.testng.annotations.*;
import patterns.BrowserFactory;
import patterns.pageobject.LoginPage;
import patterns.pageobject.SecureAreaPage;
import projectconfig.ProjectConfig;

public class LoginTest {
    @BeforeMethod
    public void setup() {
        BrowserFactory.createDriver(ProjectConfig.BROWSER_NAME);
    }

    @AfterMethod
    public void tearDown() {
        BrowserFactory.quitDriver();
    }

    @Test
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
