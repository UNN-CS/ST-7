package edu.unn.cs.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class App {

    public static void main(String[] args) {
        String chromeDriverPath = resolveChromeDriverPath();
        if (chromeDriverPath != null) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }
        WebDriver webDriver = new ChromeDriver();
        try {
            runTask1(webDriver);
            Task2.run(webDriver);
            Task3.run(webDriver);
        } finally {
            webDriver.quit();
        }
    }

    private static String resolveChromeDriverPath() {
        String path = System.getenv("CHROMEDRIVER_PATH");
        if (path == null || path.isEmpty()) {
            String os = System.getProperty("os.name", "").toLowerCase();
            if (os.contains("mac")) {
                path = "/opt/homebrew/bin/chromedriver";
            } else if (os.contains("win")) {
                path = "C:\\chromedriver\\chromedriver.exe";
            } else {
                path = "/usr/bin/chromedriver";
            }
        }
        File driverFile = new File(path);
        if (driverFile.exists() && driverFile.canExecute()) {
            return driverFile.getAbsolutePath();
        }
        return null;
    }

    static void runTask1(WebDriver webDriver) {
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement generateButton = webDriver.findElement(By.cssSelector("input[name='submit1']"));
            generateButton.click();

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement passwordElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#resultid div.verybigtext b")));
            String password = passwordElement.getText();
            System.out.println("Сгенерированный пароль: " + password);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
