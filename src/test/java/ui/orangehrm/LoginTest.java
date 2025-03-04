package ui.orangehrm;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import patterns.BrowserFactory;
import patterns.pageobject.orangehrm.DashboardPage;
import patterns.pageobject.orangehrm.LoginPage;

public class LoginTest {

    @BeforeMethod
    public void setup(){
        BrowserFactory.createDriver("chrome");
    }

    @AfterMethod
    public void teardown(){
        BrowserFactory.quitDriver();
    }

    @Test
    public void checkLogin() {
        new LoginPage()
                .open()
                .setUsername("Admin")
                .setPassword("admin123")
                .clickLoginButton();

        String dashboardTitle = new DashboardPage().getTitle().getText();

        Assert.assertEquals(dashboardTitle, "Dashboard");
    }
}
