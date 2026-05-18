package com.mycompany.app;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Task3 
{
    public static void main(String[] args) 
    {
        getWeatherForecast();
    }
    
    public static void getWeatherForecast() 
    {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        try {
            // Запрос с координатами Нижнего Новгорода (56, 44)
            String url = "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";
            webDriver.get(url);
            
            Thread.sleep(3000);
            
            // Получаем JSON ответ из <pre> тега
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String json_str = elem.getText();
            
            // Парсим JSON
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            
            // Получаем hourly данные
            JSONObject hourlyData = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourlyData.get("time");
            JSONArray temperatures = (JSONArray) hourlyData.get("temperature_2m");
            JSONArray rains = (JSONArray) hourlyData.get("rain");
            
            System.out.println("=== ЗАДАНИЕ №3 ===");
            System.out.println("Прогноз погоды для Нижнего Новгорода (координаты: 56, 44)\n");
            
            // Выводим таблицу с заголовком
            System.out.println(String.format("| %-3s | %-19s | %-11s | %-12s |", "№", "Дата/время", "Температура", "Осадки (мм)"));
            System.out.println("| --- | ------------------- | ----------- | ------------ |");
            
            // Собираем данные для записи в файл
            StringBuilder fileContent = new StringBuilder();
            fileContent.append(String.format("| %-3s | %-19s | %-11s | %-12s |\n", "№", "Дата/время", "Температура", "Осадки (мм)"));
            fileContent.append("| --- | ------------------- | ----------- | ------------ |\n");
            
            // Выводим данные
            for (int i = 0; i < times.size(); i++) {
                String time = (String) times.get(i);
                double temp = ((Number) temperatures.get(i)).doubleValue();
                double rain = ((Number) rains.get(i)).doubleValue();
                
                String tempStr = String.format("%.1f°C", temp);
                String rainStr = String.format("%.2f", rain);
                
                System.out.println(String.format("| %-3d | %-19s | %-11s | %-12s |", (i + 1), time, tempStr, rainStr));
                fileContent.append(String.format("| %-3d | %-19s | %-11s | %-12s |\n", (i + 1), time, tempStr, rainStr));
            }
            
            System.out.println();
            
            // Сохраняем в файл
            String outputPath = "result/forecast.txt";
            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(fileContent.toString());
                System.out.println("Таблица сохранена в файл: " + outputPath);
            } catch (IOException e) {
                System.out.println("Ошибка при записи файла: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Error in Task3");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}
