package patterns;

import com.codeborne.selenide.WebDriverRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import projectconfig.ProjectConfig;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static WebDriver driver;
    public static void createDriver(String browserName){
        switch (browserName.toLowerCase()){
            case "chrome":
                WebDriverManager.chromedriver().setup();
//                WebDriverManager.chromedriver().driverVersion("120");

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.default_content_settings.popups", 0);
                prefs.put("download.default_directory", "D:\\Homework\\HillelTest\\target\\files");

                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", prefs);

                options.setCapability("selenoid:options", new HashMap<String, Object>() {{
                    put("sessionTimeout", "15m");
                    put("enableVideo", true);
                }});

                //для локального запуску
//                driver = new ChromeDriver(options);


                //для віддаленого запуску
                try {
                    driver = new RemoteWebDriver(new URL(ProjectConfig.REMOTE_URL), options);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                driver.manage().window().maximize();
                WebDriverRunner.setWebDriver(driver);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                //для віддаленого запуску
                try {
                    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd"), firefoxOptions);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                //для локального запуску
//                driver = new FirefoxDriver();
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
