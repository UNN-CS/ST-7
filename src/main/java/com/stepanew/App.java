package com.stepanew;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\stepanew\\IdeaProjects\\ST-7\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }

}
