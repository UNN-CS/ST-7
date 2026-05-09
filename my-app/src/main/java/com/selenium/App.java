package com.selenium;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    // private static final String CHROME_DRIVER_PATH = "/usr/local/bin/chromedriver";
    
    public static void main(String[] args) {
        System.out.println("Запуск Selenium тестов:");
        // System.out.println("ChromeDriver path: " + CHROME_DRIVER_PATH);

        runTask1(); // генерация пароля
        
        Task2.runTask2(); // получение IP (убрали параметр пути)
        
        Task3.runTask3(); // погода (убрали параметр пути)
        
        System.out.println("\nУспешно!");
    }
    
    private static void runTask1() {
        try {
            WebDriver webDriver = new ChromeDriver();
            
            System.out.println("\nЗадание 1");
            System.out.println("Открытие генератора паролей:");
            
            webDriver.get("https://www.calculator.net/password-generator.html");
            
            Thread.sleep(2000);
            
            try {
                WebElement passwordElement = webDriver.findElement(
                    By.cssSelector("input[id*='password'], input[name*='password'], .password")
                );
                
                String password = passwordElement.getAttribute("value");
                
                if (password == null || password.isEmpty()) {
                    password = passwordElement.getText();
                }
                
                if (password != null && !password.isEmpty()) {
                    System.out.println("Сгенерированный пароль: " + password);
                } else {
                    System.out.println("Пароль не найден");
                    System.out.println("Источник: " + webDriver.getPageSource().substring(0, 500));
                }
                
            } catch (Exception e) {
                System.out.println("Не удалось найти элемент пароля автоматически"); // Браузер можно открыть вручную
            }
            
            System.out.println("\n");
            
            Thread.sleep(3000);
            webDriver.quit();
        } catch (Exception e) {
            System.out.println("Ошибка 1");
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}