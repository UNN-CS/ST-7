package org.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.*;
import java.util.stream.Collectors;

public class App {

    private static final String WEB_URL = "http://www.papercdcase.com/index.php";
    private static final String CHROME_DRIVER_PATH = "/home/mineshadow/Загрузки/chromedriver-linux64/chromedriver";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();

        try {
            webDriver.get(WEB_URL);
            fillForm(webDriver);
            selectOptions(webDriver);
            submitForm(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }

    private static void fillForm(WebDriver webDriver) throws InterruptedException {
        Map<String, String> xPaths = getXPaths();
        Map<String, String> values = getValues();

        Set<String> textFields = xPaths.keySet().stream()
                .filter(item -> !List.of("Paper", "Type", "CreateBtn").contains(item))
                .collect(Collectors.toSet());

        for (String key : textFields) {
            String xPath = xPaths.get(key);
            String value = values.get(key);
            webDriver.findElement(By.xpath(xPath)).sendKeys(value);

            Thread.sleep(100);
        }
    }

    private static void selectOptions(WebDriver webDriver) {
        Map<String, String> xPaths = getXPaths();

        webDriver.findElement(By.xpath(xPaths.get("Type"))).click();
        webDriver.findElement(By.xpath(xPaths.get("Paper"))).click();
    }

    private static void submitForm(WebDriver webDriver) {
        Map<String, String> xPaths = getXPaths();

        webDriver.findElement(By.xpath(xPaths.get("CreateBtn"))).click();
    }

    private static Map<String, String> getXPaths() {
        return new HashMap<>() {{
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

            put("Force", "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[7]/td[2]/input");
            put("CreateBtn", "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");
        }};
    }

    private static Map<String, String> getValues() {
        return new HashMap<>() {{
            put("Artist", "Bonanza");
            put("Title", "Adventures of Programmers");

            put("Track1", "Why is this..");
            put("Track2", "Task..");
            put("Track3", "Looks so unusual?");
            put("Track4", "Am I..");
            put("Track5", "Missing something?");
            put("Track6", "Who uses Selenium though?");
            put("Track7", "Is this my end?");
            put("Track8", "When can I eat my dinner?");

            put("Track9", "This world is complicated");
            put("Track10", "But work is work");
            put("Track11", "You have to force yourself into it");
            put("Track12", "Even if.. It looks unusual");
            put("Track13", "Gotta keep going at it");
            put("Track14", "And stay creative");
            put("Track15", "Unless you want to be kicked out");
            put("Track16", "Then you do you I guess");
            put("Force", "1");
        }};
    }
}