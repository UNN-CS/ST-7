import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    private static final String IPIFY_URL = "https://api.ipify.org/?format=json";

    public static String getIpAddress(WebDriver webDriver) throws Exception {
        webDriver.get(IPIFY_URL);

        String json = readJsonFromBrowser(webDriver);
        JSONObject root = (JSONObject) new JSONParser().parse(json);

        return (String) root.get("ip");
    }

    private static String readJsonFromBrowser(WebDriver webDriver) {
        WebElement pre = webDriver.findElement(By.tagName("pre"));
        return pre.getText();
    }
}
