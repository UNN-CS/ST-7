import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    private static final String IPIFY_URL = "https://api.ipify.org/?format=json";

    public static String loadIpAddress() {
        WebDriver driver = App.createChromeDriver();
        try {
            driver.get(IPIFY_URL);

            WebElement jsonBlock = driver.findElement(By.tagName("pre"));
            JSONObject answer = (JSONObject) new JSONParser().parse(jsonBlock.getText());
            return String.valueOf(answer.get("ip"));
        } catch (Exception exception) {
            return "Error: " + exception;
        } finally {
            driver.quit();
        }
    }
}
