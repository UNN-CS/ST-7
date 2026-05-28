package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {
    
    public static void getPublicIp(WebDriver webDriver) {
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            String ipAddress = (String) obj.get("ip");
            
            System.out.println("Ваш публичный IPv4 адрес: " + ipAddress);
            
        } catch (Exception e) {
            System.out.println("Ошибка при получении IP адреса");
            System.out.println(e.toString());
        }
    }
}