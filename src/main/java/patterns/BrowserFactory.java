package patterns;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static WebDriver driver;
    public static void createDriver(String browserName){
        if(driver != null){
            return;
        }

        switch (browserName.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", "D:\\Homework\\HillelTest\\target\\files");

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);

                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException(String.format("This browser name '%s' don`t support"));
        }
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
    }
}
