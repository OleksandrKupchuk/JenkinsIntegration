package ui.steps.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import patterns.BrowserFactory;

public class Hook {
    @Before
    public static void setup(){
        BrowserFactory.createDriver("chrome");
    }

    @After
    public static void teardown(){
        BrowserFactory.quitDriver();
    }
}
