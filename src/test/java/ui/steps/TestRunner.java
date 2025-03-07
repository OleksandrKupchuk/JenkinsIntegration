package ui.steps;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",
                tags = "@regression",
                plugin = {"pretty", "html:target/cucumber-report.html"})
public class TestRunner extends AbstractTestNGCucumberTests {
}
