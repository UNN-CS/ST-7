package edu.unn.cs.st7;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Task3 {

    private static final String FORECAST_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    private Task3() {
    }

    public static void run(WebDriver webDriver) {
        try {
            webDriver.get(FORECAST_URL);

            String jsonStr = JsonPage.getJsonText(webDriver);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");

            String table = buildTable(times, temperatures, rain);
            System.out.println(table);

            Path outputPath = Paths.get("result", "forecast.txt");
            Files.createDirectories(outputPath.getParent());
            Files.write(outputPath, table.getBytes(StandardCharsets.UTF_8));

            System.out.println("Прогноз сохранён в " + outputPath.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }

    static String buildTable(JSONArray times, JSONArray temperatures, JSONArray rain) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("| %-3s | %-13s | %-11s | %-12s |%n", "№", "Дата/время", "Температура", "Осадки (мм)"));
        sb.append(String.format("| %-3s | %-13s | %-11s | %-12s |%n", "--", "-------------", "-----------", "------------"));

        int count = Math.min(times.size(), Math.min(temperatures.size(), rain.size()));
        for (int i = 0; i < count; i++) {
            sb.append(String.format("| %-3d | %-13s | %-11s | %-12s |%n",
                    i + 1,
                    times.get(i),
                    temperatures.get(i),
                    rain.get(i)));
        }
        return sb.toString();
    }
}
