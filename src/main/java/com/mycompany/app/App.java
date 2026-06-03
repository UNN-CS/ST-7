package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    private static final String PASSWORD_URL =
        "https://www.calculator.net/password-generator.html";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver",
            "C:\\WebDrivers\\chromedriver.exe");

        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get(PASSWORD_URL);
            String password = getGeneratedPassword(webDriver);
            System.out.println("Generated password: " + password);

            String ip = Task2.getClientIp(webDriver);
            System.out.println("Client IP: " + ip);

            String forecastTable = Task3.getForecastTable(webDriver);
            System.out.println(forecastTable);
            Task3.saveForecastTable(forecastTable);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }

    static String getGeneratedPassword(WebDriver webDriver) {
        WebElement passwordInput;
        try {
            passwordInput = webDriver.findElement(By.id("resultid"));
        } catch (Exception ignored) {
            passwordInput = webDriver.findElement(By.name("result"));
        }
        String password = passwordInput.getAttribute("value");
        if (password == null || password.length() == 0) {
            password = passwordInput.getText();
        }
        return password;
    }
}
