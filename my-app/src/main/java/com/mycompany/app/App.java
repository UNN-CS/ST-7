package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App
{
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:/drivers/chromedriver-win64/chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("resultid")));

            WebElement resultElement = webDriver.findElement(By.id("resultid"));
            String password = resultElement.getText().trim();

            System.out.println("=== Задание №1 ===");
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();

            webDriver.get("https://api.ipify.org/?format=json");
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("pre")));

            String jsonString = webDriver.findElement(By.tagName("pre")).getText();
            String ipAddress = jsonString.replaceAll(".*\"ip\":\"([^\"]+)\".*", "$1");

            System.out.println("=== Задание №2 ===");
            System.out.println("Ваш IP-адрес: " + ipAddress);
            System.out.println();

            Task3.getWeatherForecast();

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}