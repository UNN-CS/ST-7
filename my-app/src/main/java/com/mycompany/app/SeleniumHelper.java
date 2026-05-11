package com.mycompany.app;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class SeleniumHelper {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[\\S]{8,}$");

    private SeleniumHelper() {
    }

    public static void withDriver(String driverPath, Consumer<WebDriver> action) {
        WebDriver webDriver = null;
        try {
            System.setProperty("webdriver.chrome.driver", driverPath);
            webDriver = new ChromeDriver();
            action.accept(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    public static String extractGeneratedPassword(WebDriver webDriver) {
        new WebDriverWait(webDriver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

        List<By> candidateLocators = List.of(
                By.cssSelector("input[id*='password' i]"),
                By.cssSelector("input[name*='password' i]"),
                By.cssSelector("input[readonly]"),
                By.cssSelector("textarea"),
                By.cssSelector("input[type='text']")
        );
        for (By locator : candidateLocators) {
            for (WebElement element : webDriver.findElements(locator)) {
                String value = element.getAttribute("value");
                if (isLikelyPassword(value)) {
                    return value;
                }
                String text = element.getText();
                if (isLikelyPassword(text)) {
                    return text;
                }
            }
        }

        for (WebElement element : webDriver.findElements(By.cssSelector("body *"))) {
            String text = element.getText();
            if (isLikelyPassword(text)) {
                return text.trim();
            }
        }
        return null;
    }

    public static String readJsonPayload(WebDriver webDriver) {
        List<WebElement> preElements = webDriver.findElements(By.tagName("pre"));
        if (!preElements.isEmpty()) {
            return preElements.get(0).getText();
        }

        String bodyText = webDriver.findElement(By.tagName("body")).getText();
        if (bodyText != null && !bodyText.isBlank()) {
            return bodyText;
        }
        return webDriver.getPageSource();
    }

    private static boolean isLikelyPassword(String value) {
        return value != null && PASSWORD_PATTERN.matcher(value.trim()).matches();
    }
}
