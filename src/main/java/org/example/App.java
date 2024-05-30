package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class App {
    private static final String ARTIST_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
    private static final String TYPE_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"; // Jewel case
    private static final String PAPER_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"; // A4
    private static final String BUTTON_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";
    public static final String DRIVER_PATH = "E:/Dev/chromedriver-win64/chromedriver.exe";
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
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
            webDriver.findElement(By.xpath(ARTIST_PATH)).sendKeys(artistName);
            webDriver.findElement(By.xpath(TITLE_PATH)).sendKeys(albumTitle);
            for (int i = 0; i < trackList.size(); i++) {
                int col = i / 8 + 1;
                int row = i % 8 + 1;
                webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[" +
                        col + "]/table/tbody/tr[" + row + "]/td[2]/input")).sendKeys(trackList.get(i));
            }
            webDriver.findElement(By.xpath(TYPE_PATH)).click();
            webDriver.findElement(By.xpath(PAPER_PATH)).click();
            webDriver.findElement(By.xpath(BUTTON_PATH)).submit();
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}

