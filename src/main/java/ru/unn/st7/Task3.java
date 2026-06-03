package ru.unn.st7;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void saveWeatherForecast(WebDriver driver, String outputFile) throws Exception {
        String apiUrl = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
        driver.get(apiUrl);

        WebElement preBlock = driver.findElement(By.tagName("pre"));
        String jsonData = preBlock.getText();

        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonData);
        JSONObject hourly = (JSONObject) root.get("hourly");
        JSONArray timeList = (JSONArray) hourly.get("time");
        JSONArray tempList = (JSONArray) hourly.get("temperature_2m");
        JSONArray rainList = (JSONArray) hourly.get("rain");

        List<String> tableRows = new ArrayList<>();
        tableRows.add("=============================================================");
        tableRows.add(String.format("%-4s | %-20s | %-10s | %-10s", "№", "Дата/время", "Т°C", "Осадки, мм"));
        tableRows.add("=============================================================");
        
        for (int i = 0; i < timeList.size(); i++) {
            String time = (String) timeList.get(i);
            double temperature = (double) tempList.get(i);
            double rain = (double) rainList.get(i);
            String line = String.format("%-4d | %-20s | %-10.1f | %-10.2f", i+1, time, temperature, rain);
            tableRows.add(line);
        }
        tableRows.add("=============================================================");

        Path outputPath = Path.of(outputFile);
        if (outputPath.getParent() != null && !Files.exists(outputPath.getParent())) {
            Files.createDirectories(outputPath.getParent());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            for (String row : tableRows) {
                writer.println(row);
            }
        }

        System.out.println("Задание 3. Прогноз погоды на сутки (Нижний Новгород):");
        for (String row : tableRows) {
            System.out.println(row);
        }
        System.out.println("Таблица сохранена в файл: " + outputFile);
    }
}
