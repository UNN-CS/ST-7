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

        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\example\\Desktop\\chrome\\chrome.exe");
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://www.papercdcase.com/index.php");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            String artistName = "Queen";
            String albumTitle = "A Night at the Opera";
            List<String> trackList = List.of(
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

            String artistInputXPath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
            String albumTitleInputXPath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
            String trackInputXPathTemplate = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
            String jewelCaseOptionXPath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
            String a4PaperOptionXPath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]";
            String createCaseButtonXPath = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";

            // Fill artist name
            WebElement artistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(artistInputXPath)));
            artistInputField.sendKeys(artistName);

            // Fill album title
            WebElement albumTitleInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(albumTitleInputXPath)));
            albumTitleInputField.sendKeys(albumTitle);

            // Fill track names
            for (int i = 0; i < trackList.size(); i++) {
                int column = i / 8 + 1;
                int row = i % 8 + 1;
                String trackInputXPath = String.format(trackInputXPathTemplate, column, row);
                WebElement trackInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trackInputXPath)));
                trackInputField.sendKeys(trackList.get(i));
            }

            // Select Jewel Case
            WebElement jewelCaseOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(jewelCaseOptionXPath)));
            jewelCaseOption.click();

            // Select A4 paper type
            WebElement a4PaperOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(a4PaperOptionXPath)));
            a4PaperOption.click();

            // Submit the form
            WebElement createCaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(createCaseButtonXPath)));
            createCaseButton.click();

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
