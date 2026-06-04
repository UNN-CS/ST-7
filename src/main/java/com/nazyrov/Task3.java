package com.nazyrov;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Task3 {
    
    public static void parseAndSaveForecast(String json) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(json);
            
            // ???????? ??????? ???????
            JSONArray currentCondition = (JSONArray) root.get("current_condition");
            JSONObject current = (JSONObject) currentCondition.get(0);
            
            String temp = (String) current.get("temp_C");
            String humidity = (String) current.get("humidity");
            String windSpeed = (String) current.get("windspeedKmph");
            
            // ???????? ???????? ??????
            JSONArray weatherDesc = (JSONArray) current.get("weatherDesc");
            JSONObject desc = (JSONObject) weatherDesc.get(0);
            String description = (String) desc.get("value");
            
            System.out.println("Current weather in Nizhny Novgorod:");
            System.out.println("Temperature: " + temp + "?C");
            System.out.println("Humidity: " + humidity + "%");
            System.out.println("Wind speed: " + windSpeed + " km/h");
            System.out.println("Conditions: " + description);
            
            // ???????? ??????? ?? ???????
            JSONArray weather = (JSONArray) root.get("weather");
            JSONObject today = (JSONObject) weather.get(0);
            String maxTemp = (String) today.get("maxtempC");
            String minTemp = (String) today.get("mintempC");
            
            System.out.println("\nForecast for today:");
            System.out.println("Max temperature: " + maxTemp + "?C");
            System.out.println("Min temperature: " + minTemp + "?C");
            
            // ????????? ? ????
            StringBuilder fileContent = new StringBuilder();
            fileContent.append("Weather Forecast for Nizhny Novgorod\n");
            fileContent.append("==================================\n\n");
            fileContent.append("Current weather:\n");
            fileContent.append("  Temperature: ").append(temp).append("?C\n");
            fileContent.append("  Humidity: ").append(humidity).append("%\n");
            fileContent.append("  Wind speed: ").append(windSpeed).append(" km/h\n");
            fileContent.append("  Conditions: ").append(description).append("\n\n");
            fileContent.append("Today's forecast:\n");
            fileContent.append("  Max: ").append(maxTemp).append("?C\n");
            fileContent.append("  Min: ").append(minTemp).append("?C\n");
            
            Files.createDirectories(Paths.get("result"));
            Files.write(Paths.get("result/forecast.txt"), fileContent.toString().getBytes());
            System.out.println("\n? Forecast saved to result/forecast.txt");
            
        } catch (Exception e) {
            System.err.println("Parse error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
