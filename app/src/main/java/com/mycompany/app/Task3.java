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
    public static void getWeatherForecast(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver webDriver = new ChromeDriver();
        
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            
            webDriver.get(url);
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            
            String jsonStr = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");
            
            System.out.println("\n=== ПРОГНОЗ ПОГОДЫ НИЖНИЙ НОВГОРОД ===");
            System.out.println("--------------------------------------------------");
            System.out.printf("| %-2s | %-20s | %-11s | %-10s |\n", "№", "Дата/время", "Температура", "Осадки (мм)");
            System.out.println("--------------------------------------------------");
            
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                Number temp = (Number) temperatures.get(i);
                Number rain = (Number) rains.get(i);
                
                System.out.printf("| %-2d | %-20s | %-11.1f | %-10.2f |\n", 
                    (i + 1), time, temp.doubleValue(), rain.doubleValue());
            }
            System.out.println("--------------------------------------------------");
            
            try (PrintWriter writer = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                writer.println("=== ПРОГНОЗ ПОГОДЫ НИЖНИЙ НОВГОРОД ===");
                writer.println("--------------------------------------------------");
                writer.printf("| %-2s | %-20s | %-11s | %-10s |\n", "№", "Дата/время", "Температура", "Осадки (мм)");
                writer.println("--------------------------------------------------");
                
                for (int i = 0; i < times.size(); i++) {
                    String time = (String) times.get(i);
                    Number temp = (Number) temperatures.get(i);
                    Number rain = (Number) rains.get(i);
                    
                    writer.printf("| %-2d | %-20s | %-11.1f | %-10.2f |\n", 
                        (i + 1), time, temp.doubleValue(), rain.doubleValue());
                }
                writer.println("--------------------------------------------------");
            }
            
            System.out.println("\nТаблица сохранена в result/forecast.txt");
            
        } catch (Exception e) {
            System.out.println("Error in Task3: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}