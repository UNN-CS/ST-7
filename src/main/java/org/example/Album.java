package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private static String DRIVER_PATH = "C:\\Users\\User\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe";
    private static String SRC = "http://www.papercdcase.com/index.php";
    private static String ARTIST_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static String TITLE_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
    private static String TRACKS_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
    private static String TYPE_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static String PAPER_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]";
    private static String A4_PAPER_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]";
    private static String JEWEL_TYPE_XPATH =
    "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static String SUBMIT_BUTTON_XPATH =
            "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";
    private static String artist = "ssshhhiiittt!";
    private static String title = "The third life";
    private static ArrayList<String> tracks =  new ArrayList<>(
            List.of("iceberg", "how I saved the sun", "go to sleep", "sea", "flowers",
                    "stay in a dream", "hope", "home", "requiem"));

    public Album() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get(SRC);

            WebElement artistTxtInput = webDriver.findElement(By.xpath(ARTIST_XPATH));
            artistTxtInput.sendKeys(artist);

            WebElement titleTxtInput = webDriver.findElement(By.xpath(TITLE_XPATH));
            titleTxtInput.sendKeys(title);

            tracks.forEach((track) -> {
                int index = tracks.indexOf(track);
                int column = 1;
                int row = index + 1;
                if (index >= 8) {
                    column = 2;
                    row = index % 8 + 1;
                }
                WebElement trackTxtInput = webDriver.findElement(By.xpath(String.format(TRACKS_XPATH, column, row)));
                trackTxtInput.sendKeys(track);
            });

            WebElement typeRadioBttn = webDriver.findElement(By.xpath(TYPE_XPATH));
            typeRadioBttn.click();

            WebElement paperRadioBttn = webDriver.findElement(By.xpath(PAPER_XPATH));
            paperRadioBttn.click();

            WebElement jewelTypeRadioBttn = webDriver.findElement(By.xpath(JEWEL_TYPE_XPATH));
            jewelTypeRadioBttn.click();

            WebElement a4PaperRadioBttt = webDriver.findElement(By.xpath(A4_PAPER_XPATH));
            a4PaperRadioBttt.click();

            WebElement submitBttn = webDriver.findElement(By.xpath(SUBMIT_BUTTON_XPATH));
        //    submitBttn.click();
            submitBttn.submit();

        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }

    }

}
