package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;

public class Task3 {

    private static final String URL =
            "https://api.open-meteo.com/v1/forecast" +
            "?latitude=56" +
            "&longitude=44" +
            "&hourly=temperature_2m,rain" +
            "&current=cloud_cover" +
            "&timezone=Europe%2FMoscow" +
            "&forecast_days=1" +
            "&wind_speed_unit=ms";

    public static void run() {

        System.setProperty(
                "webdriver.chrome.driver",
                App.DRIVER
        );

        WebDriver webDriver = new ChromeDriver();

        try {

            String json = loadWeatherJson(webDriver);

            JSONObject hourly = parseHourly(json);

            String table = buildTable(hourly);

            printTable(table);

            saveToFile(table);

        } catch (Exception e) {

            System.out.println("Error");
            System.out.println(e.toString());

        } finally {

            webDriver.quit();
        }
    }

    private static String loadWeatherJson(WebDriver webDriver)
            throws Exception {

        webDriver.get(URL);

        WebElement elem =
                webDriver.findElement(By.tagName("pre"));

        return elem.getText();
    }

    private static JSONObject parseHourly(String json)
            throws Exception {

        JSONParser parser = new JSONParser();

        JSONObject obj =
                (JSONObject) parser.parse(json);

        return (JSONObject) obj.get("hourly");
    }

    private static String buildTable(JSONObject hourly) {

        JSONArray time =
                (JSONArray) hourly.get("time");

        JSONArray temperature =
                (JSONArray) hourly.get("temperature_2m");

        JSONArray rain =
                (JSONArray) hourly.get("rain");

        StringBuilder table = new StringBuilder();

        table.append(
                String.format(
                        "%-5s %-25s %-15s %-15s%n",
                        "№",
                        "Дата/время",
                        "Температура",
                        "Осадки"
                )
        );

        for (int i = 0; i < time.size(); i++) {

            table.append(
                    String.format(
                            "%-5d %-25s %-15s %-15s%n",
                            i + 1,
                            time.get(i),
                            temperature.get(i),
                            rain.get(i)
                    )
            );
        }

        return table.toString();
    }

    private static void printTable(String table) {

        System.out.println(table);
    }

    private static void saveToFile(String table)
            throws Exception {

        File dir = new File("result");

        dir.mkdirs();

        FileWriter writer =
                new FileWriter("result/forecast.txt");

        writer.write(table);

        writer.close();

        System.out.println(
                "Forecast saved to result/forecast.txt"
        );
    }
}
