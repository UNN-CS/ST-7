package ru.unn.st7;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Task3 {
    private static final String WEATHER_API = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void exportWeatherForecast(WebDriver webDriver) throws Exception {
        webDriver.get(WEATHER_API);
        WebElement preElement = webDriver.findElement(By.tagName("pre"));
        String jsonContent = preElement.getText();

        JSONParser jsonParser = new JSONParser();
        JSONObject parsedRoot = (JSONObject) jsonParser.parse(jsonContent);
        JSONObject hourlyData = (JSONObject) parsedRoot.get("hourly");
        JSONArray timeList = (JSONArray) hourlyData.get("time");
        JSONArray tempList = (JSONArray) hourlyData.get("temperature_2m");
        JSONArray rainList = (JSONArray) hourlyData.get("rain");

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder.append("|№   |  Дата/время   | Температура | Осадки (мм)  |").append(System.lineSeparator());
        tableBuilder.append("| -- | ------------- | ----------- | ------------ |").append(System.lineSeparator());

        int recordCount = Math.min(timeList.size(), Math.min(tempList.size(), rainList.size()));
        for (int idx = 0; idx < recordCount; idx++) {
            tableBuilder.append("|")
                    .append(idx + 1)
                    .append(" | ")
                    .append(String.valueOf(timeList.get(idx)))
                    .append(" | ")
                    .append(String.valueOf(tempList.get(idx)))
                    .append(" | ")
                    .append(String.valueOf(rainList.get(idx)))
                    .append(" |")
                    .append(System.lineSeparator());
        }

        System.out.print(tableBuilder);

        Path outputFile = Path.of("result", "forecast.txt");
        Files.createDirectories(outputFile.getParent());
        Files.writeString(outputFile, tableBuilder.toString(), StandardCharsets.UTF_8);
    }
}
