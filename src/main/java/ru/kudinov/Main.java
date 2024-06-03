package ru.kudinov;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.Callable;

record Album(String name, String artistName, String[] trackNames) {}

public class Main {
    private static final String CHROME_DRIVER_PATH = "/home/relby/Downloads/chromedriver-linux64/chromedriver";
    private static final String CHROME_PATH = "/home/relby/Downloads/chrome-linux64/chrome";
    private static final String URL = "http://www.papercdcase.com/index.php";
    private static class XPath {
        public static final String ARTIST_TEXT_INPUT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
        public static final String TITLE_TEXT_INPUT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
        public static final String TRACK_TEXT_INPUT = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
        public static final String JEWEL_CASE_BUTTON = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
        public static final String A4_BUTTON = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]";
        public static final String FORM = "/html/body/table[2]/tbody/tr/td[1]/div/form";
    }

    public static <T> T executeOrFail(Callable<T> callable, String errorMessage) {
        try {
            return callable.call();
        } catch (Exception e) {
            System.err.printf("ERROR: %s: %s\n", errorMessage, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    public static void executeOrFail(Runnable runnable, String errorMessage) {
        try {
            runnable.run();
        } catch (Exception e) {
            System.err.printf("ERROR: %s: %s\n", errorMessage, e.getMessage());
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(CHROME_PATH);
        WebDriver driver = new ChromeDriver(options);

        executeOrFail(() -> driver.get(URL), "Couldn't get url `%s`".formatted(URL));

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

        WebElement artistTextInputElement = executeOrFail(
                () -> driver.findElement(By.xpath(XPath.ARTIST_TEXT_INPUT)),
                "Couldn't get artist text input element");
        executeOrFail(
                () -> artistTextInputElement.sendKeys(album.artistName()),
                "Couldn't send keys to artist text input element");

        WebElement titleTextInputElement = executeOrFail(
                () -> driver.findElement(By.xpath(XPath.TITLE_TEXT_INPUT)),
                "Couldn't get title text input element");
        executeOrFail(
                () -> titleTextInputElement.sendKeys(album.name()),
                "Couldn't send keys to title text input element");


        String[] albumTrackNames = album.trackNames();
        int tracksColumnLength = 8;
        for (int i = 0; i < albumTrackNames.length; i++) {
            int trackColumnIndex = i / tracksColumnLength + 1;
            int trackRowIndex = i % tracksColumnLength + 1;
            WebElement trackTextInputElement = executeOrFail(
                    () -> driver.findElement(By.xpath(XPath.TRACK_TEXT_INPUT.formatted(trackColumnIndex, trackRowIndex))),
                    "Couldn't get track text input element");
            int finalI = i;
            executeOrFail(
                    () -> trackTextInputElement.sendKeys(albumTrackNames[finalI]),
                    "Couldn't send keys to track text input element");
        }

        WebElement jewelCaseButtonElement = executeOrFail(
                () -> driver.findElement(By.xpath(XPath.JEWEL_CASE_BUTTON)),
                "Couldn't get jewel case button element");
        executeOrFail(jewelCaseButtonElement::click, "Couldn't click jewel case button element");

        WebElement a4ButtonElement = executeOrFail(
                () -> driver.findElement(By.xpath(XPath.A4_BUTTON)),
                "Coulnd't get a4 button element"
        );
        executeOrFail(a4ButtonElement::click, "Couldn't click a4 button element");

        WebElement form = executeOrFail(
                () -> driver.findElement(By.xpath(XPath.FORM)),
                "Couldn't get form element"
        );
        executeOrFail(form::submit, "Couldn't submit form element");
    }

}
