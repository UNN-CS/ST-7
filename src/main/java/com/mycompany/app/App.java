package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App 
{
    public static void main(String[] args)
    {
        System.setProperty(
            "webdriver.chrome.driver",
            "/snap/chromium/3390/usr/lib/chromium-browser/chromedriver"
        );

        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get("https://www.calculator.net/password-generator.html");

            Thread.sleep(2000);

            WebElement passwordElement =
                webDriver.findElement(By.cssSelector(".verybigtext b"));

            String generatedPassword = passwordElement.getText();

            System.out.println("Password: " + generatedPassword);
            System.out.println();

            Task2.getIpAddress(webDriver);
            Task3.getWeatherForecast(webDriver);

            Thread.sleep(3000);

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());

        } finally {
            webDriver.quit();
        }
    }
}