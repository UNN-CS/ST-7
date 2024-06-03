package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AutomatedPaperCase {
    public static void main(String[] args) {
        WebDriver inetGosling = configInternetDri();

        try {
            goToPage(inetGosling);
            addSongInform(inetGosling, inetGoslingWait());
            choseCaseAndCreate(inetGosling, inetGoslingWait());
            saveFileToComputer(inetGosling);

        } catch (Exception e) {
            System.err.println("You have this errors - " + e.getMessage());
        }
        quitinetGosling(inetGosling);
    }

    private static WebDriver configInternetDri() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\example\\Desktop\\chromedriver\\chromedriver.exe");
        ChromeOptions browserConfig = new ChromeOptions();
        browserConfig.setBinary("C:\\Users\\example\\Desktop\\chrome\\chrome.exe");
        return new ChromeDriver(browserConfig);
    }

    private static void goToPage(WebDriver inetGosling) {
        inetGosling.get("http://www.papercdcase.com/index.php");
    }

    private static InetGoslingWait inetGoslingWait() {
        return new InetGoslingWait(inetGosling, Duration.ofSeconds(5));
    }

    private static void addSongInform(WebDriver inetGosling, InetGoslingWait inetGoslingWait) {
        String band = "Led Zeppelin";
        String bandAlbum = "Led Zeppelin IV";
        List<String> composOfThisBand = Arrays.asList("Black Dog", "Rock and Roll", "The Battle of Evermore", "Stairway to Heaven", "Misty Mountain Hop", "Four Sticks", "Going to California", "When the Levee Breaks"
        );

        WebElement inForBand = waitForVisibilityOfElement(inetGosling, inetGoslingWait, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
        enterDescription(inForBand, band);

        WebElement inForBandAlbum = waitForVisibilityOfElement(inetGosling, inetGoslingWait, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
        enterDescription(inForBandAlbum, bandAlbum);

        for (int r = 0; r < composOfThisBand.size(); r++) {
            String xValue = String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[%d]/td[2]/input", r + 1);
            WebElement InSongs = waitForVisibilityOfElement(inetGosling, inetGoslingWait, xValue);
            enterDescription(InSongs, composOfThisBand.get(r));
        }
    }

    private static void waitForVisibilityOfElement(WebDriver inetGosling, InetGoslingWait inetGoslingWait, String valueX) {
        return inetGoslingWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(valueX)));
    }

    private static void enterDescription(WebElement webElem, String description) {
        webElem.sendKeys(descrition);
    }

    private static void choseCaseAndCreate(WebDriver inetGosling, InetGoslingWait inetGoslingWait) {
        clickElement(inetGosling, inetGoslingWait, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
        clickElement(inetGosling, inetGoslingWait, "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
        clickElement(inetGosling, inetGoslingWait, "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");
    }

    private static void clickElement(WebDriver inetGosling, InetGoslingWait inetGoslingWait, String valueX) {
        WebElement webElem = waitForClickabilityOfElement(inetGosling, inetGoslingWait, valueX);
        webElem.click();
    }

    private static WebElement waitForClickabilityOfElement(WebDriver inetGosling, InetGoslingWait inetGoslingWait, String valueX) {
        return inetGoslingWait.until(ExpectedConditions.elementToBeClickable(By.xpath(valueX)));
    }

    private static void saveFileToComputer(WebDriver inetGosling) {
        switchToNewWindow(inetGoslingr);
        String downloadLink = inetGosling.getCurrentUrl();
        System.out.println("PDF файл скачан с: " + downloadLink);
    }

    private static void switchToNewWindow(WebDriver inetGosling) {
        for (String linuxHand : inetGosling.getWindowHandles()) {
            inetGosling.switchTo().window(linuxHand);
        }
    }

    private static void quitinetGosling(WebDriver inetGosling) {
        inetGosling.quit();
    }
}
