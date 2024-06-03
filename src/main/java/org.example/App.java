package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedHashMap;
import java.util.Map;

public class MainApp {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/path/to/your/chromedriver");
        WebDriver webConfigurator = new ChromeDriver();

        Map<String, String> factElem = new LinkedHashMap<String, String>() {{
            put("artistInput", "//input[@name='artist']");
            put("albumInput", "//input[@name='album']");

            int iter1 = 1;
            while (iter1 <= 5) {
                put("track" + iter1, "//table[@class='noBorder']/tbody/tr/td[1]/table/tbody/tr[" + iter1 + "]/td[2]/input");
                iter1++;
            }

            int iter2 = 1;
            while (iter2 <= 5) {
                put("track" + (iter2 + 5), "//table[@class='noBorder']/tbody/tr/td[2]/table/tbody/tr[" + iter2 + "]/td[2]/input");
                iter2++;
            }

            put("jewelCaseCheckbox", "//input[@value='Jewel Case']");
            put("a4PaperCheckbox", "//input[@value='A4 Paper']");
            put("submitButton", "//input[@type='submit']");
        }};

        Map<String, String> ValForIn = new LinkedHashMap<String, String>() {{
            put("artistInput", "Katy Perry");
            put("albumInput", "Teenage Dream");

            String[] albumSongs = {
                "Teenage Dream", "California Gurls", "Peacock", "The One That Got Away", "Pearl",
                "Not Like the Movies", "Last Friday Night", "Firework", "Circle the Drain", "Who Am I Living For?"
            };
            int iter3 = 1;
            while (iter3 <= 10) {
                put("track" + iter3, albumSongs[iter3 - 1]);
                iter3++;
            }
        }};

        String LinkForApplication = "http://www.papercdcase.com/index.php";

        try {
            webConfigurator.get(LinkForApplication);

            for (Map.Entry<String, String> EntIn : ValForIn.entrySet()) {
                String OpenIn = EntIn.getKey();
                String ValIn = EntIn.getValue();

                if (OpenIn.startsWith("track") && ValIn.isEmpty()) {
                    continue;
                }

                WebElement inputField = webConfigurator.findElement(By.xpath(factElem.get(OpenIn)));
                inputField.sendKeys(ValIn);
            }

            webConfigurator.findElement(By.xpath(factElem.get("jewelCaseCheckbox"))).click();
            webConfigurator.findElement(By.xpath(factElem.get("a4PaperCheckbox"))).click();
            webConfigurator.findElement(By.xpath(factElem.get("submitButton"))).click();

        } catch (Exception e) {
            System.out.println("Возникли следующие ошибки:");
            System.out.println(e.getMessage());
        } finally {
            webConfigurator.quit();
        }
    }
}
