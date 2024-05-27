package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class Album {
    private String artist = "Agata Kristi";
    private String title = "Favourites (2002)";

    private static List<String> tracks = Arrays.asList(
            "Motorcycle Girl", "Decadence", "Hysterics", "Like I'm at War", "I'll be there",
            "Fairytale Taiga", "Black Moon", "Opium for no one", "Sailor", "Miracles",
           "Secret", "Bullet", "Closer",
             "Thriller"
    );

    private static final String ARTIST_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
    private static final String TRACKS_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
    private static final String TYPE_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static final String PAPER_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]";
    private static final String SUBMIT_BUTTON_XPATH = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";


    private static final String SOURCE = "http://www.papercdcase.com/index.php";

    private static final String DRIVER_NAME = "webdriver.chrome.driver";

    private static final String DRIVER_PATH = "C:\\Users\\Sokol\\OneDrive\\Рабочий стол\\ST-7\\chromedriver-win64\\chromedriver.exe";

    private final WebDriver driver;

    public Album() {
        this.driver = new ChromeDriver();
    }

    public void createCover() {
        System.setProperty(DRIVER_NAME, DRIVER_PATH);

        driver.get(SOURCE);

        WebElement artistElement = driver.findElement(By.xpath(ARTIST_XPATH));
        artistElement.sendKeys(artist);

        WebElement titleElement = driver.findElement(By.xpath(TITLE_XPATH));
        titleElement.sendKeys(title);

        for (int i = 0; i < tracks.size(); i++) {
            String trackXpath = String.format(TRACKS_XPATH, (i % 2) + 1, (i / 2) + 1);
            WebElement trackElement = driver.findElement(By.xpath(trackXpath));
            trackElement.sendKeys(tracks.get(i));
        }

        WebElement typeElement = driver.findElement(By.xpath(TYPE_XPATH));
        typeElement.click();

        WebElement paperElement = driver.findElement(By.xpath(PAPER_XPATH));
        paperElement.click();

        WebElement submitButton = driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH));
        submitButton.click();
    }
}
