package org.example;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {

    public static void run(WebDriver driver) {

        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe%2FMoscow&forecast_days=1";

            driver.get(url);

            WebElement pre = driver.findElement(By.tagName("pre"));
            String jsonText = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonText);

            JSONObject hourly = (JSONObject) obj.get("hourly");

            JSONArray time = (JSONArray) hourly.get("time");
            JSONArray temp = (JSONArray) hourly.get("temperature_2m");
            JSONArray rain = (JSONArray) hourly.get("rain");

            System.out.println("\nTASK 3");
            System.out.println("№ | Date/Time | Temp | Rain");
            System.out.println("--------------------------------");

            for (int i = 0; i < time.size(); i++) {
                System.out.println(
                        (i + 1) + " | " +
                        time.get(i) + " | " +
                        temp.get(i) + " | " +
                        rain.get(i)
                );
            }

        } catch (Exception e) {
            System.out.println("Task3 error");
            System.out.println(e);
        }
    }
}
