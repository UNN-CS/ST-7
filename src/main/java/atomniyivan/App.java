package atomniyivan;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App 
{
    private final Album musicAlbum;
    private final WebDriver webDriver;

    private static final String ARTIST_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
    private static final String TYPE_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static final String PAPER_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[1]";
    private static final String CREATE_BUTTON = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";

    public static void main( String[] args )
    {
            new App(
                new AirbourneAlbum()
            ).startWebDriver();
    }

    private App(Album album) {
        this.musicAlbum = album;
        this.webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\khram\\Downloads\\chromedriver-win64\\chromedriver.exe");
    }

    public void startWebDriver() {
        try {
            webDriver.get("http://www.papercdcase.com/index.php");

            findElementByXpath(ARTIST_XPATH).sendKeys(musicAlbum.getArtist());
            findElementByXpath(TITLE_XPATH).sendKeys(musicAlbum.getAlbumTitle());

            musicListToXpath(musicAlbum.getMusicTracks()).forEach((track, xpath) -> findElementByXpath(xpath).sendKeys(track));

            findElementByXpath(TYPE_XPATH).click();
            findElementByXpath(PAPER_XPATH).click();

            findElementByXpath(CREATE_BUTTON).click();

        } catch (Exception e) {
            System.out.println("Element not found!");
            e.printStackTrace();
        }
    }

    private WebElement findElementByXpath(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    private Map<String, String> musicListToXpath(List<String> musicTracks) {

        Map<String, String> xpaths = new HashMap<>(musicTracks.size());

        for (int i = 0; i < musicTracks.size(); i++) {
            int colIndex = i / 8;
            int rowIndex = i % 8;

            String xpath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[" + (colIndex + 1) + "]/table/tbody/tr[" + (rowIndex + 1) + "]/td[2]/input";
            xpaths.put(musicTracks.get(i), xpath);
        }
        return xpaths;
    }
}
