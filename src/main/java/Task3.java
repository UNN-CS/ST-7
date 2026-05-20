import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {
    private static final String FORECAST_URL =
            "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain"
                    + "&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow"
                    + "&forecast_days=1"
                    + "&wind_speed_unit=ms";

    public static String getForecastTable(WebDriver webDriver) throws Exception {
        webDriver.get(FORECAST_URL);

        String json = readJsonFromBrowser(webDriver);
        JSONObject root = (JSONObject) new JSONParser().parse(json);
        JSONObject hourly = (JSONObject) root.get("hourly");

        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
        JSONArray rains = (JSONArray) hourly.get("rain");

        return buildForecastTable(times, temperatures, rains);
    }

    public static void writeForecastTable(String forecastTable, Path outputFile) throws IOException {
        Path parent = outputFile.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        Files.write(outputFile, forecastTable.getBytes(StandardCharsets.UTF_8));
    }

    private static String readJsonFromBrowser(WebDriver webDriver) {
        WebElement pre = webDriver.findElement(By.tagName("pre"));
        return pre.getText();
    }

    private static String buildForecastTable(JSONArray times, JSONArray temperatures, JSONArray rains) {
        StringBuilder table = new StringBuilder();
        table.append("| № | Дата/время | Температура | Осадки (мм) |").append(System.lineSeparator());
        table.append("| -- | ------------- | ----------- | ------------ |").append(System.lineSeparator());

        for (int i = 0; i < times.size(); i++) {
            String time = (String) times.get(i);
            double temperature = ((Number) temperatures.get(i)).doubleValue();
            double rain = ((Number) rains.get(i)).doubleValue();

            table.append("| ")
                    .append(i + 1)
                    .append(" | ")
                    .append(time)
                    .append(" | ")
                    .append(String.format(Locale.US, "%.1f", temperature))
                    .append(" | ")
                    .append(String.format(Locale.US, "%.2f", rain))
                    .append(" |")
                    .append(System.lineSeparator());
        }

        return table.toString();
    }
}
