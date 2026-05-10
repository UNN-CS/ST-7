package com.st7;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {
    public static void execute(WebDriver driver) {
        String url = "https://api.open-meteo.com/v1/forecast" +
                "?latitude=56&longitude=44" +
                "&hourly=temperature_2m,rain" +
                "&timezone=Europe%2FMoscow" +
                "&forecast_days=1" +
                "&wind_speed_unit=ms";

        try {
            driver.get(url);
            WebElement pre = driver.findElement(By.tagName("pre"));
            String jsonText = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            JSONObject hourly = (JSONObject) json.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            System.out.println("=== Задание №3 ===");
            String header = String.format("%-4s | %-20s | %-11s | %-10s",
                    "№", "Дата/время", "Температура,°C", "Осадки, мм");
            System.out.println(header);
            System.out.println("-------------------------------------------------------------");

            File resultDir = new File("result");
            if (!resultDir.exists()) resultDir.mkdirs();

            try (PrintWriter writer = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                writer.println(header);
                writer.println("-------------------------------------------------------------");
                for (int i = 0; i < times.size(); i++) {
                    String time = (String) times.get(i);
                    Object tempObj = temps.get(i);
                    Object rainObj = rains.get(i);
                    double temp = (tempObj instanceof Number) ? ((Number) tempObj).doubleValue() : 0.0;
                    double rain = (rainObj instanceof Number) ? ((Number) rainObj).doubleValue() : 0.0;

                    String line = String.format("%-4d | %-20s | %-11.1f | %-10.2f",
                            i + 1, time, temp, rain);
                    System.out.println(line);
                    writer.println(line);
                }
            }
            System.out.println("\nТаблица прогноза сохранена в result/forecast.txt");
        } catch (Exception e) {
            System.err.println("Ошибка в Task3: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
