package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {
    public static void getIpAddress(WebDriver driver) {
        try {
            driver.get("https://api.ipify.org/?format=json");
            WebElement bodyElem = driver.findElement(By.tagName("body"));
            String jsonText = bodyElem.getText();
            
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonText);
            String ip = (String) json.get("ip");
            
            System.out.println("Ваш IP-адрес: " + ip);
        } catch (Exception e) {
            System.err.println("Ошибка получения IP: " + e.getMessage());
        }
    }
}