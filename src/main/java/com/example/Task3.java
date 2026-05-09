package com.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    
    public static void getWeatherForecast() {
        WebDriver webDriver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(url);
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            
            String json_str = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");
            
            System.out.println("\nПрогноз погоды на Нижний Новгород:");
            System.out.println(String.format("| %-3s | %-19s | %-11s | %-12s |", "№", "Дата/время", "Температура", "Осадки (мм)"));
            System.out.println("|-----|---------------------|-------------|--------------|");
            
            StringBuilder forecastTable = new StringBuilder();
            forecastTable.append(String.format("| %-3s | %-19s | %-11s | %-12s |\n", "№", "Дата/время", "Температура", "Осадки (мм)"));
            
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                double temp = ((Number) temperatures.get(i)).doubleValue();
                double rain = ((Number) rains.get(i)).doubleValue();
                
                System.out.println(String.format("| %-3d | %-19s | %-11.1f | %-12.2f |", i + 1, time, temp, rain));
                forecastTable.append(String.format("| %-3d | %-19s | %-11.1f | %-12.2f |\n", i + 1, time, temp, rain));
            }
            
            try (FileWriter writer = new FileWriter("result/forecast.txt")) {
                writer.write(forecastTable.toString());
                System.out.println("\nТаблица сохранена в result/forecast.txt");
            } catch (IOException e) {
                System.out.println("Ошибка при сохранении файла: " + e.toString());
            }
            
        } catch (Exception e) {
            System.out.println("Error in Task3");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
