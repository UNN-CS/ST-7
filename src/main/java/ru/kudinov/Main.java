package ru.kudinov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

record Album(String name, String artistName, String[] trackNames) {}

public class Main {
    private static final String CHROME_DRIVER_PATH = "/home/relby/Downloads/chromedriver-linux64/chromedriver";
    private static final String CHROME_PATH = "/home/relby/Downloads/chrome-linux64/chrome";
    private static final String URL = "http://www.papercdcase.com/index.php";
    private class XPath {
        public static final String ARTIST_TEXT_INPUT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
        public static final String TITLE_TEXT_INPUT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
        public static final String TRACK_TEXT_INPUT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
        public static final String JEWEL_CASE_BUTTON = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
        public static final String A4_BUTTON = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]";
        public static final String FORM = "/html/body/table[2]/tbody/tr/td[1]/div/form";
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(CHROME_PATH);
        WebDriver driver = new ChromeDriver(options);

        driver.get(URL);

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

        WebElement artistTextInputElement = driver.findElement(By.xpath(XPath.ARTIST_TEXT_INPUT));
        artistTextInputElement.sendKeys(album.artistName());

        WebElement titleTextInputElement = driver.findElement(By.xpath(XPath.TITLE_TEXT_INPUT));
        titleTextInputElement.sendKeys(album.name());

        String[] albumTrackNames = album.trackNames();
        int tracksColumnLength = 8;
        for (int i = 0; i < albumTrackNames.length; i++) {
            int trackColumnIndex = i / tracksColumnLength + 1;
            int trackRowIndex = i % tracksColumnLength + 1;

            WebElement trackTextInputElement = driver.findElement(By.xpath(XPath.TRACK_TEXT_INPUT.formatted(trackColumnIndex, trackRowIndex)));
            trackTextInputElement.sendKeys(albumTrackNames[i]);
        }

        WebElement jewelCaseButtonElement = driver.findElement(By.xpath(XPath.JEWEL_CASE_BUTTON));
        jewelCaseButtonElement.click();

        WebElement a4ButtonElement = driver.findElement(By.xpath(XPath.A4_BUTTON));
        a4ButtonElement.click();

        WebElement form = driver.findElement(By.xpath(XPath.FORM));
        form.submit();
    }
}
