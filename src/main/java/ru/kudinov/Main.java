package ru.kudinov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

record Album(String name, String artistName, String[] trackNames) {}

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/relby/Downloads/chromedriver-linux64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.setBinary("/home/relby/Downloads/chrome-linux64/chrome");
        WebDriver driver = new ChromeDriver(options);

        driver.get("http://www.papercdcase.com/index.php");

        Album album = new Album("Hot Fuss", "The Killers", new String[] {
            "Jenny Was A Friend Of Mine",
            "Mr. Brightside",
            "Smile Like You Mean It",
            "Somebody Told Me",
            "All These Things That I've Done",
            "Any, You're A Star",
            "On Top",
            "Glamorous Indie Rock & Roll",
            "Believe Me Natalie",
            "Midnight Show",
            "Everything Will Be Alright",
        });

        WebElement artistTextInputElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input"));
        artistTextInputElement.sendKeys(album.artistName());

        WebElement titleTextInputElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
        titleTextInputElement.sendKeys(album.name());

        String[] albumTrackNames = album.trackNames();
        for (int i = 0; i < albumTrackNames.length; i++) {
            int tracksColumnLength = 8;
            int trackColumnIndex = i / tracksColumnLength + 1;
            int trackRowIndex = i % tracksColumnLength + 1;

            WebElement trackTextInputElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input".formatted(trackColumnIndex, trackRowIndex)));
            trackTextInputElement.sendKeys(albumTrackNames[i]);
        }

        WebElement jewelCaseButtonElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
        jewelCaseButtonElement.click();

        WebElement a4ButtonElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
        a4ButtonElement.click();

        WebElement createCdCaseElement = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
        createCdCaseElement.submit();
    }
}