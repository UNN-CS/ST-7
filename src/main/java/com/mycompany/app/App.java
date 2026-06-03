package com.mycompany.app;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", getChromeDriverPath());
        ChromeOptions options = new ChromeOptions();
        WebDriver webDriver = new ChromeDriver(options);
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebElement generateButton = webDriver.findElement(
                    By.cssSelector("input[name='submit1'][value='Generate']"));
            generateButton.click();

            WebDriverWait wait = new WebDriverWait(webDriver, 10);
            WebElement passwordElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("#resultid .verybigtext b")));
            String password = passwordElement.getText();
            System.out.println("Сгенерированный пароль: " + password);

            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }

    private static String getChromeDriverPath() {
        Path projectRoot = Paths.get(System.getProperty("user.dir"));
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();

        if (os.contains("mac")) {
            if (arch.contains("aarch64") || arch.contains("arm")) {
                return projectRoot.resolve("drivers/chromedriver-mac-arm64/chromedriver").toString();
            }
            return projectRoot.resolve("drivers/chromedriver-mac-x64/chromedriver").toString();
        }
        if (os.contains("linux")) {
            return projectRoot.resolve("drivers/chromedriver-linux64/chromedriver").toString();
        }
        return projectRoot.resolve("drivers/chromedriver.exe").toString();
    }
}
