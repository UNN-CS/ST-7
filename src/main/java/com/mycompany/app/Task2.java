package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    private static final String IP_URL = "https://api.ipify.org/?format=json";

    private Task2() {
    }

    public static String getClientIp(WebDriver webDriver) {
        try {
            webDriver.get(IP_URL);
            String json = getPageText(webDriver);
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(json);
            String ip = (String) object.get("ip");
            System.out.println(ip);
            return ip;
        } catch (Exception e) {
            throw new IllegalStateException("Cannot read client IP", e);
        }
    }

    static String getPageText(WebDriver webDriver) {
        try {
            WebElement pre = webDriver.findElement(By.tagName("pre"));
            return pre.getText();
        } catch (Exception ignored) {
            WebElement body = webDriver.findElement(By.tagName("body"));
            return body.getText();
        }
    }
}
