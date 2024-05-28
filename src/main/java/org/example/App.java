package org.example;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "H:\\vuz\\тестирование\\chromedriver-win64\\chromedriver.exe"); // Укажите путь к вашему драйверу
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(3));
            WebElement artistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input")));
            artistField.sendKeys("The Beatles");

            WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input")));
            titleField.sendKeys("Help!");

            String[] tracks = {
                    "Act Naturally",
                    "It’s Only Love",
                    "You Like Me Too Much",
                    "Tell Me What You See",
                    "I’ve Just Seen a Face",
                    "Yesterday",
                    "Dizzy Miss Lizzy"
            };
            for (int i = 0; i < tracks.length; i++) {
                WebElement trackField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[" + (i + 1) + "]/td[2]/input")));
                trackField.sendKeys(tracks[i]);
            }

            WebElement paperRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]")));
            paperRadioButton.click();

            WebElement typeRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]")));
            typeRadioButton.click();

            WebElement generateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input")));
            generateButton.click();

            Thread.sleep(2000);
            for (String windowHandle : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(windowHandle);
            }

            String pdfUrl = webDriver.getCurrentUrl();
            System.out.println("Download PDF from: " + pdfUrl);

            File pdfFile = new File("cd.pdf");
            FileUtils.copyURLToFile(new URL(pdfUrl), pdfFile);
            System.out.println("PDF saved to: " + pdfFile.getAbsolutePath());


        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
