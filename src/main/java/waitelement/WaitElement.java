package waitelement;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class WaitElement {
    private WebDriver driver;
    private WebDriverWait wait;
    private Duration duration;

    public WaitElement(WebDriver driver){
        this.driver = driver;
        duration = Duration.ofSeconds(10);
        wait = new WebDriverWait(driver, duration);
    }

    public void waitInvisibilityOfElementLocated(By element){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    public WebElement waitVisibilityOfElementLocated(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public WebElement fluentVisibilityOfElementLocated(By element){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(duration)
                .pollingEvery(Duration.ofSeconds(2))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver -> driver.findElement(element));
    }

    public boolean waitForDownloadFile(String filePath){
        return wait.until(webDriver -> {
            return Files.exists(Paths.get(filePath));
        });
    }
}
