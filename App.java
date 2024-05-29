package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/vadimbelan/Desktop/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("http://www.papercdcase.com/index.php");

            String artistName = "Travis Scott";
            String albumTitle = "Utopia";
            String[] tracks = {
                    "HYAENA", "THANK GOD", "MODERN JAM (Ft. Teezo Touchdown)", "MY EYES",
                    "GOD’S COUNTRY", "SIRENS", "MELTDOWN (Ft. Drake)",
                    "FE!N (Ft. Playboi Carti)", "DELRESTO (ECHOES) by Beyoncé & Travis Scott", "I KNOW ?"
            };

            WebElement artistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='artist']")));
            artistField.sendKeys(artistName);

            WebElement titleField = driver.findElement(By.xpath("//input[@name='title']"));
            titleField.sendKeys(albumTitle);

            for (int i = 0; i < tracks.length; i++) {
                String xpath = String.format("//input[@name='track%d']", i + 1);
                WebElement trackField = driver.findElement(By.xpath(xpath));
                trackField.sendKeys(tracks[i]);
            }

            WebElement jewelCaseOption = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            jewelCaseOption.click();

            WebElement paperA4Option = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            paperA4Option.click();

            WebElement submitButton = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            submitButton.click();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
