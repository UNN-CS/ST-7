package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "/home/user/Загрузки/chromedriver-linux64/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/home/user/Загрузки/chrome-linux64/chrome");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement passwordElem = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".verybigtext > b"))
            );
            String password = passwordElem.getText();

            System.out.println("=== Задание 1 ===");
            System.out.println("Сгенерированный пароль: " + password);
        } catch (Exception e) {
            System.out.println("Ошибка в задании 1: " + e.toString());
        } finally {
            driver.quit();
        }

        Task2.execute();

        Task3.execute();
    }
}
