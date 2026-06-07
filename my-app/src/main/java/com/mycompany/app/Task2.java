package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Task2 {
  public static String getIP(WebDriver webDriver) {
    try {
      webDriver.get("https://api.ipify.org/?format=json");
      WebElement elem = webDriver.findElement(By.tagName("pre"));
      String json_str = elem.getText();
      JSONParser parser = new JSONParser();
      JSONObject obj = (JSONObject) parser.parse(json_str);
      String ip = (String) obj.get("ip");
      System.out.println("IP-адрес клиента: " + ip);
      return ip;
    } catch (Exception e) {
      System.out.println("Error: " + e.toString());
      return null;
    }
  }
}
