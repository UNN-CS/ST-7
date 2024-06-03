import constants.ByXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.IntStream;


import static constants.WebDriver.WEBDRIVER;
import static constants.WebDriver.WEBDRIVER_PATH;


public class App {
    static final int TRACKS_COLUMN_LENGTH = 8;
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        AlbumGruppaKrovi album = new AlbumGruppaKrovi();
        try {
            driver.get(ByXpath.WEBSITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            inputDataInElement(ByXpath.ARTIST_INPUT_XPATH, album.getArtist());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            inputDataInElement(ByXpath.ALBUM_TITLE_INPUT_XPATH, album.getTitle());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            inputTracks(album.getTracks());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            clickOnElement(ByXpath.JEWEL_CASE_OPTION_XPATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            clickOnElement(ByXpath.A_4_PAPER_OPTION_XPATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            clickOnElement(ByXpath.CREATE_CASE_BUTTON_XPATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            downloadPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void inputDataInElement(By xPath, String data) {
        WebElement element = driver.findElement(xPath);
        element.sendKeys(data);
    }

    private static void clickOnElement(By xPath) {
        WebElement element = driver.findElement(xPath);
        element.click();
    }

    private static void inputTracks(List<String> tracks) {
        final int tracksSize = tracks.size();
        IntStream.range(0, tracksSize).forEach(i -> {
            final int trackColumnIndex = i / TRACKS_COLUMN_LENGTH + 1;
            final int trackRowIndex = i % TRACKS_COLUMN_LENGTH + 1;
            By trackXpath = By.xpath(String.format(ByXpath.TRACK_STRING, trackColumnIndex, trackRowIndex));
            inputDataInElement(trackXpath, tracks.get(i));
        });
    }

    private static void downloadPDF() {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        String downloadLink = driver.getCurrentUrl();
        System.out.println("PDF downloaded from: " + downloadLink);
    }
}
