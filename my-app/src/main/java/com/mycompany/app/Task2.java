package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Task2 
{
    public static void main(String[] args) 
    {
        getIpAddress();
    }
    
    public static void getIpAddress() 
    {
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            
            Thread.sleep(2000);
            
            // Получаем JSON ответ из <pre> тега
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String json_str = elem.getText();
            
            // Парсим JSON
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(json_str);
            
            String ip = (String) obj.get("ip");
            
            System.out.println("=== ЗАДАНИЕ №2 ===");
            System.out.println("IP-адрес клиента: " + ip);
            System.out.println();
            
        } catch (Exception e) {
            System.out.println("Error in Task2");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}
