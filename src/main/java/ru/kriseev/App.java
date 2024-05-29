package ru.kriseev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            setFieldValueByXpath(webDriver, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input", "Megadeth");
            setFieldValueByXpath(webDriver, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input", "Rust In Peace");

            List<String> songs = new ArrayList<>();
            songs.add("Holy Wars... The Punishment Due");
            songs.add("Hangar 18");
            songs.add("Take No Prisoners");
            songs.add("Five Magics");
            songs.add("Poison Was The Cure");
            songs.add("Lucretia");
            songs.add("Tornado Of Souls");
            songs.add("Dawn Patrol");
            songs.add("Rust In Peace... Polaris");
            inputSongs(webDriver, songs);
            clickElementByXpath(webDriver,"/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
            clickElementByXpath(webDriver,"/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
            clickElementByXpath(webDriver, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[7]/td[2]/input");
            webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input")).submit();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        }
    }

    static void setFieldValueByXpath(WebDriver driver, String xpath, String value) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Could not set field value");
        }
    }

    static void clickElementByXpath(WebDriver driver, String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            element.click();
        } catch (Exception e) {
            System.out.println("Could not click on element");
        }
    }

    static void inputSongs(WebDriver driver, List<String> songs) {
        String trackFormatString = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
        for (int i = 0; i < songs.size(); i++) {
            WebElement element = driver.findElement(By.xpath(String.format(trackFormatString, i / 8 + 1, i % 8 + 1)));
            element.sendKeys(songs.get(i));
        }
    }
}
