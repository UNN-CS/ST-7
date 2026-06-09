package ru.unn.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;

public class App {
    public static void main(String[] args) {
        WebDriver driver = initWebDriver();
        try {
            String pwd = task1Password(driver);
            System.out.println(pwd);

            String publicIp = Task2.fetchPublicIp(driver);
            System.out.println(publicIp);

            Task3.exportWeatherForecast(driver);
        } catch (Exception err) {
            System.out.println("Error");
            System.out.println(err);
        } finally {
            try {
                driver.quit();
            } catch (Exception ignored) {
            }
        }
    }

    private static WebDriver initWebDriver() {
        ChromeOptions chromeSettings = new ChromeOptions();

        String chromeExePath = System.getenv("CHROME_BINARY");
        if (chromeExePath != null && !chromeExePath.isBlank()) {
            chromeSettings.setBinary(chromeExePath);
        }

        String driverPath = System.getenv("CHROMEDRIVER_PATH");
        if (driverPath == null || driverPath.isBlank()) {
            driverPath = System.getProperty("webdriver.chrome.driver");
        }
        if (driverPath != null && !driverPath.isBlank()) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        return new ChromeDriver(chromeSettings);
    }

    private static String task1Password(WebDriver webDriver) {
        webDriver.get("https://www.calculator.net/password-generator.html");

        List<By> selectorList = List.of(
                By.id("password"),
                By.cssSelector("input#password"),
                By.cssSelector("input[name='password']"),
                By.cssSelector("input[type='text'][readonly]"),
                By.cssSelector("input[type='text']")
        );

        WebElement targetElement = null;
        for (By selector : selectorList) {
            List<WebElement> elementsFound = webDriver.findElements(selector);
            if (!elementsFound.isEmpty()) {
                targetElement = elementsFound.get(0);
                break;
            }
        }

        if (targetElement == null) {
            throw new IllegalStateException("Password element not found");
        }

        String attributeValue = targetElement.getAttribute("fieldContent");
        if (attributeValue != null && !attributeValue.isBlank()) {
            return attributeValue.trim();
        }
        return targetElement.getText().trim();
    }
}
