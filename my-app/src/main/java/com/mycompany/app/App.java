package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
  public static void main(String[] args) {
    System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");

    WebDriver webDriver = new ChromeDriver();

    System.out.println("=== Задание №1 (Генерация пароля) ===");
    try {
      webDriver.get("https://www.calculator.net/password-generator.html");

      WebElement passElem = webDriver.findElement(By.className("verybigtext"));
      String password = passElem.getText();
      System.out.println("Сгенерированный пароль: " + password);
    } catch (Exception e) {
      System.out.println("Error in Task 1");
      System.out.println(e.toString());
    }

    System.out.println("\n=== Задание №2 (IP Адрес) ===");
    Task2.run(webDriver);

    System.out.println("\n=== Задание №3 (Прогноз погоды) ===");
    Task3.run(webDriver);

    webDriver.quit();
  }
}
