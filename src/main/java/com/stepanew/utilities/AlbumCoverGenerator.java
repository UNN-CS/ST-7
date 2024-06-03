package com.stepanew.utilities;

import com.stepanew.entities.Album;
import com.stepanew.entities.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.IntStream;

/**
 * Класс для генерации обложки альбома на основе данных альбома и путей из CSV-файла.
 * Использует Selenium WebDriver для автоматизации ввода данных на веб-сайте.
 */
public class AlbumCoverGenerator {

    private static final String ARTIST = "ARTIST_PATH";

    private static final String TITLE = "TITLE_PATH";

    private static final String TRACKS = "TRACKS_PATH_FORMAT";

    private static final String BUTTON = "BUTTON_PATH";

    private static final String TYPE = "TYPE_PATH_FORMAT";

    private static final String PAPER = "PAPER_PATH_FORMAT";

    private static final String SOURCE = "SOURCE";

    private static final String DRIVER_NAME = "DRIVER_NAME";

    private static final String DRIVER_PATH = "DRIVER_PATH";

    private final Album album;

    private final Paths paths;

    private final WebDriver webDriver;

    /**
     * Конструктор для создания генератора обложек альбомов.
     *
     * @param album объект альбома, для которого генерируется обложка
     */
    public AlbumCoverGenerator(Album album) {
        this.album = album;
        this.paths = new CsvParser().readDataFile();
        System.setProperty(paths.getPath(DRIVER_NAME), paths.getPath(DRIVER_PATH));
        this.webDriver = new ChromeDriver();
    }

    /**
     * Генерирует обложку альбома, вводя данные на веб-сайте.
     */
    public void generateAlbumCover() {
        try {
            webDriver.get(paths.getPath(SOURCE));

            sendKeysToPath(album.getArtist(), paths.getPath(ARTIST));
            sendKeysToPath(album.getTitle(), paths.getPath(TITLE));

            sendTracks();

            clickToPath(String.format(paths.getPath(TYPE), 2));
            clickToPath(String.format(paths.getPath(PAPER), 2));
            clickToPath(paths.getPath(BUTTON));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Вводит текст в элемент, найденный по указанному XPath.
     *
     * @param keys текст для ввода
     * @param path XPath элемента
     */
    private void sendKeysToPath(String keys, String path) {
        webDriver.findElement(By.xpath(path)).sendKeys(keys);
    }

    /**
     * Нажимает на элемент, найденный по указанному XPath.
     *
     * @param path XPath элемента
     */
    private void clickToPath(String path) {
        webDriver.findElement(By.xpath(path)).click();
    }

    /**
     * Вводит треки альбома в соответствующие поля на веб-сайте.
     */
    private void sendTracks() {
        List<String> tracks = album.getTracks();

        IntStream.range(0, tracks.size())
                .forEach(i -> {
                    int numColl = i < 8 ? 1 : 2;
                    int numRow = i < 8 ? i + 1 : (i % 8) + 1;
                    String path = String.format(paths.getPath(TRACKS), numColl, numRow);
                    String key = tracks.get(i);
                    sendKeysToPath(key, path);
                });
    }

}
