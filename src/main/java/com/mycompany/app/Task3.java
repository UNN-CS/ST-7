package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Task3 {

    private static final String FORECAST_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow"
                    + "&forecast_days=1&wind_speed_unit=ms";

    private Task3() {
    }

    @SuppressWarnings("unchecked")
    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(FORECAST_URL);
        WebElement elem = webDriver.findElement(By.tagName("pre"));
        String jsonStr = elem.getText();
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonStr);

        JSONObject current = (JSONObject) root.get("current");
        Object cloud = current != null ? current.get("cloud_cover") : null;

        JSONObject hourly = (JSONObject) root.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temps = (JSONArray) hourly.get("temperature_2m");
        JSONArray rains = (JSONArray) hourly.get("rain");

        StringBuilder out = new StringBuilder();
        if (cloud != null) {
            out.append("Current cloud cover: ").append(cloud).append("%\n\n");
        }

        out.append("| \u2116 | \u0414\u0430\u0442\u0430/\u0432\u0440\u0435\u043c\u044f | "
                + "\u0422\u0435\u043c\u043f\u0435\u0440\u0430\u0442\u0443\u0440\u0430 | "
                + "\u041e\u0441\u0430\u0434\u043a\u0438 (\u043c\u043c) |\n");
        out.append("| --- | ---------- | ----------- | ----------- |\n");

        for (int i = 0; i < times.size(); i++) {
            String time = String.valueOf(times.get(i));
            String temp = String.valueOf(temps.get(i));
            String rain = String.valueOf(rains.get(i));
            out.append("| ").append(i + 1).append(" | ").append(time).append(" | ")
                    .append(temp).append(" | ").append(rain).append(" |\n");
        }

        String table = out.toString();
        System.out.println(table);

        Path dir = Path.of("result");
        Files.createDirectories(dir);
        Files.writeString(dir.resolve("forecast.txt"), table, StandardCharsets.UTF_8);
    }
}
