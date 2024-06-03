package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.time.Duration;
import java.util.List;

public class App {

    private static WebDriver initializationOfWebComponent() {
        String pathToDriver = "C:\\Users\\Администратор\\Desktop\\lab7.2\\chromedriver-win64\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToDriver);
        return new ChromeDriver();
    }

    private static void createAndGetForm(WebDriver drForWeb) throws Exception {
        drForWeb.get("http://www.papercdcase.com/index.php");
        WebDriverWait waiterWhite = new WebDriverWait(drForWeb, Duration.ofSeconds(3));

        List<WebElement> elemForInput = drForWeb.findElements(By.tagName("input"));
        WebElement optionSinger = elemForInput.get(0);
        WebElement optionForName = elemForInput.get(1);
        WebElement variatyForPaper = elemForInput.get(6);
        WebElement variaryForType = elemForInput.get(5);
        WebElement button = elemForInput.get(elemForInput.size() - 1);

        optionSinger.sendKeys("Dua Lipa");
        optionForName.sendKeys("Future Nostalgia");

        List<WebElement> ElemForSong = drForWeb.findElements(By.xpath("//tr[td[1]/text()='Track']]/td[2]/input"));
        String[] compositions = {"Future Nostalgia", "Physical", "Hallucinate", "Don't Start Now", "Levitating", "Love Again", "Cool", "Pretty Please", "Break My Heart"};
        for (int i = 0; i < compositions.length; i++) {
            ElemForSong.get(i).sendKeys(compositions[i]);
        }

        variatyForPaper.click();
        variaryForType.click();
        button.click();

        waiterWhite.until(ExpectedConditions.numberOfWindowsToBe(2));
        drForWeb.switchTo().window(drForWeb.getWindowHandles().toArray(new String[0])[1]);
    }

    private static void saveWalterWhiteCom(WebDriver drForWeb) throws Exception {
        String pdfUrl = drForWeb.getCurrentUrl();
        try (InputStream inPunk = new URL(pdfUrl).openStream(); FileOutputStream outPunk = new FileOutputStream("cd.pdf")) {
            byte[] bruce = new byte[4096];
            int bytesRead;
            while ((bytesRead = inPunk.read(bruce)) != -1) {
                outPunk.write(bruce, 0, bytesRead);
            }
        }
    }

    public static void main(String[] args) {
        WebDriver drForWeb = initializationOfWebComponent();
        try {
            createAndGetForm(drForWeb);
            saveWalterWhiteCom(drForWeb);
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            drForWeb.quit();
        }
    }
}
