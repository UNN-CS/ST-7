package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            String password = new WebDriverWait(webDriver, Duration.ofSeconds(6))
                .ignoring(StaleElementReferenceException.class)
                .until(d -> d.findElement(By.cssSelector("#resultid .verybigtext b")).getText());
            System.out.println(password);

            Task2.getIpAddress(webDriver);
            Task3.getWeatherForecast(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
        System.exit(0);
    }
}
