package org.example;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {

    public static void run(WebDriver driver) {

        try {
            driver.get("https://api.ipify.org/?format=json");

            WebElement pre = driver.findElement(By.tagName("pre"));
            String jsonText = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonText);

            String ip = (String) obj.get("ip");

            System.out.println("\nTASK 2");
            System.out.println("IP: " + ip);

        } catch (Exception e) {
            System.out.println("Task2 error");
            System.out.println(e);
        }
    }
}
