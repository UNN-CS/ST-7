package atomniyivan;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;


public class MusicAlbumApp {
    private static final String ARTIST = "Airbourne";
    private static final String ALBUM_TITLE = "Breakin' Outta Hell";
    private static final List<String> MUSIC_TRACKS = Arrays.asList(
            "Breakin' Outta Hell",
            "Rivalry", "Get Back Up",
            "It's Never Too Loud For Me", "Thin The Blood",
            "I'm Going To Hell For This", "Down On You",
            "Never Been Rocked Like This", "When I Drink I Go Crazy",
            "Do Me Like You Do Yourself", "It's All For Rock N' Roll"
    );

    private static final String SOURCE = "http://www.papercdcase.com/index.php";
    private static final String DRIVER_PATH = "C:\\Users\\khram\\Downloads\\chromedriver-win64\\chromedriver.exe";

    private static final String PREFIX_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form";
    private static final String ARTIST_XPATH = PREFIX_XPATH + "/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE_XPATH = PREFIX_XPATH + "/table/tbody/tr[2]/td[2]/input";
    private static final String MUSIC_TRACKS_XPATH = PREFIX_XPATH + "/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
    private static final String TYPE_XPATH = PREFIX_XPATH + "/table/tbody/tr[4]/td[2]/input[2]";
    private static final String PAPER_XPATH = PREFIX_XPATH + "/table/tbody/tr[5]/td[2]/input[1]";
    private static final String CREATE_CD_XPATH = PREFIX_XPATH + "/p/input";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver chromeWebDriver = new ChromeDriver();

        try {
            chromeWebDriver.get(SOURCE);
            WebDriverWait webDriverWait = new WebDriverWait(chromeWebDriver, Duration.ofSeconds(2));

            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ARTIST_XPATH))).sendKeys(ARTIST);
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(TITLE_XPATH))).sendKeys(ALBUM_TITLE);

            for (int i = 0; i < MUSIC_TRACKS.size(); i++) {
                String trackXpath = String.format(MUSIC_TRACKS_XPATH, (i / 8) + 1, (i % 8) + 1);
                webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(trackXpath))).sendKeys(MUSIC_TRACKS.get(i));
            }

            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(TYPE_XPATH))).click();
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(PAPER_XPATH))).click();
            webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_CD_XPATH))).click();

            for (String windowHandle : chromeWebDriver.getWindowHandles()) {
                chromeWebDriver.switchTo().window(windowHandle);
            }

            // Add PDF to this project
            FileUtils.copyURLToFile(new URL(chromeWebDriver.getCurrentUrl()), new File("cd.pdf"));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            chromeWebDriver.quit();
        }
    }
}