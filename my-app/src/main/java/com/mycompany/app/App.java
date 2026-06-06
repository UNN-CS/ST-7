package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {

    public static void main(String[] args) {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        WebDriver webDriver = new ChromeDriver(options);

        try {
            System.out.println("=== Задание №1: Генератор паролей ===");
            webDriver.get("https://www.calculator.net/password-generator.html");
            System.out.println("Page loaded: " + webDriver.getCurrentUrl());

            Thread.sleep(1000);

            WebElement lengthField = webDriver.findElement(By.id("resultid"));
            WebElement resContainer = lengthField.findElement(By.className("verybigtext"));
            String password = resContainer.findElement(By.tagName("b")).getText();

            System.out.println("Generated password: " + password);
            System.out.println();

            System.out.println("=== Задание №2: Получение IP-адреса ===");
            Task2.getIpAddress(webDriver);
            System.out.println();

            System.out.println("=== Задание №3: Прогноз погоды ===");
            Task3.getWeatherForecast(webDriver);

        } catch (Exception e) {
            System.out.println("Error:");
            System.out.println(e.toString());
            e.printStackTrace();
        } finally {
            System.out.println("\nЗакрываем браузер...");
            webDriver.quit();
        }
    }
}
