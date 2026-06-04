package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void getMyIp(String driverPath) {
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver webDriver = new ChromeDriver();
        
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement el = webDriver.findElement(By.tagName("pre"));
            
            String jsonStr = el.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            
            String ip = (String) obj.get("ip");
            System.out.println("IP-адрес: " + ip);
            
        } catch (Exception e) {
            System.out.println("Error in Task2: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}