package com.mycompany.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Task3 {

    private static final String URL =
        "https://api.open-meteo.com/v1/forecast"
        + "?latitude=56&longitude=44"
        + "&hourly=temperature_2m,rain"
        + "&current=cloud_cover"
        + "&timezone=Europe%2FMoscow"
        + "&forecast_days=1&wind_speed_unit=ms";

    private static final Path OUT = Path.of("result", "forecast.txt");

    public void buildForecast() {
        WebDriver driver = App.createDriver();
        try {
            driver.get(URL);
            String body = driver.findElement(By.tagName("body")).getText();

            JSONObject root = (JSONObject) new JSONParser().parse(body);
            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temp = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");

            String table = render(time, temp, rain);
            System.out.println(table);
            save(table);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        } finally {
            driver.quit();
        }
    }

    private String render(JSONArray time, JSONArray temp, JSONArray rain) {
        String nl = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s| %-18s| %-12s| %-11s",
                "No", "Дата/время", "Температура", "Осадки, мм")).append(nl);
        sb.append("----+-------------------+-------------+------------").append(nl);
        for (int i = 0; i < time.size(); i++) {
            double t = ((Number) temp.get(i)).doubleValue();
            double r = ((Number) rain.get(i)).doubleValue();
            sb.append(String.format("%-4d| %-18s| %-12.1f| %-11.2f",
                    i + 1, time.get(i), t, r)).append(nl);
        }
        return sb.toString();
    }

    private void save(String table) throws IOException {
        Files.createDirectories(OUT.getParent());
        Files.writeString(OUT, table, StandardCharsets.UTF_8);
        System.out.println("Сохранено в " + OUT);
    }
}