package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static final String DRIVER = "/Users/madw3y/Downloads/chromedriver-mac-arm64/chromedriver";

    public static void main(String[] args) {

        System.setProperty(
                "webdriver.chrome.driver",
                DRIVER
        );

        WebDriver webDriver = new ChromeDriver();

        try {

            webDriver.get(
                    "https://www.calculator.net/password-generator.html"
            );

            WebElement lengthField = webDriver.findElement(By.id("resultid"));

            Thread.sleep(3000);

            WebElement resContainer = lengthField.findElement(By.className("verybigtext"));
            String password = resContainer.findElement(By.tagName("b")).getText();

            System.out.println("Generated password:");
            System.out.println(password);

            System.out.println();

            Task2.run();

            Task3.run();

        } catch (Exception e) {

            System.out.println("Error");
            System.out.println(e.toString());

        } finally {

            webDriver.quit();
        }
    }
}
