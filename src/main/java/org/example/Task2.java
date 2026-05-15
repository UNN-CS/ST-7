package org.example;

import java.time.Duration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Task2 {
    private Task2() {
    }

    public static void run() {
        WebDriver webDriver = null;
        try {
            webDriver = App.createWebDriver();
            webDriver.get("https://api.ipify.org/?format=json");
            String jsonText = extractJsonText(webDriver);

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonText);
            String ip = String.valueOf(jsonObject.get("ip"));
            System.out.println("Task 2 - client IPv4: " + ip);
        } catch (Exception e) {
            System.out.println("Task 2 Error");
            System.out.println(e.toString());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    static String extractJsonText(WebDriver webDriver) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        try {
            String text = wait.until(driver -> {
                try {
                    WebElement pre = driver.findElement(By.tagName("pre"));
                    String value = pre.getText();
                    if (value != null && !value.trim().isEmpty()) {
                        return value.trim();
                    }
                } catch (Exception ignored) {
                    // <pre> not yet present; try <body> below.
                }
                try {
                    WebElement body = driver.findElement(By.tagName("body"));
                    String value = body.getText();
                    if (value != null && !value.trim().isEmpty()) {
                        return value.trim();
                    }
                } catch (Exception ignored) {
                    // body not ready yet
                }
                return null;
            });
            if (text != null && !text.isEmpty()) {
                return text;
            }
        } catch (Exception ignored) {
            // Fall through to page source fallback.
        }

        String source = webDriver.getPageSource();
        if (source == null) {
            return "";
        }
        int start = source.indexOf('{');
        int end = source.lastIndexOf('}');
        if (start >= 0 && end > start) {
            return source.substring(start, end + 1);
        }
        return source.trim();
    }
}
