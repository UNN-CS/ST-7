package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\example\\Desktop\\chromedriver\\chromedriver.exe");

        ChromeOptions settings = new ChromeOptions();
        settings.setBinary("C:\\Users\\example\\Desktop\\chrome\\chrome.exe");
        WebDriver chromeConfig = new ChromeDriver(settings);

        try {
            // Open the website
            chromeConfig.get("http://www.papercdcase.com/index.php");
            WebDriverWait needTime = new WebDriverWait(chromeConfig, Duration.ofSeconds(5));

            // Define album details
            String singerPseudonim = "Queen";
            String trackFromAlb = "A Night at the Opera";
            List<String> queensSongs = List.of(
                    "Death on Two Legs",
                    "Lazing on a Sunday Afternoon",
                    "I'm in Love with My Car",
                    "You're My Best Friend",
                    "'39",
                    "Sweet Lady",
                    "Seaside Rendezvous",
                    "The Prophet's Song",
                    "Love of My Life",
                    "Good Company",
                    "Bohemian Rhapsody",
                    "God Save the Queen"
            );

            // Fill in the album details and create the CD case
            fillAlbumDetailsAndCreateCDCase(needTime, chromeConfig, singerPseudonim, trackFromAlb, queensSongs);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        chromeConfig.quit();
    }

    private static void fillAlbumDetailsAndCreateCDCase(WebDriverWait needTime, WebDriver chromeConfig, String singerPseudonim, String trackFromAlb, List<String> queensSongs) {
        // Fill artist name
        WebElement inForSingerName = needTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='artist']")));
        inForSingerName.sendKeys(singerPseudonim);

        // Fill album title
        WebElement inForAlbName = needTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='title']")));
        inForAlbName.sendKeys(trackFromAlb);

        // Fill track names
        for (int i = 0; i < queensSongs.size(); i++) {
            String softSongsIn = String.format("//input[@name='track%d']", i + 1);
            WebElement songInPosition = needTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(softSongsIn)));
            songInPosition.sendKeys(queensSongs.get(i));
        }

        // Select Jewel Case
        WebElement n1 = needTime.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Jewel Case']")));
        n1.click();

        // Select A4 paper type
        WebElement n2 = needTime.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='A4']")));
        n2.click();

        // Submit the form
        WebElement n3 = needTime.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @value='Create Case!']")));
        n3.click();
    }
}