package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    
    public static void getIpAddress(WebDriver webDriver) {
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            Thread.sleep(1000);
            WebElement preElement = webDriver.findElement(By.tagName("pre"));
            String jsonText = preElement.getText();
            String ip = jsonText.split("\"ip\":\"")[1].split("\"")[0];
            System.out.println("Your IP address: " + ip);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Error in Task2");
            System.out.println(e.toString());
        }
    }
}