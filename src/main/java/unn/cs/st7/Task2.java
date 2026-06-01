package unn.cs.st7;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get("https://api.ipify.org/?format=json");
        String jsonStr = readJsonText(webDriver);
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        String ip = (String) obj.get("ip");
        System.out.println("IPv4-адрес: " + ip);
    }

    static String readJsonText(WebDriver webDriver) {
        List<WebElement> preElements = webDriver.findElements(By.tagName("pre"));
        if (!preElements.isEmpty()) {
            return preElements.get(0).getText();
        }
        return webDriver.findElement(By.tagName("body")).getText();
    }
}
