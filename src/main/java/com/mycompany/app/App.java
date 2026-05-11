package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) {
        System.setProperty(
            "webdriver.chrome.driver",
            "chromedriver-win64/chromedriver-win64/chromedriver.exe"
        );
        
        WebDriver webDriver = new ChromeDriver();

        try {
            System.out.println("Task 1");
            webDriver.get("https://www.calculator.net/password-generator.html");
            
            WebElement passwordElement = webDriver.findElement(By.xpath("//input[@type='text']"));
            String generatedPassword = passwordElement.getAttribute("value");
            
            System.out.println("PASSWORD: " + generatedPassword);
            System.out.println();

            System.out.println("Task 2");
            Task2.execute(webDriver);
            System.out.println();

            System.out.println("Task 3");
            Task3.execute(webDriver);

        } catch (Exception e) {
            System.out.println("Error: Task 1");
            System.out.println(e.toString());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }
}