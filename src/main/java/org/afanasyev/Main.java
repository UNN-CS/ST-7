package org.afanasyev;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\aleshacoder\\Desktop\\chromedriver-win64\\chromedriver.exe");

        var webDriver = new ChromeDriver();

        try {
            webDriver.get("http://www.papercdcase.com/index.php");
        } catch (Exception e) {
            System.out.printf("Couldn't get URL: %s\n", e.getMessage());
            return;
        }

        var artist = "Metallica";
        var album = "Ride The Lightning";
        var tracks = Arrays.asList(
                "Fight Fire With Fire",
                "Ride The Lightning",
                "For Whom The Bell Tolls",
                "Fade To Black",
                "Trapped Under Ice",
                "Escape",
                "Creeping Death",
                "The Call Of Ktulu"
        );

        WebElement artistElement;
        try {
            artistElement = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input"));
        } catch (Exception e) {
            System.out.printf("Coudn't get artist element: %s\n", e.getMessage());
            return;
        }
        try {
            artistElement.sendKeys(artist);
        } catch (Exception e) {
            System.out.printf("Couldn't send keys to artist element: %s\n", e.getMessage());
            return;
        }

        WebElement titleElement;
        try {
            titleElement = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
        } catch (Exception e) {
            System.out.printf("Couldn't get title element: %s\n", e.getMessage());
            return;
        }
        try {
            titleElement.sendKeys(album);
        } catch (Exception e) {
            System.out.printf("Couldn't send keys to title element: %s\n", e.getMessage());
            return;
        }

        for (int i = 0; i < tracks.size(); i++) {
            WebElement trackElement;
            try {
                trackElement = webDriver.findElement(By.xpath(String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[%d]/td[2]/input", i + 1)));
            } catch (Exception e) {
                System.out.printf("Couldn't get track element number %d: %s\n", i + 1, e.getMessage());
                return;
            }
            try {
                trackElement.sendKeys(tracks.get(i));
            } catch (Exception e) {
                System.out.printf("Couldn't send keys to track element number %d: %s\n", i + 1, e.getMessage());
                return;
            }

        }

        WebElement typeJewelCaseElement;
        try {
            typeJewelCaseElement = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
        } catch (Exception e) {
            System.out.printf("Couldn't get type jewel case element: %s\n",  e.getMessage());
            return;
        }
        try {
            typeJewelCaseElement.click();
        } catch (Exception e) {
            System.out.printf("Couldn't click type jewel case element: %s\n", e.getMessage());
            return;
        }

        WebElement paperA4Element;
        try {
            paperA4Element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
        } catch (Exception e) {
            System.out.printf("Couldn't get paper a4 element: %s\n",  e.getMessage());
            return;
        }

        try {
            paperA4Element.click();
        } catch (Exception e) {
            System.out.printf("Couldn't click paper a4 element: %s\n", e.getMessage());
            return;
        }

        WebElement submitElement;
        try {
            submitElement = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
        } catch (Exception e) {
            System.out.printf("Couldn't get the submit element: %s\n", e.getMessage());
            return;
        }
        try {
            submitElement.submit();
        } catch (Exception e) {
            System.out.printf("Couldn't submit the submit element: %s\n", e.getMessage());
            return;
        }
    }
}
