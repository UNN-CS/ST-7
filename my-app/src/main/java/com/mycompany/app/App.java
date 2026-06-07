package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
  public static void main(String[] args) {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");

    WebDriver webDriver = new ChromeDriver(options);

    try {
      // Задание 1 - Password generator
      webDriver.get("https://www.calculator.net/password-generator.html");
      System.out.println("Задание 1: страница загружена - "
          + webDriver.getTitle());

      // Задание 2 - IP address
      Task2.getIP(webDriver);

      // Задание 3 - Weather forecast
      Task3.getForecast(webDriver);

    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
    } finally {
      webDriver.quit();
    }
  }
}
