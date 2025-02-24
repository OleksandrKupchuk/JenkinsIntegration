import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;

public class FileTest {
    private Path filePath;

    @BeforeMethod
    public void setup() {
        filePath = Paths.get("target/some.txt");
    }

    @AfterMethod
    public void tearDown() {
        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void checkCreateFile() {
        try {
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(Files.exists(filePath));
    }

    @Test
    public void checkWriteFile(){
        String fileString;

        try {
            Files.writeString(filePath, "Its write to file");
            fileString = Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Assert.assertTrue(Files.exists(filePath));
        Assert.assertEquals(fileString, "Its write to file");
    }

    @Test
    public void checkReadOneLineInFile(){
        try(FileReader fileReader = new FileReader("target/some.txt")){
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            System.out.println();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
