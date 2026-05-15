package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task3 
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            
            webDriver.get(url);
            
            WebElement preElement = webDriver.findElement(By.tagName("pre"));
            String jsonText = preElement.getText();
            
            String[] times = extractJsonArray(jsonText, "time");
            String[] temps = extractJsonArray(jsonText, "temperature_2m");
            String[] rains = extractJsonArray(jsonText, "rain");
            
            System.out.println("№\tДата/время\t\tТемпература\tОсадки (мм)");
            System.out.println("--------------------------------------------------------");
            
            for (int i = 0; i < times.length; i++) {
                System.out.println((i + 1) + "\t" + times[i] + "\t" + temps[i] + "°C\t\t" + rains[i] + " mm");
            }
            
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
    
    private static String[] extractJsonArray(String json, String key) {
        String searchKey = "\"" + key + "\":[";
        int startIndex = json.indexOf(searchKey) + searchKey.length();
        int endIndex = json.indexOf("]", startIndex);
        String arrayStr = json.substring(startIndex, endIndex);
        String[] items = arrayStr.split(",");
        for (int i = 0; i < items.length; i++) {
            items[i] = items[i].replace("\"", "").trim();
        }
        return items;
    }
}