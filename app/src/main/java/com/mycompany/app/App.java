package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        String driverPath = "/usr/local/bin/chromedriver";
        
        System.out.println("=== ЗАДАНИЕ №1 ===");
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver webDriver = new ChromeDriver();
        
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            
            WebElement generateBtn = webDriver.findElement(By.cssSelector("input[value='Generate']"));
            generateBtn.click();
            
            WebElement passwordField = webDriver.findElement(By.id("copy"));
            String password = passwordField.getAttribute("value");
            
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            webDriver.quit();
        }
        
        System.out.println("=== ЗАДАНИЕ №2 ===");
        Task2.getMyIp(driverPath);
        
        System.out.println("\n=== ЗАДАНИЕ №3 ===");
        Task3.getWeatherForecast(driverPath);
    }
}