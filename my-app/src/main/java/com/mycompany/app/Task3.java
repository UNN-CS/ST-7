package com.mycompany.app;

import java.io.File;
import java.io.PrintWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {
  public static void run(WebDriver webDriver) {
    try {
      String url = "https://api.open-meteo.com/v1/"
          + "forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_"
          + "cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
      webDriver.get(url);

      WebElement elem = webDriver.findElement(By.tagName("pre"));
      String json_str = elem.getText();

      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(json_str);
      JSONObject hourly = (JSONObject) obj.get("hourly");

      JSONArray time = (JSONArray) hourly.get("time");
      JSONArray temperature = (JSONArray) hourly.get("temperature_2m");
      JSONArray rain = (JSONArray) hourly.get("rain");

      StringBuilder table = new StringBuilder();
      table.append(String.format(
          "| %-2s | %-16s | %-11s | %-11s |\n", "№", "Дата/время", "Температура", "Осадки (мм)"));
      table.append("|----|------------------|-------------|-------------|\n");

      for (int i = 0; i < time.size(); i++) {
        String dateTime = time.get(i).toString().replace("T", " ");
        String temp = temperature.get(i).toString();
        String rainVal = rain.get(i).toString();

        table.append(
            String.format("| %-2d | %-16s | %-11s | %-11s |\n", (i + 1), dateTime, temp, rainVal));
      }

      System.out.println(table.toString());

      try (PrintWriter writer = new PrintWriter("../result/forecast.txt")) {
        writer.print(table.toString());
      }
      System.out.println("\nФайл с прогнозом сохранен: result/forecast.txt\n");

    } catch (Exception e) {
      System.out.println("Error in Task 3");
      System.out.println(e.toString());
    }
  }
}
