import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;


public class App {
    static final By ARTIST_INPUT_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
    static final By ALBUM_TITLE_INPUT_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
    static final By JEWEL_CASE_OPTION_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
    static final By A_4_PAPER_OPTION_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
    static final By CREATE_CASE_BUTTON_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");
    static final String TRACK_STRING= "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
    static final String WEBDRIVER = "webdriver.chrome.driver";
    static final String WEBDRIVER_PATH = "chromedriver-mac-arm64/chromedriver";
    static final String WEBSITE = "http://www.papercdcase.com/index.php";
    static final int TRACKS_COLUMN_LENGTH = 8;


    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        System.setProperty(WEBDRIVER, WEBDRIVER_PATH);
        AlbumWhyBaby album = new AlbumWhyBaby();
        try {
            driver.get(WEBSITE);
            WebElement artistTextInputElement = driver.findElement(ARTIST_INPUT_XPATH);
            artistTextInputElement.sendKeys(album.getArtist());

            WebElement titleTextInputElement = driver.findElement(ALBUM_TITLE_INPUT_XPATH);
            titleTextInputElement.sendKeys(album.getTitle());

            inputTracks(driver, album.getTracks());

            WebElement jewelCaseButtonElement = driver.findElement(JEWEL_CASE_OPTION_XPATH);
            jewelCaseButtonElement.click();

            WebElement a4ButtonElement = driver.findElement(A_4_PAPER_OPTION_XPATH);
            a4ButtonElement.click();

            WebElement createCdCaseElement = driver.findElement(CREATE_CASE_BUTTON_XPATH);
            createCdCaseElement.submit();
            downloadPDF(driver);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    private static void inputTracks(WebDriver driver, List<String> tracks) {
        for (int i = 0; i < tracks.size(); i++) {
            int trackColumnIndex = i / TRACKS_COLUMN_LENGTH + 1;
            int trackRowIndex = i % TRACKS_COLUMN_LENGTH + 1;
            By trackXpath = By.xpath(TRACK_STRING.formatted(trackColumnIndex, trackRowIndex));
            WebElement trackTextInputElement = driver.findElement(trackXpath);
            trackTextInputElement.sendKeys(tracks.get(i));
        }
    }

    private static void downloadPDF(WebDriver driver) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        String downloadLink = driver.getCurrentUrl();
        System.out.println("PDF downloaded from: " + downloadLink);
    }
}
