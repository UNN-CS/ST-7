package constants;
import org.openqa.selenium.By;


public class ByXpath {
    public static final String WEBSITE = "http://www.papercdcase.com/index.php";
    public static final By ARTIST_INPUT_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
    public static final By ALBUM_TITLE_INPUT_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
    public static final By JEWEL_CASE_OPTION_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
    public static final By A_4_PAPER_OPTION_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
    public static final By CREATE_CASE_BUTTON_XPATH = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");
    public static final String TRACK_STRING= "/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[%d]/table/tbody/tr[%d]/td[2]/input";
}
