package org.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.stream.Collectors;

public class App
{

    static Map<String, String> xPaths  = new HashMap<>() {{
        put("Artist", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
        put("Title", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");

        put("Track1", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[1]/td[2]/input");
        put("Track2", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[2]/td[2]/input");
        put("Track3", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[3]/td[2]/input");
        put("Track4", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[4]/td[2]/input");
        put("Track5", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[5]/td[2]/input");
        put("Track6", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[6]/td[2]/input");
        put("Track7", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[7]/td[2]/input");
        put("Track8", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[8]/td[2]/input");

        put("Track9", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[2]/input");
        put("Track10", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/input");
        put("Track11", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[3]/td[2]/input");
        put("Track12", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[4]/td[2]/input");
        put("Track13", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[5]/td[2]/input");
        put("Track14", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[6]/td[2]/input");
        put("Track15", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[7]/td[2]/input");
        put("Track16", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[8]/td[2]/input");

        put("Type", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
        put("Paper", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");

        put("CreateBtn", "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");
    }};

    static Map<String, String> values  = new HashMap<>() {{
        put("Artist", "Joy Division");
        put("Title", "Unknown Pleasures");

        put("Track1", "Disorder");
        put("Track2", "Day of the Lords");
        put("Track3", "Candidate");
        put("Track4", "Insight");
        put("Track5", "New Dawn Fades");
        put("Track6", "She's Lost Control");
        put("Track7", "Shadowplay");
        put("Track8", "Wilderness");

        put("Track9", "Interzone");
        put("Track10", "I Remember Nothing");
        put("Track11", "");
        put("Track12", "");
        put("Track13", "");
        put("Track14", "");
        put("Track15", "");
        put("Track16", "");
    }};

    static String webURL = "http://www.papercdcase.com/index.php";
    static String chromeDriverPath = "/home/mineshadow/Загрузки/chrome-headless-shell-linux64/chrome-headless-shell";

    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get(webURL);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }

        try {
            Set<String> textFields = xPaths.keySet().stream()
                    .filter(item -> !List.of("Paper", "Type", "CreateBtn").contains(item))
                    .collect(Collectors.toSet());

            for (String key : textFields) {
                String xPath = xPaths.get(key);
                String value = values.get(key);
                webDriver.findElement(By.xpath(xPath)).sendKeys(value);

                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }

        try {
            String xPath;

            xPath = xPaths.get("Type");
            webDriver.findElement(By.xpath(xPath)).click();

            xPath = xPaths.get("Paper");
            webDriver.findElement(By.xpath(xPath)).click();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }

        try {
            String xPath;

            xPath = xPaths.get("CreateBtn");
            webDriver.findElement(By.xpath(xPath)).click();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}