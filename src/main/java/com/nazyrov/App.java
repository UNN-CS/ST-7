package com.nazyrov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver-win64\\chromedriver.exe");
        
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
        
        WebDriver driver = new ChromeDriver(options);
        
        try {
            System.out.println("=== ST-7 Selenium Tasks ===\n");
            
            // ??????? ?1 - ????????? ??????? (??????? ????)
            System.out.println("--- Task 1: Password Generator ---");
            // ?????????? ?????? ????-????????? ???????
            driver.get("https://www.random.org/passwords/?num=1&len=12&format=plain&rnd=new");
            Thread.sleep(2000);
            
            String password = driver.findElement(By.tagName("body")).getText().trim();
            System.out.println("Generated password: " + password);
            
            // ??????? ?2 - IP ?????
            System.out.println("\n--- Task 2: Get IP Address ---");
            driver.get("https://api.ipify.org/?format=json");
            Thread.sleep(1000);
            
            String pageSource = driver.getPageSource();
            int start = pageSource.indexOf("\"ip\":\"") + 6;
            int end = pageSource.indexOf("\"", start);
            String ipAddress = pageSource.substring(start, end);
            System.out.println("Your IP address: " + ipAddress);
            
            // ??????? ?3 - ??????? ??????
            System.out.println("\n--- Task 3: Weather Forecast for Nizhny Novgorod ---");
            driver.get("https://wttr.in/Nizhny+Novgorod?format=%t+%h+%w+%C");
            Thread.sleep(2000);
            
            String weatherText = driver.getPageSource();
            // ??????? ?? HTML ?????
            String cleanWeather = weatherText.replaceAll("\\<.*?\\>", "").trim();
            System.out.println("Weather info: " + cleanWeather);
            
            // ????????? ? ????
            java.nio.file.Files.createDirectories(java.nio.file.Paths.get("result"));
            java.nio.file.Files.write(
                java.nio.file.Paths.get("result/forecast.txt"),
                ("Weather in Nizhny Novgorod: " + cleanWeather).getBytes()
            );
            System.out.println("\n? Forecast saved to result/forecast.txt");
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            driver.quit();
        }
    }
}
