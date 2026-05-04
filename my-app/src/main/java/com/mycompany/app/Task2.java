package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {

    public static void getMyIp(WebDriver webDriver) {
        try {
            System.out.println("=== Задание №2 ===");

            webDriver.get("https://api.ipify.org/?format=json");

            WebElement preElement = webDriver.findElement(By.tagName("pre"));
            String jsonString = preElement.getText();

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

            String ipAddress = (String) jsonObject.get("ip");

            System.out.println("Ваш IP-адрес: " + ipAddress);
            System.out.println();

        } catch (Exception e) {
            System.out.println("Ошибка при получении IP: " + e.toString());
        }
    }
}