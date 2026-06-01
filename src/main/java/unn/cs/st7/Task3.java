package unn.cs.st7;

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
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
            + "&hourly=temperature_2m,rain&current=cloud_cover"
            + "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(FORECAST_URL);
        String jsonStr = Task2.readJsonText(webDriver);
        JSONParser parser = new JSONParser();
        JSONObject root = (JSONObject) parser.parse(jsonStr);
        JSONObject hourly = (JSONObject) root.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
        JSONArray rain = (JSONArray) hourly.get("rain");

        String table = buildTable(times, temperatures, rain);
        System.out.println(table);

        Path output = Paths.get("result", "forecast.txt");
        Files.createDirectories(output.getParent());
        Files.write(output, table.getBytes(StandardCharsets.UTF_8));
        System.out.println("Таблица сохранена в " + output.toAbsolutePath());
    }

    private static String buildTable(JSONArray times, JSONArray temperatures, JSONArray rain) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-4s| %-19s | %-11s | %s%n",
                "№", "Дата/время", "Температура", "Осадки (мм)"));
        sb.append(String.format("%-4s| %-19s | %-11s | %s%n",
                "--", "-------------------", "-----------", "------------"));
        for (int i = 0; i < times.size(); i++) {
            sb.append(String.format("%-4d| %-19s | %-11s | %s%n",
                    i + 1,
                    times.get(i),
                    temperatures.get(i),
                    rain.get(i)));
        }
        return sb.toString();
    }
}
