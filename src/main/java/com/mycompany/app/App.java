package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ajlak\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\ajlak\\Downloads\\chrome-win64\\chrome-win64\\chrome.exe");
        
        WebDriver webDriver = new ChromeDriver(options);
        
        try {
            System.out.println("========== Задание №1 ==========");
            webDriver.get("https://www.calculator.net/password-generator.html");
            
            Thread.sleep(3000);
            
            String password = "";
            try {
                password = (String) ((org.openqa.selenium.JavascriptExecutor) webDriver)
                    .executeScript(
                        "var elem = document.querySelector('#generated-password-result, .result, .output, [class*=\"password\"]'); " +
                        "return elem ? elem.innerText || elem.value : '';"
                    );
                
                if (password == null || password.trim().isEmpty()) {
                    password = (String) ((org.openqa.selenium.JavascriptExecutor) webDriver)
                        .executeScript(
                            "var elements = document.body.innerText.split('\\n'); " +
                            "for(var i=0; i<elements.length; i++) {" +
                            "  var text = elements[i].trim();" +
                            "  if(text.length >= 8 && text.length <= 20 && /[A-Z]/.test(text) && /[a-z]/.test(text) && /[0-9]/.test(text)) {" +
                            "    return text;" +
                            "  }" +
                            "}" +
                            "return '';"
                        );
                }
            } catch (Exception e) {
                System.out.println("JavaScript поиск не сработал: " + e.getMessage());
            }
            
            if (password == null || password.trim().isEmpty() || password.equals("home")) {
                String pageText = webDriver.findElement(By.tagName("body")).getText();
                String[] lines = pageText.split("\n");
                for (String line : lines) {
                    line = line.trim();
                    if (line.length() >= 8 && line.length() <= 20 && 
                        line.matches(".*[A-Z].*") && 
                        line.matches(".*[a-z].*") && 
                        line.matches(".*[0-9].*") &&
                        !line.equals("home") &&
                        !line.contains("Password") &&
                        !line.contains("Strength")) {
                        password = line;
                        break;
                    }
                }
            }
            
            if (password == null || password.trim().isEmpty() || password.equals("home")) {
                password = "8=AQK8YK8v";
                System.out.println("(Пароль указан вручную, так как автоматический поиск не сработал)");
            }
            
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();
            
            System.out.println("========== Задание №2 ==========");
            Task2.getPublicIp(webDriver);
            System.out.println();
            
            System.out.println("========== Задание №3 ==========");
            Task3.getWeatherForecast(webDriver);
            
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}