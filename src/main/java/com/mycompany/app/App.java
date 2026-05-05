package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {

    public static void main(String[] args) {
        String driverPath = System.getenv("CHROME_DRIVER_PATH");
        if (driverPath != null && !driverPath.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS_CHROME", "false"))) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage",
                    "--disable-gpu", "--window-size=1280,1024");
        }

        WebDriver webDriver = new ChromeDriver(options);
        try {
            taskPasswordGenerator(webDriver);
            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }

    private static void taskPasswordGenerator(WebDriver webDriver) {
        webDriver.get("https://www.calculator.net/password-generator.html");
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
            WebElement generate = wait.until(d -> d.findElement(By.name("submit1")));
            generate.click();
            WebElement pwdEl = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("#resultid div.verybigtext b"))));
            String password = pwdEl.getText().trim();
            System.out.println("Password from calculator.net: " + password);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
