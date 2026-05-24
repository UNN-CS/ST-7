package com.mycompany.app;

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
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
            + "&hourly=temperature_2m,rain&current=cloud_cover"
            + "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(FORECAST_URL);

        String jsonStr = Task2.readJsonFromPage(webDriver);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);

        JSONObject hourly = (JSONObject) obj.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
        JSONArray rain = (JSONArray) hourly.get("rain");

        StringBuilder table = new StringBuilder();
        table.append(String.format("|%-4s| %-13s | %-11s | %-12s|%n", "№", "Дата/время", "Температура", "Осадки (мм)"));
        table.append(String.format("|%-4s| %-13s | %-11s | %-12s|%n", "--", "-------------", "-----------", "------------"));

        for (int i = 0; i < times.size(); ++i) {
            table.append(String.format("|%-4d| %-13s | %-11s | %-12s|%n",
                    i + 1,
                    times.get(i),
                    temperatures.get(i),
                    rain.get(i)));
        }

        System.out.println();
        System.out.println("Прогноз погоды на сутки (Нижний Новгород):");
        System.out.println(table);

        Path forecastFile = Paths.get("result", "forecast.txt");
        Files.write(forecastFile, table.toString().getBytes(StandardCharsets.UTF_8));
    }
}
