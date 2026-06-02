package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Task2 {
    public static void printMyIP() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\nyuti\\repos\\ST-7\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            WebElement pre = webDriver.findElement(By.tagName("pre"));
            String jsonStr = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonStr);
            String ip = (String) json.get("ip");

            System.out.println("Задание №2");
            System.out.println("IP-адрес: " + ip);
            System.out.println();
        } catch (Exception e) {
            System.out.println("Ошибка при выполнении задания №2");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}