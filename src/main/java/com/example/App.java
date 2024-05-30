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
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        try {
            webDriver.get("http://www.papercdcase.com/index.php");

            String artistName = "Eminem";
            String albumTitle = "The Marshall Mathers LP";
            String[] tracksNames = {
                    "Public Service Announcement 2000",
                    "Kill You",
                    "Stan",
                    "Paul (Skit)",
                    "Who Knew",
                    "Steve Berman (Skit)",
                    "The Way I Am",
                    "The Real Slim Shady",
                    "Remember Me?",
                    "I'm Back",
                    "Marshall Mathers",
                    "Ken Kaniff (Skit)",
                    "Drug Ballad",
                    "Amityville",
                    "Bitch Please II"
            };

            WebElement artistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input")));
            artistField.sendKeys(artistName);

            WebElement titleField = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
            titleField.sendKeys(albumTitle);

            for (int i = 0; i < tracksNames.length; i++) {
                int row = (i < 8) ? i + 1 : i - 7;
                int col = (i < 8) ? 1 : 2;
                String xpath = String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", col, row);
                WebElement track = webDriver.findElement(By.xpath(xpath));
                track.sendKeys(tracksNames[i]);
            }

            WebElement jewelCaseOption = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            jewelCaseOption.click();

            WebElement paperA4Option = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            paperA4Option.click();

            WebElement submitButton = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            submitButton.click();
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e.toString());
        }
    }
}

