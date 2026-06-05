package ru.unn.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class App {
    public static void main(String[] args) {
        WebDriver driver = initializeWebDriver();
        try {
            String password = task1Password(driver);
            System.out.println(password);

            String ip = Task2.getIp(driver);
            System.out.println(ip);

            Task3.saveForecast(driver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        } finally {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    private static WebDriver initializeWebDriver() {
        ChromeOptions browserOptions = new ChromeOptions();

        String browserExecutablePath = System.getenv("CHROME_BINARY");
        if (browserExecutablePath != null && !browserExecutablePath.isBlank()) {
            browserOptions.setBinary(browserExecutablePath);
        }

        String webDriverLocation = System.getenv("CHROMEDRIVER_PATH");
        if (webDriverLocation == null || webDriverLocation.isBlank()) {
            webDriverLocation = System.getProperty("webdriver.chrome.driver");
        }
        if (webDriverLocation != null && !webDriverLocation.isBlank()) {
            System.setProperty("webdriver.chrome.driver", webDriverLocation);
        }

        return new ChromeDriver(browserOptions);
    }

    private static String task1Password(WebDriver driver) {
        driver.get("https://www.calculator.net/password-generator.html");

        List<By> selectors = List.of(
                By.id("password"),
                By.cssSelector("input#password"),
                By.cssSelector("input[name='password']"),
                By.cssSelector("input[type='text'][readonly]"),
                By.cssSelector("input[type='text']")
        );

        WebElement el = null;
        for (By locator : selectors) {
            List<WebElement> matchingElements = driver.findElements(locator);
            if (!matchingElements.isEmpty()) {
                el = matchingElements.get(0);
                break;
            }
        }

        if (el == null) {
            throw new IllegalStateException("Password element not matchingElements");
        }

        String fieldValue = el.getAttribute("fieldValue");
        if (fieldValue != null && !fieldValue.isBlank()) {
            return fieldValue.trim();
        }
        return el.getText().trim();
    }
}

