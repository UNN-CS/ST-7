package org.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

public final class Task3 {
    private static final String FORECAST_URL =
        "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain"
            + "&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    private Task3() {
    }

    public static void run() {
        WebDriver webDriver = null;
        try {
            webDriver = App.createWebDriver();
            webDriver.get(FORECAST_URL);
            String jsonText = Task2.extractJsonText(webDriver);
            String table = buildForecastTable(jsonText);
            Path outputPath = Path.of("result", "forecast.txt");
            writeForecastToFile(outputPath, table);
            System.out.println("Task 3 - forecast saved to: " + outputPath);
            System.out.println(table);
        } catch (Exception e) {
            System.out.println("Task 3 Error");
            System.out.println(e.toString());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    static String buildForecastTable(String jsonText) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonText);
        JSONObject hourly = (JSONObject) root.get("hourly");

        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
        JSONArray rains = (JSONArray) hourly.get("rain");

        int rowCount = Math.min(times.size(), Math.min(temperatures.size(), rains.size()));
        StringBuilder table = new StringBuilder();
        table.append(String.format("%-3s | %-16s | %-12s | %-11s%n", "№", "Дата/время", "Температура", "Осадки (мм)"));
        table.append("----+------------------+--------------+------------\n");

        for (int i = 0; i < rowCount; i++) {
            table.append(
                String.format(
                    "%-3d | %-16s | %-12s | %-11s%n",
                    i + 1,
                    String.valueOf(times.get(i)),
                    String.valueOf(temperatures.get(i)),
                    String.valueOf(rains.get(i))
                )
            );
        }
        return table.toString();
    }

    private static void writeForecastToFile(Path outputPath, String table) throws IOException {
        Path parent = outputPath.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.writeString(outputPath, table, StandardCharsets.UTF_8);
    }
}
