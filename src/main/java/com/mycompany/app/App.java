package com.mycompany.app;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {

    public static void main(String[] args) {
        System.out.println("=== Задание №1: генератор паролей ===");
        System.out.println("Пароль: " + generatePassword());

        System.out.println();
        System.out.println("=== Задание №2: IP-адрес клиента ===");
        System.out.println("IP: " + new Task2().fetchIp());

        System.out.println();
        System.out.println("=== Задание №3: прогноз погоды ===");
        new Task3().buildForecast();
    }

    public static WebDriver createDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");

        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static String generatePassword() {
        WebDriver driver = createDriver();
        try {
            driver.get("https://www.calculator.net/password-generator.html");
            WebElement field = driver.findElement(By.id("password"));
            return field.getAttribute("value");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return "";
        } finally {
            driver.quit();
        }
    }
}