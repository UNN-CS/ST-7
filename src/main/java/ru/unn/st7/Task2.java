package ru.unn.st7;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static String fetchPublicIp(WebDriver webDriver) throws Exception {
        webDriver.get("https://api.ipify.org/?format=json");
        WebElement preTag = webDriver.findElement(By.tagName("pre"));
        String jsonData = preTag.getText();

        JSONParser parser = new JSONParser();
        JSONObject parsedObject = (JSONObject) parser.parse(jsonData);
        Object ipAddress = parsedObject.get("ip");
        return ipAddress == null ? "" : ipAddress.toString();
    }
}