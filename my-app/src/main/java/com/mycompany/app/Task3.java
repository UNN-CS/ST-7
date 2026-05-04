package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.PrintWriter;

public class Task3 {

    public static void getWeatherForecast() {
        try {
            System.out.println("=== Задание №3 ===");

            String urlString = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe/Moscow&forecast_days=1";

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(response.toString());

            JSONObject hourly = (JSONObject) jsonObject.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            System.out.println("┌────┬─────────────────────┬─────────────┬─────────────┐");
            System.out.println("│ №  │ Дата/время          │ Температура │ Осадки (мм) │");
            System.out.println("├────┼─────────────────────┼─────────────┼─────────────┤");

            try (PrintWriter fileWriter = new PrintWriter(new FileWriter("C:/ST-7/ST-7/result/forecast.txt"))) {
                fileWriter.println("Прогноз погоды для Нижнего Новгорода (координаты: 56, 44)");
                fileWriter.println("┌────┬─────────────────────┬─────────────┬─────────────┐");
                fileWriter.println("│ №  │ Дата/время          │ Температура │ Осадки (мм) │");
                fileWriter.println("├────┼─────────────────────┼─────────────┼─────────────┤");

                for (int i = 0; i < times.size(); i++) {
                    String time = (String) times.get(i);
                    double temp = (double) temperatures.get(i);
                    double rain = (double) rains.get(i);

                    System.out.printf("│ %2d │ %-19s │ %3.1f°C      │ %5.2f       │\n", (i + 1), time, temp, rain);
                    fileWriter.printf("│ %2d │ %-19s │ %3.1f°C      │ %5.2f       │\n", (i + 1), time, temp, rain);
                }

                System.out.println("└────┴─────────────────────┴─────────────┴─────────────┘");
                fileWriter.println("└────┴─────────────────────┴─────────────┴─────────────┘");
            }

            System.out.println("\nТаблица сохранена в файл: result/forecast.txt");

        } catch (Exception e) {
            System.out.println("Ошибка при получении погоды: " + e.toString());
        }
    }
}