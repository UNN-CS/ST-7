package com.mycompany.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Task2 
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://api.ipify.org/?format=json");
            
            WebElement preElement = webDriver.findElement(By.tagName("pre"));
            String json = preElement.getText();
            
            String ip = json.replace("{\"ip\":\"", "").replace("\"}", "");
            
            System.out.println(ip);
            
            Thread.sleep(3000);
            
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}