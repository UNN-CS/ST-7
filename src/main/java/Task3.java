import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Task3 {
    private static final String FORECAST_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
    private static final String FORECAST_FILE = "result/forecast.txt";

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get(FORECAST_URL);

        String jsonStr = Task2.getJsonText(webDriver);

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        JSONObject hourly = (JSONObject) obj.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
        JSONArray rain = (JSONArray) hourly.get("rain");

        String table = buildTable(times, temperatures, rain);
        System.out.println(table);
        saveToFile(table);
    }

    private static String buildTable(JSONArray times, JSONArray temperatures, JSONArray rain) {
        StringBuilder table = new StringBuilder();
        table.append(String.format("| %-3s | %-19s | %-11s | %-12s |%n", "№", "Дата/время", "Температура", "Осадки (мм)"));
        table.append(String.format("| %-3s | %-19s | %-11s | %-12s |%n", "--", "-------------------", "-----------", "------------"));

        for (int i = 0; i < times.size(); ++i) {
            table.append(String.format(
                    "| %-3d | %-19s | %-11s | %-12s |%n",
                    i + 1,
                    times.get(i),
                    temperatures.get(i),
                    rain.get(i)
            ));
        }

        return table.toString();
    }

    private static void saveToFile(String table) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FORECAST_FILE))) {
            writer.print(table);
        }
        System.out.println("Прогноз сохранён в файл " + FORECAST_FILE);
        System.out.println();
    }
}
