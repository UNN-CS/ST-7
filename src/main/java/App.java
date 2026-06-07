package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {

    public static void main(String[] args) {

        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get("https://www.calculator.net/password-generator.html");

            System.out.println("Task 1 opened");

            Task2.run(webDriver);
            Task3.run(webDriver);

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        } finally {
            webDriver.quit();
        }
    }
}
