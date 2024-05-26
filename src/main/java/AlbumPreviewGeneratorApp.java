import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class AlbumPreviewGeneratorApp {

    public static void main(String[] args) {
        new AlbumPreviewGeneratorApp(
                new TheEminemShowAlbum()
        ).startWebDriver();
    }

    private static final String ARTIST_INPUT_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE_INPUT_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";

    private static final String TYPE_SECOND_RADIO_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static final String PAPER_FIRST_RADIO_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[1]";

    private static final String SUBMIT_BUTTON_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";


    private final Album album;
    private final WebDriver webDriver;

    private AlbumPreviewGeneratorApp(Album album) {
        this.album = album;
        this.webDriver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dima\\IdeaProjects\\testing-course-unn-lab-7\\driver\\chromedriver.exe");
    }

    public void startWebDriver() {
        try {
            webDriver.get("http://www.papercdcase.com/index.php");

            elementByXpath(ARTIST_INPUT_XPATH).sendKeys(album.getArtist());
            elementByXpath(TITLE_INPUT_XPATH).sendKeys(album.getTitle());

            tracksToXpath(album.getTracks()).forEach((track, xpath) -> elementByXpath(xpath).sendKeys(track));

            elementByXpath(TYPE_SECOND_RADIO_XPATH).click();
            elementByXpath(PAPER_FIRST_RADIO_XPATH).click();

            elementByXpath(SUBMIT_BUTTON_XPATH).click();

        } catch (Exception e) {
            System.out.println("Could not find web element!");
            e.printStackTrace();
        }
    }

    private Map<String, String> tracksToXpath(List<String> tracks) {
        if (tracks.size() > 16) {
            throw new IllegalArgumentException("Maximum tracks on the form must be not greater then 16");
        }

        Map<String, String> xpaths = new HashMap<>(tracks.size());

        for (int i = 0; i < tracks.size(); i++) {
            int colIndex = i / 8;
            int rowIndex = i % 8;

            String xpath = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[" + (colIndex + 1) + "]/table/tbody/tr[" + (rowIndex + 1) + "]/td[2]/input";
            xpaths.put(tracks.get(i), xpath);
        }

        return xpaths;
    }

    private WebElement elementByXpath(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }


}
