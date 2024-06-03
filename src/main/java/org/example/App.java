package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Игорь\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            WebElement artist = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input"));
            artist.sendKeys("Christopher Larkin");
            WebElement title = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
            title.sendKeys("Hollow Knight: Gods & Nightmares");


            ArrayList<String> firstHalf = new ArrayList<>();
            ArrayList<String> secondHalf = new ArrayList<>();
            fillTracks(firstHalf, secondHalf);


            WebElement track;
            for(int i = 1; i < 9; i++) {
                track = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[" + i + "]/td[2]/input"));
                track.sendKeys(firstHalf.get(i-1));
            }
            for(int i = 1; i < 8; i++) {
                track = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[2]/table/tbody/tr[" + i + "]/td[2]/input"));
                track.sendKeys(firstHalf.get(i-1));
            }

            WebElement typeJewelcase = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            typeJewelcase.click();
            WebElement PaperA4 = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            PaperA4.click();

            WebElement btn = webDriver.findElement(By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            btn.submit();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e);
        }
    }

    static private void fillTracks(ArrayList<String> first, ArrayList<String> second) {
        first.add("Hive Knight");
        first.add("Truth, Beauty and Hatred");
        first.add("Nightmare Lantern (Interlude)");
        first.add("The Grimm Troupe");
        first.add("Nightmare King");
        first.add("White Defender");
        first.add("Dreamers");
        first.add("Pale Court");

        second.add("Gods & Glory");
        second.add("Daughter of Hallownest");
        second.add("Godhome");
        second.add("Sisters of Battle");
        second.add("Haunted Foes");
        second.add("Furious Gods");
        second.add("Pure Vessel");
    }
}
