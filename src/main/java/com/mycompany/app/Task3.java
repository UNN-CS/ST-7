package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {
    public static void getWeatherForecast(WebDriver driver) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            driver.get(url);
            
            WebElement preElem = driver.findElement(By.tagName("pre"));
            String jsonText = preElem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject fullData = (JSONObject) parser.parse(jsonText);
            
            JSONObject hourly = (JSONObject) fullData.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");
            
            System.out.printf("%-3s | %-20s | %-12s | %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            System.out.println("-----------------------------------------------------");
            
            FileWriter fw = new FileWriter("result/forecast.txt", java.nio.charset.StandardCharsets.UTF_8);
            PrintWriter pw = new PrintWriter(fw);
            pw.printf("%-3s | %-20s | %-12s | %-10s%n", "№", "Дата/время", "Температура", "Осадки (мм)");
            pw.println("-----------------------------------------------------");
            
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                Double temp = (Double) temps.get(i);
                Double rain = (Double) rains.get(i);
                
                System.out.printf("%-3d | %-20s | %-12.1f | %-10.2f%n", i+1, time, temp, rain);
                pw.printf("%-3d | %-20s | %-12.1f | %-10.2f%n", i+1, time, temp, rain);
            }
            pw.close();
            System.out.println("\nРезультат сохранен в result/forecast.txt");
            
        } catch (Exception e) {
            System.err.println("Ошибка получения погоды: " + e.getMessage());
        }
    }
}