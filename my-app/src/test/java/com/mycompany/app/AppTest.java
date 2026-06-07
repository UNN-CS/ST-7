package com.mycompany.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

  static WebDriver driver;

  @BeforeAll
  public static void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--headless");
    options.addArguments("--no-sandbox");
    options.addArguments("--disable-dev-shm-usage");
    driver = new ChromeDriver(options);
  }

  @AfterAll
  public static void tearDown() {
    if (driver != null) driver.quit();
  }

  @Test
  public void testGetIPNotNull() {
    String ip = Task2.getIP(driver);
    assertNotNull(ip);
  }

  @Test
  public void testGetIPFormat() {
    String ip = Task2.getIP(driver);
    assertNotNull(ip);
    assertTrue(ip.matches("\\d+\\.\\d+\\.\\d+\\.\\d+"));
  }

  @Test
  public void testPasswordGeneratorPageLoads() {
    driver.get("https://www.calculator.net/password-generator.html");
    assertNotNull(driver.getTitle());
    assertFalse(driver.getTitle().isEmpty());
  }

  @Test
  public void testWeatherForecastNoException() {
    assertDoesNotThrow(() -> Task3.getForecast(driver));
  }
}
