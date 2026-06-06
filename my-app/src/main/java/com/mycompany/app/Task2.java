package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static String getIpAddress(WebDriver webDriver) {
        String ipAddress = "Unknown";
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            System.out.println("Page loaded: " + webDriver.getCurrentUrl());

            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            System.out.println("Raw JSON: " + jsonStr);

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            ipAddress = (String) obj.get("ip");

            System.out.println("Your IP address: " + ipAddress);
        } catch (Exception e) {
            System.out.println("Error in Task2:");
            System.out.println(e.toString());
        }
        return ipAddress;
    }
}
