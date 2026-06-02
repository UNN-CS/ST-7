package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Task3 {
    public static void generateWeatherForecast() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nyuti\\repos\\ST-7\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast" +
                    "?latitude=56&longitude=44" +
                    "&hourly=temperature_2m,rain" +
                    "&current=cloud_cover" +
                    "&timezone=Europe%2FMoscow" +
                    "&forecast_days=1" +
                    "&wind_speed_unit=ms";
            webDriver.get(url);

            WebElement pre = webDriver.findElement(By.tagName("pre"));
            String jsonStr = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);

            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temperatures = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-4s %-20s %-12s %-10s%n",
                    "№", "Дата/время", "Температура", "Осадки (мм)"));
            sb.append("------------------------------------------------\n");

            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                double temp = (double) temperatures.get(i);   
                double rain = (double) rains.get(i);
                sb.append(String.format("%-4d %-20s %-12.1f %-10.2f%n",
                        i + 1, time, temp, rain));
            }

            System.out.println("Задание №3");
            System.out.print(sb.toString());

            try (PrintWriter pw = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                pw.print(sb.toString());
            } catch (IOException e) {
                System.err.println("Ошибка записи файла: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("Ошибка при выполении задания №3");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}