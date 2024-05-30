import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class App {

    private static final String ARTIST = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input";
    private static final String TITLE = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input";
    private static final String TYPE = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]";
    private static final String PAPER = "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]";
    private static final String BUTTON = "/html/body/table[2]/tbody/tr/td[1]/div/form/p/input";

    public static void main(String[] args) {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\chand\\gitclones\\Awenger444\\ST-7_Lab\\chromedriver-win64\\chromedriver.exe"
        );
        WebDriver webDriver = new ChromeDriver();

        String artist = "A$AP Rocky";
        String album = "TESTING";
        List<String> songs = Arrays.asList(
                "Distorted Records",
                "A$AP Forever REMIX (Ft. Kid Cudi, Moby & T.I.)",
                "Tony Tone",
                "Fukk Sleep (Ft. FKA twigs)",
                "Praise the Lord (Da Shine) (Ft. Skepta)",
                "CALLDROPS (Ft. Dean Blunt & Kodak Black)",
                "Buck Shots",
                "Gunz N Butter (Ft. Juicy J)",
                "Brotha Man (Ft. French Montana)",
                "OG Beeper",
                "Kids Turned Out Fine",
                "Hun43rd (Ft. Devonté Hynes)",
                "Changes",
                "Black Tux, White Collar",
                "Purity (Ft. Frank Ocean)"
        );

        try {
            webDriver.get("http://www.papercdcase.com/index.php");

            webDriver.findElement(By.xpath(ARTIST)).sendKeys(artist);
            webDriver.findElement(By.xpath(TITLE)).sendKeys(album);

            for (int i = 0; i < songs.size(); i++) {
                webDriver.findElement(
                        By.xpath(
                                "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[" +
                                        (i / 8 + 1) + "]/table/tbody/tr[" + (i % 8 + 1) + "]/td[2]/input"
                        )
                ).sendKeys(songs.get(i));
            }

            webDriver.findElement(By.xpath(TYPE)).click();
            webDriver.findElement(By.xpath(PAPER)).click();
            webDriver.findElement(By.xpath(BUTTON)).submit();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}
