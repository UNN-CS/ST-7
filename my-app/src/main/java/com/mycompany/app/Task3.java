package com.mycompany.app;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class Task3 {
    private static final String DRIVER_PATH_ENV = "CHROMEDRIVER_PATH";
    private static final String DEFAULT_DRIVER_PATH = "..\\drivers\\chromedriver.exe";
    private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    private Task3() {
    }

    public static void printAndSaveForecast() {
        String driverPath = System.getenv(DRIVER_PATH_ENV);
        if (driverPath == null || driverPath.isBlank()) {
            driverPath = DEFAULT_DRIVER_PATH;
        }

        SeleniumHelper.withDriver(driverPath, webDriver -> {
            webDriver.get(WEATHER_URL);
            try {
                String jsonText = SeleniumHelper.readJsonPayload(webDriver);
                JSONObject root = (JSONObject) new JSONParser().parse(jsonText);
                JSONObject hourly = (JSONObject) root.get("hourly");

                JSONArray times = (JSONArray) hourly.get("time");
                JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
                JSONArray rains = (JSONArray) hourly.get("rain");

                StringBuilder table = new StringBuilder();
                table.append(String.format("%-3s | %-16s | %-11s | %-11s%n",
                        "№", "Дата/время", "Температура", "Осадки (мм)"));
                table.append("-".repeat(51)).append(System.lineSeparator());

                int rows = Math.min(times.size(), Math.min(temperatures.size(), rains.size()));
                for (int i = 0; i < rows; i++) {
                    table.append(String.format("%-3d | %-16s | %-11s | %-11s%n",
                            i + 1,
                            String.valueOf(times.get(i)),
                            String.valueOf(temperatures.get(i)),
                            String.valueOf(rains.get(i))));
                }

                String result = table.toString();
                System.out.println("Task #3 forecast for Nizhny Novgorod:");
                System.out.println(result);
                writeForecastFile(result);
            } catch (Exception e) {
                System.out.println("Task #3 parse error: " + e);
            }
        });
    }

    private static void writeForecastFile(String content) throws IOException {
        Path resultDir = Path.of("..", "result");
        Files.createDirectories(resultDir);
        Path outputFile = resultDir.resolve("forecast.txt");

        Files.writeString(
                outputFile,
                content,
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
        System.out.println("Task #3 saved file: " + outputFile.toAbsolutePath().normalize());
    }
}
