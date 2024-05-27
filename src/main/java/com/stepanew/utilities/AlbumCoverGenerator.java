package com.stepanew.utilities;

import com.stepanew.entities.Album;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.IntStream;

public class AlbumCoverGenerator {

    private static final String ARTIST_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";

    private static final String TITLE_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";

    //первый аргумент format() -> номер колонки (1, 2); второй аргумент -> номер строки (1..8)
    private static final String TRACKS_PATH_FORMAT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";

    private static final String BUTTON_PATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";

    //первый аргумент format() -> номер типа обложки (1, 2)
    private static final String TYPE_PATH_FORMAT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[%d]";

    //первый аргумент format() -> номер бумаги обложки (1, 2)
    private static final String PAPER_PATH_FORMAT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[%d]";

    private static final String SOURCE = "http://www.papercdcase.com/index.php";

    private static final String DRIVER_NAME = "webdriver.chrome.driver";

    private static final String DRIVER_PATH = "C:\\Users\\stepanew\\IdeaProjects\\ST-7\\chromedriver.exe";

    private final Album album;

    private final WebDriver webDriver;

    static {
        System.setProperty(DRIVER_NAME, DRIVER_PATH);
    }

    public AlbumCoverGenerator(Album album) {
        this.album = album;
        this.webDriver = new ChromeDriver();
    }

    public void generateAlbumCover() {
        try {
            webDriver.get(SOURCE);

            sendKeysToPath(album.getArtist(), ARTIST_PATH);
            sendKeysToPath(album.getTitle(), TITLE_PATH);

            sendTracks();

            clickToPath(String.format(TYPE_PATH_FORMAT, 2));
            clickToPath(String.format(PAPER_PATH_FORMAT, 2));
            clickToPath(BUTTON_PATH);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void sendKeysToPath(String keys, String path) {
        webDriver.findElement(By.xpath(path)).sendKeys(keys);
    }

    private void clickToPath(String path) {
        webDriver.findElement(By.xpath(path)).click();
    }

    private void sendTracks() {
        List<String> tracks = album.getTracks();

        IntStream.range(0, tracks.size())
                .forEach(i -> {
                    int numColl = i < 8 ? 1 : 2;
                    int numRow = i < 8 ? i + 1 : (i % 8) + 1;
                    String path = String.format(TRACKS_PATH_FORMAT, numColl, numRow);
                    String key = tracks.get(i);
                    sendKeysToPath(key, path);
                });
    }

}
