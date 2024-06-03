package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ckeckSite();
    }

    private static void ckeckSite(){
        //получение доступа к драйверу
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tim\\Documents\\Tim\\soft\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        String albumName = "White Flag";
        String bandName = "Normandie";
        List<String> tracks = new ArrayList<>(
                Arrays.asList(  "Ecstasy",
                                "White Flag",
                                "Enough",
                                "Dead",
                                "(Don't) Need You",
                                "The Bell",
                                "Moth",
                                "Maniacs",
                                "Summer",
                                "Keep Fucking It Up",
                                "Fever",
                                "Heaven" ));

        try {
            webDriver.get("http://www.papercdcase.com/index.php");
            WebElement element;
            //Artist
            element = webDriver.findElement(By.xpath(
                    "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input"));
            element.sendKeys(bandName);
            //Title
            element = webDriver.findElement(By.xpath(
                    "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input"));
            element.sendKeys(albumName);
            //Tracks
            String template_path = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
            for (int i = 0; i < tracks.size(); i++) {
                int col_num = i / 8 + 1;
                int row_row = i % 8 + 1;
                String path = String.format(template_path,
                        col_num, row_row);
                element = webDriver.findElement(By.xpath(path));
                element.sendKeys(tracks.get(i));
            }
            //Type
            element = webDriver.findElement(By.xpath(
                    "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]"));
            element.click();
            //Paper
            element = webDriver.findElement(By.xpath(
                    "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]"));
            element.click();

            //Button
            element = webDriver.findElement(By.xpath(
                    "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input"));
            element.submit();
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
