package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nyuti\\repos\\ST-7\\chromedriver.exe");
        
        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        try {
            System.out.println("--- Задание 1 ---");
            try {
                webDriver.get("https://www.calculator.net/password-generator.html");
                WebElement passwordElem = webDriver.findElement(By.className("verybigtext"));
                String fullText = passwordElem.getText();
                System.out.println("Результат: " + fullText);
            } catch (Exception e) {
                System.out.println("Ошибка в Задании 1: " + e.getMessage());
            }

            System.out.println("\n--- Задание 2 ---");
            Task2.run(webDriver);

            System.out.println("\n--- Задание 3 ---");
            Task3.run(webDriver);

        } finally {
            System.out.println("\nРабота завершена. Закрываю браузер...");
            webDriver.quit();
        }
    }
}