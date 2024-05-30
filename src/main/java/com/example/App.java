package com.example;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            driver.get("http://www.papercdcase.com/index.php");
            String artistName = "Billie Eilish";
            String albumTitle = "Happier Than Ever";
            String[] tracksNames = {
                    "Getting Older",
                    "I Didn't Change My Number",
                    "Billie Bossa Nova",
                    "My Future",
                    "Oxytocin",
                    "Goldwing",
                    "Lost Cause",
                    "Halley's Comet",
                    "Not My Responsibility",
                    "OverHeated",
                    "Everybody Dies",
                    "Your Power",
                    "NDA",
                    "Therefore I Am",
                    "Happier Than Ever",
                    "Male Fantasy"
            };
            WebElement artist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input")));
            artist.sendKeys(artistName);
            WebElement title = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
            title.sendKeys(albumTitle);
            for (int i = 0; i < tracksNames.length; i++) {
                int row = (i < 8) ? i + 1 : i - 7;
                int col = (i < 8) ? 1 : 2;
                String xpath = String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", col, row);
                WebElement track = driver.findElement(By.xpath(xpath));
                track.sendKeys(tracksNames[i]);
            }
            WebElement buttonJewelCase = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            buttonJewelCase.click();
            WebElement buttonA4 = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            buttonA4.click();
            WebElement buttonCreate = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            buttonCreate.submit();

        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e.getMessage());
        }
    }
}