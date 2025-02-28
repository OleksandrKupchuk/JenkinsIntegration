package setup;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import patterns.BrowserFactory;
import projectconfig.ProjectConfig;

public class TestSetup {
    @BeforeMethod
    public void setup() {
        BrowserFactory.createDriver(ProjectConfig.BROWSER_NAME);
    }

    @AfterMethod
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
