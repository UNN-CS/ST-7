package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {
    public static void getIpAddress(WebDriver webDriver) {
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            JSONObject obj = (JSONObject) new JSONParser()
                .parse(webDriver.findElement(By.tagName("pre")).getText());
            System.out.println(obj.get("ip"));
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
