package com.nazyrov;

import org.openqa.selenium.WebDriver;

public class Task2 {
    
    public static String getIpAddress(WebDriver driver) {
        try {
            driver.get("https://api.ipify.org/?format=json");
            Thread.sleep(500);
            
            String pageSource = driver.getPageSource();
            // Простой парсинг JSON
            int start = pageSource.indexOf("\"ip\":\"") + 6;
            int end = pageSource.indexOf("\"", start);
            return pageSource.substring(start, end);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}