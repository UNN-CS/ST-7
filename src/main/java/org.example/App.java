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
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Администратор\\Desktop\\lab7.2\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("http://www.papercdcase.com/index.php");
            WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(3));
            
            By artistFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
            By titleFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
            By paperRadioButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
            By typeRadioButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
            By generateButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");

            WebElement artistInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(artistFieldLocator));
            artistInput.sendKeys("Dua Lipa");

            WebElement titleInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(titleFieldLocator));
            titleInput.sendKeys("Future Nostalgia");

            String[] trackList = {"Future Nostalgia", "Physical", "Hallucinate", "Don't start Now", "Levitating", "Love Again", "Cool", "Pretty Please", "Break my heart"};
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

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            String downloadLink = driver.getCurrentUrl();

            File outputFile = new File("cd.pdf");
            FileUtils.copyURLToFile(new URL(downloadLink), outputFile);

        } catch (Exception ex) {
            System.out.println("Error");
            System.out.println(ex.toString());
        } finally {
            driver.quit();
        }
    }
}
