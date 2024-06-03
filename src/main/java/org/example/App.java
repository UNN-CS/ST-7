// Copyright 2024 Nedelin Dmitry
package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "E:\\sourse\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input"));
                element.sendKeys("NAOKI");
            } catch (Exception e) {
                System.out.println("ErrorNum0");
                System.out.println(e.toString());
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
                element.sendKeys("Guilty Gear -Strive-");
            } catch (Exception e) {
                System.out.println("ErrorNum1");
                System.out.println(e.toString());
            }

            List<String> trackList = new ArrayList<>();
            trackList.add("Smell of the Game");
            trackList.add("Find Your One Way");
            trackList.add("Armor-clad Faith");
            trackList.add("The Town Inside Me");
            trackList.add("Like a Weed, Naturally, as a Matter of Course");
            trackList.add("Alone Infection");
            trackList.add("Let Me Carve Your Way");
            trackList.add("The Roar of the Spark");
            trackList.add("Ups and Downs");
            trackList.add("What do you fight for");
            trackList.add("Love the Subhuman Self");
            trackList.add("Hellfire ");
            trackList.add("Rock Parade");
            trackList.add("Requiem");
            trackList.add("Symphony ");
            trackList.add("Drift ");
            for (int i = 0; i < trackList.size(); i++) {
                WebElement element = webDriver.findElement(By.xpath(String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", i / 8 + 1, i % 8 + 1)));
                element.sendKeys(trackList.get(i));
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
                element.click();
            } catch (Exception e) {
                System.out.println("ErrorNum2");
                System.out.println(e.toString());
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
                element.click();
            } catch (Exception e) {
                System.out.println("ErrorNum3");
                System.out.println(e.toString());
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[7]/td[2]/input"));
                element.click();
            } catch (Exception e) {
                System.out.println("ErrorNum4");
                System.out.println(e.toString());
            }
            webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input")).submit();
        } catch (Exception e) {
            System.out.println("ErrorNum5");
            System.out.println(e.toString());
        }
    }
}