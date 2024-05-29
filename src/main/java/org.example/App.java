package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;

public class App {

    static Map<String, String> fieldXpaths = new LinkedHashMap<>() {{
        put("ArtistName", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
        put("AlbumTitle", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");

        for (int i = 1; i <= 5; i++) {
            put("Song" + i, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[" + i + "]/td[2]/input");
        }

        for (int i = 1; i <= 5; i++) {
            put("Song" + (i + 5), "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[" + i + "]/td[2]/input");
        }

        put("JewelCase", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
        put("A4Paper", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
        put("SubmitButton", "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");
    }};

    static Map<String, String> inputData = new LinkedHashMap<>() {{
        put("ArtistName", "Katy Perry");
        put("AlbumTitle", "Teenage Dream");

        String[] trackList = {
            "Teenage Dream", "California Gurls", "Peacock", "The One That Got Away", "Pearl",
            "Not Like the Movies", "Last Friday Night", "Firework", "Circle the Drain", "Who Am I Living For?"
        };
        for (int i = 1; i <= 10; i++) {
            put("Song" + i, trackList[i - 1]);
        }
    }};

    static String targetURL = "http://www.papercdcase.com/index.php";
    static String chromeDriverPath = "D:\\Marina\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get(targetURL);

            for (Map.Entry<String, String> fieldEntry : inputData.entrySet()) {
                String fieldKey = fieldEntry.getKey();
                String fieldValue = fieldEntry.getValue();
                
                if (fieldKey.startsWith("Song") && fieldValue.isEmpty()) {
                    continue;
                }

                WebElement inputField = driver.findElement(By.xpath(fieldXpaths.get(fieldKey)));
                inputField.sendKeys(fieldValue);

                Thread.sleep(100);
            }

            driver.findElement(By.xpath(fieldXpaths.get("JewelCase"))).click();
            driver.findElement(By.xpath(fieldXpaths.get("A4Paper"))).click();
            driver.findElement(By.xpath(fieldXpaths.get("SubmitButton"))).click();
        } catch (Exception e) {
            System.out.println("An error occurred:");
            System.out.println(e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
