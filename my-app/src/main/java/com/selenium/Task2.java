package com.selenium;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    
    public static void runTask2() {
        try {
            WebDriver webDriver = new ChromeDriver();
            
            System.out.println("\nЗадание 2");
            System.out.println("Получение IP-адреса клиента");
            
            webDriver.get("https://api.ipify.org/?format=json");
            
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            
            String ipAddress = (String) obj.get("ip");
            
            System.out.println("IPv4-адрес: " + ipAddress);
            System.out.println("\n");
            
            webDriver.quit();
            
        } catch (Exception e) {
            System.out.println("Ошибка 2");
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }
}