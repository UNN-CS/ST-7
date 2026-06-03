package ru.unn.st7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {
    private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=56"
                    + "&longitude=44"
                    + "&hourly=temperature_2m,rain"
                    + "&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow"
                    + "&forecast_days=1"
                    + "&wind_speed_unit=ms";

    private static final Path FORECAST_FILE = Paths.get("result", "forecast.txt");

    public static void printForecast() {
        String forecast = loadForecastTable();
        System.out.print(forecast);
        saveForecast(forecast);
    }

    public static String loadForecastTable() {
        WebDriver driver = App.createChromeDriver();
        try {
            driver.get(WEATHER_URL);

            WebElement jsonBlock = driver.findElement(By.tagName("pre"));
            JSONObject root = (JSONObject) new JSONParser().parse(jsonBlock.getText());
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            table.append("| № | Дата/время | Температура | Осадки (мм) |\n");
            table.append("|---|------------|-------------|-------------|\n");

            for (int i = 0; i < times.size(); i++) {
                String time = String.valueOf(times.get(i));
                double temperature = ((Number) temperatures.get(i)).doubleValue();
                double rain = ((Number) rains.get(i)).doubleValue();

                table.append(String.format(
                        Locale.US,
                        "| %2d | %s | %10.1f | %11.2f |\n",
                        i + 1,
                        time,
                        temperature,
                        rain));
            }

            return table.toString();
        } catch (Exception exception) {
            return "Error: " + exception + System.lineSeparator();
        } finally {
            driver.quit();
        }
    }

    private static void saveForecast(String forecast) {
        try {
            Files.createDirectories(FORECAST_FILE.getParent());
            Files.write(FORECAST_FILE, forecast.getBytes(StandardCharsets.UTF_8));
        } catch (IOException exception) {
            System.out.println("Cannot write forecast file: " + exception);
        }
    }
}
