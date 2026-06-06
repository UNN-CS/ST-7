package com.mycompany.app;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {
    private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44" +
            "&hourly=temperature_2m,rain&current=cloud_cover" +
            "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void getWeatherForecast(WebDriver webDriver) {
        try {
            webDriver.get(WEATHER_URL);
            System.out.println("Page loaded: " + webDriver.getCurrentUrl());

            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) obj.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            String header = String.format("| %-4s | %-20s | %-12s | %-12s |%n",
                    "№", "Дата/время", "Температура", "Осадки (мм)");
            String separator = String.format("|%s|%s|%s|%s|%n",
                    "-".repeat(6), "-".repeat(22), "-".repeat(14), "-".repeat(14));

            table.append(header);
            table.append(separator);

            System.out.println("\nПрогноз погоды для Нижнего Новгорода (56, 44):");
            System.out.print(header);
            System.out.print(separator);

            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                double temp = ((Number) temperatures.get(i)).doubleValue();
                double rain = ((Number) rains.get(i)).doubleValue();

                String row = String.format("| %-4d | %-20s | %-12s | %-12s |%n",
                        i + 1, time, temp + "°C", rain);
                table.append(row);

                System.out.printf("| %-4d | %-20s | %-12s | %-12s |%n",
                        i + 1, time, temp + "°C", rain);
            }

            saveToFile(table.toString());

        } catch (Exception e) {
            System.out.println("Error in Task3:");
            System.out.println(e.toString());
        }
    }

    private static void saveToFile(String content) {
        try {
            java.io.File dir = new java.io.File("result");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            PrintWriter writer = new PrintWriter(new FileWriter("result/forecast.txt"));
            writer.print(content);
            writer.close();
            System.out.println("\nРезультат сохранен в файл: result/forecast.txt");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.toString());
        }
    }
}
