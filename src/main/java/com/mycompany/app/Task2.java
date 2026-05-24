package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get("https://api.ipify.org/?format=json");

        String jsonStr = readJsonFromPage(webDriver);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        String ip = (String) obj.get("ip");

        System.out.println("IPv4-адрес клиента: " + ip);
    }

    static String readJsonFromPage(WebDriver webDriver) {
        try {
            WebElement pre = webDriver.findElement(By.tagName("pre"));
            return pre.getText();
        } catch (Exception ignored) {
            return webDriver.findElement(By.tagName("body")).getText();
        }
    }
}
