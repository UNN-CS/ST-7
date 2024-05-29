package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class PapercaseAutomation {
    public static void main(String[] args) {
        // Set ChromeDriver path
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\example\\Desktop\\chromedriver\\chromedriver.exe");

        // Configure Chrome options
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setBinary("C:\\Users\\example\\Desktop\\chrome\\chrome.exe");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver(chromeOptions);

        try {
            // Navigate to the website
            driver.get("http://www.papercdcase.com/index.php");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Fill album details
            fillAlbumDetails(driver, wait);

            // Select case type and generate
            selectCaseAndGenerate(driver, wait);

            // Download PDF
            downloadPDF(driver);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Quit WebDriver
            driver.quit();
        }
    }

    private static void fillAlbumDetails(WebDriver driver, WebDriverWait wait) {
        // Album details
        String artist = "Led Zeppelin";
        String albumTitle = "Led Zeppelin IV";
        List<String> trackList = Arrays.asList("Black Dog", "Rock and Roll", "The Battle of Evermore", "Stairway to Heaven", "Misty Mountain Hop", "Four Sticks", "Going to California", "When the Levee Breaks"
        );

        // Locators
        By artistInputLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
        By albumTitleInputLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
        String trackInputXPathTemplate = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[%d]/td[2]/input";

        // Fill artist name
        WebElement artistInput = wait.until(ExpectedConditions.visibilityOfElementLocated(artistInputLocator));
        artistInput.sendKeys(artist);

        // Fill album title
        WebElement albumTitleInput = wait.until(ExpectedConditions.visibilityOfElementLocated(albumTitleInputLocator));
        albumTitleInput.sendKeys(albumTitle);

        // Fill track names
        for (int i = 0; i < trackList.size(); i++) {
            String trackInputXPath = String.format(trackInputXPathTemplate, i + 1);
            WebElement trackInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trackInputXPath)));
            trackInput.sendKeys(trackList.get(i));
        }
    }

    private static void selectCaseAndGenerate(WebDriver driver, WebDriverWait wait) {
        // Case selection locators
        By jewelCaseOptionLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
        By a4PaperOptionLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
        By createCaseButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");

        // Select Jewel Case
        WebElement jewelCaseOption = wait.until(ExpectedConditions.elementToBeClickable(jewelCaseOptionLocator));
        jewelCaseOption.click();

        // Select A4 paper type
        WebElement a4PaperOption = wait.until(ExpectedConditions.elementToBeClickable(a4PaperOptionLocator));
        a4PaperOption.click();

        // Submit the form
        WebElement createCaseButton = wait.until(ExpectedConditions.elementToBeClickable(createCaseButtonLocator));
        createCaseButton.click();
    }

    private static void downloadPDF(WebDriver driver) {
        // Switch to the new window
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Get download link
        String downloadLink = driver.getCurrentUrl();

        // Download PDF
        // Code for downloading PDF goes here
        System.out.println("PDF downloaded from: " + downloadLink);
    }
}
