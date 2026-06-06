package ru.unn.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class App {
    public static void main(String[] args) {
        WebDriver webDriver = initWebDriver();
        try {
            String password = task1Password(webDriver);
            System.out.println(password);

            String ip = Task2.fetchPublicIp(webDriver);
            System.out.println(ip);

            Task3.exportWeatherForecast(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        } finally {
            try {
                webDriver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    private static WebDriver initWebDriver() {
        ChromeOptions chromeConfig = new ChromeOptions();

        String browserPath = System.getenv("CHROME_BINARY");
        if (browserPath != null && !browserPath.isBlank()) {
            chromeConfig.setBinary(browserPath);
        }

        String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
        if (chromeDriverPath == null || chromeDriverPath.isBlank()) {
            chromeDriverPath = System.getProperty("webdriver.chrome.driver");
        }
        if (chromeDriverPath != null && !chromeDriverPath.isBlank()) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        return new ChromeDriver(chromeConfig);
    }

    private static String task1Password(WebDriver webDriver) {
        webDriver.get("https://www.calculator.net/password-generator.html");

        List<By> locators = List.of(
                By.id("password"),
                By.cssSelector("input#password"),
                By.cssSelector("input[name='password']"),
                By.cssSelector("input[type='text'][readonly]"),
                By.cssSelector("input[type='text']")
        );

        WebElement el = null;
        for (By locator : locators) {
            List<WebElement> found = webDriver.findElements(locator);
            if (!found.isEmpty()) {
                el = found.get(0);
                break;
            }
        }

        if (el == null) {
            throw new IllegalStateException("Password element not found");
        }

        String fieldContent = el.getAttribute("fieldContent");
        if (fieldContent != null && !fieldContent.isBlank()) {
            return fieldContent.trim();
        }
        return el.getText().trim();
    }
}

