package com.mycompany.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

public class Task3 {
    private static final String FORECAST_URL =
        "https://api.open-meteo.com/v1/forecast"
        + "?latitude=56&longitude=44"
        + "&hourly=temperature_2m,rain"
        + "&current=cloud_cover"
        + "&timezone=Europe%2FMoscow"
        + "&forecast_days=1"
        + "&wind_speed_unit=ms";

    private static final Path FORECAST_FILE =
        Paths.get("result", "forecast.txt");

    private Task3() {
    }

    public static String getForecastTable(WebDriver webDriver) {
        try {
            webDriver.get(FORECAST_URL);
            String json = Task2.getPageText(webDriver);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            JSONObject hourly = (JSONObject) object.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            table.append("\u2116    Date/time    Temperature    Rain (mm)")
                .append(System.lineSeparator());

            for (int i = 0; i < times.size(); i++) {
                table.append(i + 1)
                    .append("    ")
                    .append(times.get(i))
                    .append("    ")
                    .append(temperatures.get(i))
                    .append("    ")
                    .append(rains.get(i))
                    .append(System.lineSeparator());
            }

            return table.toString();
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read weather forecast", e);
        }
    }

    public static void saveForecastTable(String table) {
        try {
            Files.createDirectories(FORECAST_FILE.getParent());
            Files.write(FORECAST_FILE, table.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IllegalStateException("Cannot save weather forecast", e);
        }
    }
}
