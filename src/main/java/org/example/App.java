package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    private static WebDriver webDriver;
    private static String path = "C:\\Users\\ASUS\\OneDrive\\Рабочий стол\\EPIC_DROP_BOT\\chromedriver.exe";
    private static String path_to_body = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();

        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            fillInputField(path_to_body + "tr[1]/td[2]/input", "Big Hit");
            fillInputField(path_to_body + "tr[2]/td[2]/input", "Black & Whites");
            List<String> tracklist = new ArrayList<>(Arrays.asList(
                    "Drug Tzar",
                    "Only Weight I Feel",
                    "Godfather, Pt. 2",
                    "Heartless",
                    "Foreclosure",
                    "Temperature Check",
                    "Black & Whites",
                    "Champion",
                    "Count Your Blessings",
                    "Sly, Slick & Wicked",
                    "Dirtbal",
                    "Gank Move"
            ));
            fillTracklistFields(tracklist);

            clickElement(path_to_body + "tr[4]/td[2]/input[2]");
            clickElement(path_to_body + "tr[5]/td[2]/input[2]");
            clickElement(path_to_body + "tr[7]/td[2]/input");

            webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input")).submit();
        } catch (Exception e) {
            System.out.println("Error: main");
        }
    }

    private static void fillInputField(String xpath, String value) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpath));
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Error: fillInputField");
        }
    }

    private static void fillTracklistFields(List<String> tracklist) {
        for (int i = 0; i < tracklist.size(); i++) {
            String xpath = String.format(path_to_body + "tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", i / 8 + 1, i % 8 + 1);
            fillInputField(xpath, tracklist.get(i));
        }
    }

    private static void clickElement(String xpath) {
        try {
            WebElement element = webDriver.findElement(By.xpath(xpath));
            element.click();
        } catch (Exception e) {
            System.out.println("Error: clickElement");
        }
    }
}
