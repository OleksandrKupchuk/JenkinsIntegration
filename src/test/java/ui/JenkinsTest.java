package ui;

import org.testng.annotations.Test;

public class JenkinsTest {
    @Test(groups = {"ui"})
    public void loginSuccess() {
        System.out.println("Login success");
    }
}
