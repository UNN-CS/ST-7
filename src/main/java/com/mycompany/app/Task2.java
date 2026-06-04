package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {

    public String fetchIp() {
        WebDriver driver = App.createDriver();
        try {
            driver.get("https://api.ipify.org/?format=json");
            WebElement pre = driver.findElement(By.tagName("pre"));
            JSONObject json = (JSONObject) new JSONParser().parse(pre.getText());
            return (String) json.get("ip");
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return "";
        } finally {
            driver.quit();
        }
    }
}