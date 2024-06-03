package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class App {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "E:/Dev/chromedriver-win64/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        String artistName = "Bring Me The Horizon";
        String albumTitle = "POST HUMAN: NeX GEn";
        List<String> trackList = Arrays.asList(
                "[ost] dreamseeker", "YOUtopia", "Kool-Aid", "Top 10 staTues tHat CriEd bloOd", "liMOusIne (feat. AURORA)",
                "DArkSide", "a bulleT w/ my namE On (feat. Underoath)", "[ost] (spi)ritual", "n/A", "LosT", "sTraNgeRs",
                "R.i.p. (duskCOre RemIx)", "AmEN! (feat. Lil Uzi Vert and Daryl Palumbo of Glassjaw)", "[ost] p.u.s.s.-e",
                "DiE4u", "DIg It");
        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            String base = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody";
            sendValue(webDriver, base + "/tr[1]/td[2]/input", artistName);
            sendValue(webDriver, base + "/tr[2]/td[2]/input", albumTitle);
            String tracks = base + "/tr[3]/td[2]/table/tbody/tr/td[%d + 1]/table/tbody/tr[%d + 1]/td[2]/input";
            for (int i = 0; i < trackList.size(); i++) {
                String xPath = String.format(tracks, i / 8, i % 8);
                sendValue(webDriver, xPath, trackList.get(i));
            }
            webDriver.findElement(By.xpath(base + "/tr[4]/td[2]/input[2]")).click(); // type
            webDriver.findElement(By.xpath(base + "/tr[5]/td[2]/input[2]")).click(); // paper
            webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input")).submit();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    private static void sendValue(WebDriver driver, String xPath, String value) {
        WebElement input = driver.findElement(By.xpath(xPath));
        input.sendKeys(value);
    }
}
