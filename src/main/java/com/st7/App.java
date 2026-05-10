package com.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    public static void main(String[] args) {
        String driverPath = System.getenv("CHROME_DRIVER_PATH");
        if (driverPath == null) {
            driverPath = "/run/current-system/sw/bin/chromedriver";
        }
        System.setProperty("webdriver.chrome.driver", driverPath);

        WebDriver driver = new ChromeDriver();
        try {
            // ========== Задание №1 ==========
            driver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Пытаемся найти кнопку генерации по XPath
            WebElement generateBtn = null;
            try {
                generateBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//input[@value='Generate Password(s)']")
                ));
            } catch (Exception e) {
                System.out.println("Кнопка генерации не найдена по XPath. Пробуем другой селектор...");
            }

            if (generateBtn != null) {
                generateBtn.click();
                Thread.sleep(500);
                WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("generated-password")));
                String password = passwordField.getAttribute("value");
                System.out.println("=== Задание №1 ===");
                System.out.println("Сгенерированный пароль: " + password);
            } else {
                System.out.println("=== Задание №1 ===");
                System.out.println("Не удалось найти кнопку генерации. Пропускаем задание №1.");
            }
            System.out.println();

            // ========== Задание №2 ==========
            Task2.execute(driver);

            // ========== Задание №3 ==========
            Task3.execute(driver);

        } catch (Exception e) {
            System.err.println("Общая ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
