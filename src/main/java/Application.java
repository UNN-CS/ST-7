import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.net.URL;

public class Application {

    public static void main(String[] args) {
        new Application(
                new KatyPerryAlbum(
                        "Katy Perry",
                        "One of the Boys",
                        Arrays.asList(
                                "One of the Boys",
                                "I Kissed a Girl",
                                "Waking Up in Vegas",
                                "Thinking of You",
                                "Mannequin",
                                "Ur So Gay",
                                "Hot n Cold",
                                "If You Can Afford Me",
                                "Lost",
                                "Self Inflicted",
                                "Iâ m Still Breathing",
                                "Fingerprints"
                        )
                )
        ).startWorkTheWebDriver();
    }

    // XPath Constants:
    private static final String NAME_ARTIST_INPUT_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";

    private static final String NAME_TITLE_INPUT_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";

    private static final String NAME_TYPE_RADIOBUTTON_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";

    private static final String NAME_PAPER_RADIOBUTTON_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]";

    private static final String NAME_SUBMIT_BUTTON_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";

    // WebDriver and URL Constants:
    private static final String NAME_CHROME_DRIVER_PATH =
            "C:\\Users\\HP\\chromedriver.exe";

    private static final String NAME_CHROME_DRIVER_PROPERTY =
            "webdriver.chrome.driver";

    private static final String NAME_TARGET_URL =
            "http://www.papercdcase.com/index.php";

    //
    private final KatyPerryAlbum katyPerryAlbum;
    private final WebDriver webDriver;

    private Application(KatyPerryAlbum katyPerryAlbum) {
        this.katyPerryAlbum = katyPerryAlbum;
        try {
            System.setProperty(NAME_CHROME_DRIVER_PROPERTY, NAME_CHROME_DRIVER_PATH);
            this.webDriver = new ChromeDriver();
        } catch (Exception e) {
            throw new RuntimeException("Failed initialization the Chrome WebDriver!", e);
        }
    }

    public void startWorkTheWebDriver() {
        try {
            webDriver.get(NAME_TARGET_URL);

            findElementByXpathName(NAME_ARTIST_INPUT_XPATH).sendKeys(katyPerryAlbum.getArtistName());
            findElementByXpathName(NAME_TITLE_INPUT_XPATH).sendKeys(katyPerryAlbum.getTitleAlbum());

            List<String> tracks = katyPerryAlbum.getMusicsTracks();
            for (int i = 0; i < tracks.size(); i++) {
                String track = tracks.get(i);
                String xpath = String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input",
                        i / 8 + 1, i % 8 + 1);
                findElementByXpathName(xpath).sendKeys(track);
            }

            findElementByXpathName(NAME_TYPE_RADIOBUTTON_XPATH).click();
            findElementByXpathName(NAME_PAPER_RADIOBUTTON_XPATH).click();
            findElementByXpathName(NAME_SUBMIT_BUTTON_XPATH).click();

            downloadPDF();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    private WebElement findElementByXpathName(String xpath) {
        return webDriver.findElement(By.xpath(xpath));
    }

    private void downloadPDF() throws IOException, InterruptedException {
        //Thread.sleep(3000); // без ожидания работает корректно, но не успевает отобразиться страница с pdf файлом, так как в конце выполнения программы браузер закрывается
        String pdfUrl = webDriver.getCurrentUrl();

        Path downloadPath = Paths.get("cd.pdf");
        try (InputStream in = new URL(pdfUrl).openStream();
             OutputStream out = new FileOutputStream(downloadPath.toFile())) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }
}
