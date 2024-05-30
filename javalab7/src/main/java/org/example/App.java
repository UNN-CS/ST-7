package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "C:\\illvi\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input"));
                element.sendKeys("Kizaru");
            } catch (Exception e) {
                System.out.println("Could not set field value");
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
                element.sendKeys("Tebya lubyat tam gde menya net");
            } catch (Exception e) {
                System.out.println("Could not set field value");
            }

            List<String> tracklist = new ArrayList<>();
            tracklist.add("Murder Rate");
            tracklist.add("Serial Killa");
            tracklist.add("Lu4she");
            tracklist.add("Jefe");
            tracklist.add("Tebya lubyat tam gde menya net");
            tracklist.add("Ride Wid Me");
            tracklist.add("Break Up");
            tracklist.add("Mne eto ne nuzhno");
            tracklist.add("If I Could Teach the World");
            tracklist.add("Get Money Fuck Fame");
            tracklist.add("kak vse idet");
            tracklist.add("Ha Ha Ha");
            for (int i = 0; i < tracklist.size(); i++) {
                WebElement element = webDriver.findElement(By.xpath(String.format("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input", i / 8 + 1, i % 8 + 1)));
                element.sendKeys(tracklist.get(i));
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
                element.click();
            } catch (Exception e) {
                System.out.println("Could not click on element");
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
                element.click();
            } catch (Exception e) {
                System.out.println("Could not click on element");
            }
            try {
                WebElement element = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[7]/td[2]/input"));
                element.click();
            } catch (Exception e) {
                System.out.println("Could not click on element");
            }
            webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input")).submit();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
