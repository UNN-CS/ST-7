package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class App
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String artist = "XXXTentacion";
        String title = "Skins";
        String[] tracks = {
                "Introduction",
                "Guardian Angel",
                "Train Food",
                "Whoa (Mind in Awe)",
                "Bad!"
        };

        try {
            driver.get("http://www.papercdcase.com/index.php");
            WebElement artistField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input")));
            artistField.sendKeys(artist);
            WebElement titleField = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
            titleField.sendKeys(title);
            for (int i = 0; i < tracks.length; i++){
                int row = (i < 8) ? i + 1 : i - 7;
                int col = (i < 8) ? 1 : 2;
                WebElement trackField = driver.findElement(By.xpath(String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", col, row)));
                trackField.sendKeys(tracks[i]);
            }
            WebElement jewelRadio = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            jewelRadio.click();
            WebElement A4Radio = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            A4Radio.click();
            WebElement createButton = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            createButton.click();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }


}
