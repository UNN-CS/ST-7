package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {
    public static void getWeatherForecast(String pathToDriver) {
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        WebDriver driver = new ChromeDriver();
        
        try {
            String siteUrl = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            
            driver.get(siteUrl);
            WebElement preTag = driver.findElement(By.tagName("pre"));
            
            String jsonData = preTag.getText();
            JSONParser parser = new JSONParser();
            JSONObject mainObj = (JSONObject) parser.parse(jsonData);
            
            JSONObject hourlyData = (JSONObject) mainObj.get("hourly");
            JSONArray timeList = (JSONArray) hourlyData.get("time");
            JSONArray tempList = (JSONArray) hourlyData.get("temperature_2m");
            JSONArray rainList = (JSONArray) hourlyData.get("rain");
            
            System.out.println("\n=== WEATHER FORECAST NIZHNY NOVGOROD ===");
            System.out.println("==================================================");
            System.out.printf("| %-2s | %-20s | %-11s | %-10s |\n", "N", "Date/Time", "Temp(C)", "Rain(mm)");
            System.out.println("==================================================");
            
            for (int idx = 0; idx < timeList.size(); idx++) {
                String dt = (String) timeList.get(idx);
                Number tmp = (Number) tempList.get(idx);
                Number rn = (Number) rainList.get(idx);
                
                System.out.printf("| %-2d | %-20s | %-11.1f | %-10.2f |\n", 
                    (idx + 1), dt, tmp.doubleValue(), rn.doubleValue());
            }
            System.out.println("==================================================");
            
            try (PrintWriter fileOut = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                fileOut.println("=== WEATHER FORECAST NIZHNY NOVGOROD ===");
                fileOut.println("--------------------------------------------------");
                fileOut.printf("| %-2s | %-20s | %-11s | %-10s |\n", "N", "Date/Time", "Temp(C)", "Rain(mm)");
                fileOut.println("--------------------------------------------------");
                
                for (int idx = 0; idx < timeList.size(); idx++) {
                    String dt = (String) timeList.get(idx);
                    Number tmp = (Number) tempList.get(idx);
                    Number rn = (Number) rainList.get(idx);
                    
                    fileOut.printf("| %-2d | %-20s | %-11.1f | %-10.2f |\n", 
                        (idx + 1), dt, tmp.doubleValue(), rn.doubleValue());
                }
                fileOut.println("==================================================");
            }
            
            System.out.println("\nSaved");
            
        } catch (Exception e) {
            System.out.println("Error");
        } finally {
            driver.quit();
        }
    }
}