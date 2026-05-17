package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Task2 {
    public static void execute() {
        System.setProperty("webdriver.chrome.driver",
                "/home/user/Загрузки/chromedriver-linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/home/user/Загрузки/chrome-linux64/chrome");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get("https://api.ipify.org/?format=json");

            WebElement pre = driver.findElement(By.tagName("pre"));
            String jsonStr = pre.getText();

            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);
            String ip = (String) obj.get("ip");

            System.out.println("=== Задание 2 ===");
            System.out.println("Ваш IP-адрес: " + ip);
        } catch (Exception e) {
            System.out.println("Ошибка в задании 2: " + e.toString());
        } finally {
            driver.quit();
        }
    }
}
