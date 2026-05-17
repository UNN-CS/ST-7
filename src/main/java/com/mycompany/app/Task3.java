package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    public static void execute() {
        System.setProperty("webdriver.chrome.driver",
                "/home/user/Загрузки/chromedriver-linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/home/user/Загрузки/chrome-linux64/chrome");

        WebDriver driver = new ChromeDriver(options);
        try {
            String url = "https://api.open-meteo.com/v1/forecast"
                    + "?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain"
                    + "&current=cloud_cover"
                    + "&timezone=Europe%2FMoscow"
                    + "&forecast_days=1"
                    + "&wind_speed_unit=ms";
            driver.get(url);

            WebElement pre = driver.findElement(By.tagName("pre"));
            String jsonStr = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) root.get("hourly");

            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder sb = new StringBuilder();
            sb.append("№\tДата/время\t\tТемпература\tОсадки (мм)\n");

            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                double temp = ((Number) temps.get(i)).doubleValue();
                double rain = ((Number) rains.get(i)).doubleValue();
                sb.append(String.format("%d\t%s\t%.1f°C\t\t%.2f%n", i + 1, time, temp, rain));
            }

            System.out.println("=== Задание 3 ===");
            System.out.println(sb.toString());

            File resultDir = new File("result");
            if (!resultDir.exists()) {
                resultDir.mkdir();
            }
            try (FileWriter fw = new FileWriter("result/forecast.txt")) {
                fw.write(sb.toString());
                System.out.println("Таблица сохранена в result/forecast.txt");
            } catch (IOException e) {
                System.out.println("Ошибка записи файла: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Ошибка в задании 3: " + e.toString());
        } finally {
            driver.quit();
        }
    }
}
