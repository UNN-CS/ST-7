import constants.ByXpath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
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
            return;
        }

        final List<Runnable> actions = Arrays.asList(
                () -> inputDataInElement(ByXpath.ARTIST_INPUT_XPATH, album.getArtist()),
                () -> inputDataInElement(ByXpath.ALBUM_TITLE_INPUT_XPATH, album.getTitle()),
                () -> inputTracks(album.getTracks()),
                () -> clickOnElement(ByXpath.JEWEL_CASE_OPTION_XPATH),
                () -> clickOnElement(ByXpath.A_4_PAPER_OPTION_XPATH),
                () -> clickOnElement(ByXpath.CREATE_CASE_BUTTON_XPATH)
        );

        for (Runnable action : actions) {
            action.run();
        }

        try {
            downloadPDF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void inputDataInElement(By xPath, String data) {
        WebElement element;
        try {
            element = driver.findElement(xPath);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске элемента по XPath: " + xPath.toString());
            e.printStackTrace();
            return;
        }

        try {
            element.sendKeys(data);
        } catch (Exception e) {
            System.out.println("Ошибка при вводе данных в элемент с XPath: " + xPath.toString() + " и данными: " + data);
            e.printStackTrace();
        }
    }

    private static void clickOnElement(By xPath) {
        WebElement element;
        try {
            element = driver.findElement(xPath);
        } catch (Exception e) {
            System.out.println("Ошибка при поиске элемента по XPath: " + xPath.toString());
            e.printStackTrace();
            return;
        }

        try {
            element.click();
        } catch (Exception e) {
            System.out.println("Ошибка при клике по элементу с XPath: " + xPath.toString());
            e.printStackTrace();
        }
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
