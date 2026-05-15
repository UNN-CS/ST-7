package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {
    public static void getWeatherForecast(WebDriver webDriver) {
        try {
            webDriver.get("https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms");
            JSONObject obj = (JSONObject) new JSONParser()
                .parse(webDriver.findElement(By.tagName("pre")).getText());

            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");

            StringBuilder sb = new StringBuilder();
            sb.append("|№   |  Дата/время      | Температура | Осадки (мм)  |\n");
            sb.append("| -- | ---------------- | ----------- | ------------ |\n");

            for (int i = 0; i < time.size(); i++) {
                sb.append(String.format("|%4d| %s | %-12s| %-13s|\n",
                    i + 1, time.get(i), temperature.get(i), rain.get(i)));
            }

            System.out.print(sb);
            Files.writeString(Path.of("result/forecast.txt"), sb);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
