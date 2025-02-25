package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import patterns.BrowserFactory;
import waitelement.WaitElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class WebFileTest {
    private WebDriver driver;
    private WaitElement waitElement;

    @BeforeMethod
    public void setup(){
        BrowserFactory.createDriver("chrome");
        driver = BrowserFactory.getDriver();
        waitElement = new WaitElement(driver);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void checkUploadFile(){
        Path filePath = Paths.get("target/upload.txt");
        List<String> lines = List.of("first line", "second line");

        try {
            if(Files.notExists(filePath)){
                Files.createFile(filePath);
                Files.write(filePath, lines, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        driver.get("https://the-internet.herokuapp.com/upload");
        driver.findElement(By.id("file-upload")).sendKeys(filePath.toFile().getAbsolutePath());
        driver.findElement(By.id("file-submit")).click();
        WebElement title = driver.findElement(By.xpath("//h3"));

        Assert.assertEquals(title.getText(), "File Uploaded!");
    }

    @Test
    public void checkDownloadFile() {
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.linkText("random_data.txt")).click();

        boolean isDownloaded = waitElement.waitForDownloadFile("target/files/random_data.txt");
        Assert.assertTrue(isDownloaded);
    }
}
