package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\nyuti\\repos\\ST-7\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        try {
            System.out.println("Задание №1");
            webDriver.get("https://www.calculator.net/password-generator.html");
            WebElement passwordElem = webDriver.findElement(By.className("verybigtext"));
            String password = passwordElem.getText();
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();

        } catch (Exception e) {
            System.out.println("Ошибка при выполнении задания №1");
            e.printStackTrace();
        } finally {
            webDriver.quit();   
        }

        Task2.printMyIP();          
        Task3.generateWeatherForecast(); 
    }
}