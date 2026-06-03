package ru.unn.st7;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static void getMyPublicIp(WebDriver driver) throws Exception {
        driver.get("https://api.ipify.org/?format=json");

        WebElement preElement = driver.findElement(By.tagName("pre"));
        String jsonResponse = preElement.getText();

        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(jsonResponse);
        String ipAddress = (String) jsonObject.get("ip");
        
        System.out.println("Задание 2. Ваш публичный IPv4-адрес: " + ipAddress);
    }
}
