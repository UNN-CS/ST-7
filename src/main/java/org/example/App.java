package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App
{
    private Album album;
    private WebDriver webDriver;

    private static final String ARTIST = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
    private static final String TYPE = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static final String PAPER = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[1]";
    private static final String CREATE = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";

    public static void main( String[] args )
    {
        new App(new Album()).startWebDriver();
    }

    private App(Album album) {
        this.album = album;
        this.webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "F:\\Projects\\testing\\chromedriver-win64\\chromedriver.exe");
    }

    private void startWebDriver() {
        try {
            webDriver.get("http://www.papercdcase.com/index.php");

            webDriver.findElement(By.xpath(ARTIST)).sendKeys(album.getArtist());
            webDriver.findElement(By.xpath(TITLE)).sendKeys(album.getAlbumTitle());

            for (int i = 0; i < album.getMusicTracks().size(); i++) {
                int column = i / 8;
                int row = i % 8;

                String xpath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[" + (column + 1) + "]/table/tbody/tr[" + (row + 1) + "]/td[2]/input";
                webDriver.findElement(By.xpath(xpath)).sendKeys(album.getMusicTracks().get(i));
            }

            webDriver.findElement(By.xpath(TYPE)).click();
            webDriver.findElement(By.xpath(PAPER)).click();
            webDriver.findElement(By.xpath(CREATE)).click();

        } catch (Exception e) {
            System.out.println("Element not found!");
            e.printStackTrace();
        }
    }

}
