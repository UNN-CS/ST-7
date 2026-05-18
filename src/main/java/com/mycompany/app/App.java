package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class App 
{
    public static void main( String[] args )
    {
        ChromeOptions options = new ChromeOptions();

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver(options);

        try {
            // Задание 1:
            driver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            WebElement passwordElem = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.verybigtext b"))
            );
            String password = passwordElem.getText();

            System.out.println("\nЗадание 1:");
            System.out.println("Сгенерированный пароль: " + password);

            // Задание 2:
            System.out.println("\nЗадание 2:");
            Task2.getIpAddress(driver);
            
            // Задание 3:
            System.out.println("\nЗадание3:");
            Task3.getWeatherForecast(driver);
            
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}