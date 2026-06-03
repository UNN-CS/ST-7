package ru.unn.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:/ST8/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement passwordDiv = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("div.verybigtext b")
            ));
            String password = passwordDiv.getText();
            System.out.println("Задание 1. Сгенерированный пароль: " + password);

            Task2.getMyPublicIp(driver);

            Task3.saveWeatherForecast(driver, "result/forecast.txt");
            
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
