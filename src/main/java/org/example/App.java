package org.example;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    public static void main(String[] args) {
        configureChromeDriverPath();
        runTask1();
        Task2.run();
        Task3.run();
    }

    private static void runTask1() {
        WebDriver webDriver = null;
        try {
            webDriver = createWebDriver();
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
            WebElement passwordElement = wait.until(driver -> {
                WebElement candidate = driver.findElement(By.cssSelector("#resultid .verybigtext b"));
                if (candidate.getText() != null && !candidate.getText().trim().isEmpty()) {
                    return candidate;
                }
                return null;
            });

            String password = passwordElement.getText().trim();
            System.out.println("Task 1 - generated password: " + password);
        } catch (Exception e) {
            System.out.println("Task 1 Error");
            System.out.println(e.toString());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    private static void configureChromeDriverPath() {
        String propertyPath = System.getProperty("webdriver.chrome.driver");
        if (propertyPath != null && !propertyPath.isEmpty()) {
            return;
        }

        String envPath = System.getenv("CHROMEDRIVER_PATH");
        if (envPath != null && !envPath.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", envPath);
            return;
        }

        System.out.println("CHROMEDRIVER_PATH is not set. Set webdriver.chrome.driver manually if ChromeDriver is not auto-detected.");
    }

    static WebDriver createWebDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        return new ChromeDriver(options);
    }
}
