package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.PrintWriter;

public class Task3 {
    public static void execute(WebDriver webDriver) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(url);
            
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String json_str = elem.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            JSONObject hourly = (JSONObject) obj.get("hourly");
            
            JSONArray timeArray = (JSONArray) hourly.get("time");
            JSONArray tempArray = (JSONArray) hourly.get("temperature_2m");
            JSONArray rainArray = (JSONArray) hourly.get("rain");

            File dir = new File("result");
            if (!dir.exists()) {
                dir.mkdirs();
            }

            try (PrintWriter writer = new PrintWriter("result/forecast.txt", "UTF-8")) {
                String header = String.format("| %-3s | %-16s | %-18s | %-12s |", "№", "Дата/время", "Температура (°C)", "Осадки (мм)");
                String separator = "|-----|------------------|--------------------|--------------|";

                System.out.println(header);
                System.out.println(separator);
                writer.println(header);
                writer.println(separator);

                for (int i = 0; i < timeArray.size(); i++) {
                    String row = String.format(
                        "| %-3d | %-16s | %-18s | %-12s |", 
                        (i + 1), 
                        timeArray.get(i), 
                        tempArray.get(i), 
                        rainArray.get(i)
                    );

                    System.out.println(row);
                    writer.println(row);
                }
                System.out.println("\nraw file: result/forecast.txt");
            }
            
        } catch (Exception e) {
            System.out.println("Error: Task 3");
            System.out.println(e.toString());
        }
    }
}