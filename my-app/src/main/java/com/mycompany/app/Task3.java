package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Task3 {
  public static void getForecast(WebDriver webDriver) {
    try {
      String url = "https://api.open-meteo.com/v1/forecast?"
          + "latitude=56&longitude=44"
          + "&hourly=temperature_2m,rain"
          + "&current=cloud_cover"
          + "&timezone=Europe%2FMoscow"
          + "&forecast_days=1"
          + "&wind_speed_unit=ms";

      webDriver.get(url);
      WebElement elem = webDriver.findElement(By.tagName("pre"));
      String json_str = elem.getText();

      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(json_str);
      JSONObject hourly = (JSONObject) obj.get("hourly");
      JSONArray times = (JSONArray) hourly.get("time");
      JSONArray temps = (JSONArray) hourly.get("temperature_2m");
      JSONArray rains = (JSONArray) hourly.get("rain");

      StringBuilder sb = new StringBuilder();
      sb.append(String.format("%-5s %-22s %-15s %-15s%n",
          "№", "Дата/время", "Температура", "Осадки (мм)"));
      sb.append("-".repeat(60)).append("\n");

      for (int i = 0; i < times.size(); i++) {
        sb.append(String.format("%-5d %-22s %-15s %-15s%n",
            i + 1, times.get(i), temps.get(i), rains.get(i)));
      }

      System.out.println(sb.toString());

      try (PrintWriter pw = new PrintWriter(new FileWriter("result/forecast.txt"))) {
        pw.print(sb.toString());
      }
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
    }
  }
}
