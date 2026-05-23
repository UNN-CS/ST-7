package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Paths;

public class Task3 {
    
    public static void getWeatherForecast(WebDriver webDriver) {
        try {
            String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(weatherUrl);
            Thread.sleep(2000);
            
            WebElement preWeather = webDriver.findElement(By.tagName("pre"));
            String weatherJson = preWeather.getText();
            
            java.io.File resultDir = new java.io.File("result");
            if (!resultDir.exists()) {
                resultDir.mkdir();
            }
            
            PrintWriter writer = new PrintWriter(new FileWriter(Paths.get("result", "forecast.txt").toString()));
            
            String header = String.format("%-3s %-20s %-12s %s", "No", "Date/Time", "Temperature", "Rain (mm)");
            System.out.println(header);
            writer.println(header);
            
            String timePart = weatherJson.split("\"time\":\\[")[1].split("\\]")[0];
            String tempPart = weatherJson.split("\"temperature_2m\":\\[")[1].split("\\]")[0];
            String rainPart = weatherJson.split("\"rain\":\\[")[1].split("\\]")[0];
            
            String[] times = timePart.split(",");
            String[] temps = tempPart.split(",");
            String[] rains = rainPart.split(",");
            
            for (int i = 0; i < times.length; i++) {
                String time = times[i].replace("\"", "");
                String temp = temps[i];
                String rain = rains[i];
                
                String row = String.format("%-3s %-20s %-12s %s", 
                    (i+1), 
                    time, 
                    temp + "°C", 
                    String.format("%.2f", Double.parseDouble(rain)));
                
                System.out.println(row);
                writer.println(row);
            }
            
            writer.close();
            System.out.println();
            System.out.println("Table saved to: result/forecast.txt");
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error in Task3");
            System.out.println(e.toString());
        }
    }
}