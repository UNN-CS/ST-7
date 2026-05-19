package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.FileWriter;
import java.io.File;

public class Task3 {
    public static void run(WebDriver webDriver) {
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe%2FMoscow&forecast_days=1";
            webDriver.get(url);
            String json_str = webDriver.findElement(By.tagName("pre")).getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            String header = "№\tДата/время\t\tТемпература\tОсадки (мм)\n";
            StringBuilder table = new StringBuilder(header);

            for (int i = 0; i < times.size(); i++) {
                table.append(String.format("%d\t%s\t%s°C\t\t%s\n", 
                    i + 1, times.get(i), temps.get(i), rains.get(i)));
            }

            System.out.println("Задание 3: Прогноз погоды сформирован.");
            System.out.println(table.toString());

            java.io.File dir = new java.io.File("result");
            if (!dir.exists()) dir.mkdir();
            try (FileWriter writer = new FileWriter("result/forecast.txt")) {
                writer.write(table.toString());
            }

        } catch (Exception e) {
            System.out.println("Ошибка в Задании 3: " + e.getMessage());
        }
    }
}