package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class Task2 {

    private Task2() {
    }

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get("https://api.ipify.org/?format=json");
        WebElement elem = webDriver.findElement(By.tagName("pre"));
        String jsonStr = elem.getText();
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        String ip = (String) obj.get("ip");
        System.out.println("IPv4 (ipify): " + ip);
    }
}
