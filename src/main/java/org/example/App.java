package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver-win64/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            String artistName = "Eminem";
            String albumTitle = "Kamikaze";
            String[] tracks = {
                    "ASS Like That",
                    "Killshot",
                    "The Ringer",
                    "Greatest",
                    "Lucky You",
                    "Normal",
                    "Eminem Calls Paul (Skit",
                    "Stepping Stone",
                    "Not Alike",
                    "Kamikaze",
                    "Fall",
                    "Nice Guy",
                    "Good Guy",
                    "Venom",
            };

            WebElement artist = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input")));
            artist.sendKeys(artistName);

            WebElement title = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
            title.sendKeys(albumTitle);

            for (int i = 0; i < tracks.length; i++) {
                int row = (i < 8) ? i + 1 : i - 7;
                int col = (i < 8) ? 1 : 2;
                String xpath = String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", col, row);
                WebElement track = webDriver.findElement(By.xpath(xpath));
                track.sendKeys(tracks[i]);
            }

            WebElement typeJewelcase = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            typeJewelcase.click();
            WebElement PaperA4 = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            PaperA4.click();

            WebElement btn = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            btn.submit();
        } catch (Exception e) {
            System.out.println("Error");

        }
    }
}
