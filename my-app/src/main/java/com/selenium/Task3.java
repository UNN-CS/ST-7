package com.selenium;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    
    public static void runTask3() {
        try {
            WebDriver webDriver = new ChromeDriver();
            
            System.out.println("\nЗадание 3");
            System.out.println("Получение прогноза погоды для Нижнего Новгорода по координатам (56, 44)");
            
            String url = "https://api.open-meteo.com/v1/forecast?" +
                        "latitude=56&longitude=44" +
                        "&hourly=temperature_2m,rain" +
                        "&timezone=Europe%2FMoscow" +
                        "&forecast_days=1" +
                        "&wind_speed_unit=ms";
            
            webDriver.get(url);
            
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray timeArray = (JSONArray) hourly.get("time");
            JSONArray tempArray = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourly.get("rain");
            
            List<String[]> weatherData = new ArrayList<>();
            
            System.out.println("\n№\tДата/время\t\tТемпература\tОсадки (мм)");
            System.out.println("----------------------------------------------------------------");
            
            for (int i = 0; i < timeArray.size(); i++) {
                String time = (String) timeArray.get(i);
                double temp = (Double) tempArray.get(i);
                double rain = (Double) rainArray.get(i);
                
                System.out.printf("%d\t%s\t%.1f°C\t\t%.1f%n", 
                    i + 1, time, temp, rain);
                
                weatherData.add(new String[]{
                    String.valueOf(i + 1),
                    time,
                    String.format("%.1f°C", temp),
                    String.format("%.1f", rain)
                });
            }
            
            saveWeatherToFile(weatherData);
            System.out.println("\nДанные сохранены в файл result/forecast.txt");
            System.out.println("\n");
            
            webDriver.quit();
            
        } catch (Exception e) {
            System.out.println("Ошибка 3");
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
    
    private static void saveWeatherToFile(List<String[]> data) {
        try {
            new java.io.File("result").mkdirs();
            PrintWriter writer = new PrintWriter(new BufferedWriter(
                new FileWriter("result/forecast.txt")));
            
            writer.println("№\tДата/время\t\tТемпература\tОсадки (мм)");
            writer.println("----------------------------------------------------------------");
            
            for (String[] row : data) {
                writer.printf("%s\t%s\t\t%s\t\t%s%n", 
                    row[0], row[1], row[2], row[3]);
            }
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Ошбика 3 (не сохранилось): " + e.toString());
        }
    }
}