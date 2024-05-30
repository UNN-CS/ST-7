package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        WebDriver driver = iniWebDriver();
        try {
            fillForm(driver);
            loadPDF(driver);
        } catch (Exception ex) {
            System.out.println("Ошибка");
            System.out.println(ex.toString());
        } finally {
            driver.quit();
        }
    }

    private static void loadPDF(WebDriver driver) throws Exception {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        String downloadLink = driver.getCurrentUrl();
        File outputFile = new File("cd.pdf");
        FileUtils.copyURLToFile(new URL(downloadLink), outputFile);
    }

    private static WebDriver iniWebDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\code\\c++\\test_po_2024\\st7\\ST-7\\chromedriver-win64\\chromedriver.exe");
        return new ChromeDriver();
    }

    private static void fillForm(WebDriver driver) throws Exception {
        driver.get("http://www.papercdcase.com/index.php");
        WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        By artistFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
        By titleFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
        By paperRadioButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
        By typeRadioButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
        By generateButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");

        WebElement artistInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(artistFieldLocator));
        artistInput.sendKeys("twenty one pilots");

        WebElement titleInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(titleFieldLocator));
        titleInput.sendKeys("Blurryface");

        String[] trackList = {
            "Heavydirtysoul",
            "Stressed Out",
            "Ride",
            "Fairly Local",
            "Tear in My Heart",
            "Lane Boy",
            "The Judge",
            "Doubt",
            "Polarize",
            "We Don't Believe What's on TV",
            "Message Man",
            "Hometown",
            "Not Today",
            "Goner"
        };
        int index = 0;
        while (index < trackList.length) {
            By trackFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[" + (index + 1) + "]/td[2]/input");
            WebElement trackInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(trackFieldLocator));
            trackInput.sendKeys(trackList[index]);
            index++;
        }
        WebElement paperOption = webWait.until(ExpectedConditions.elementToBeClickable(paperRadioButtonLocator));
        paperOption.click();
        WebElement typeOption = webWait.until(ExpectedConditions.elementToBeClickable(typeRadioButtonLocator));
        typeOption.click();
        WebElement createButton = webWait.until(ExpectedConditions.elementToBeClickable(generateButtonLocator));
        createButton.click();
    }

}
