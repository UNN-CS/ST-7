import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task2 {
    static String getJsonText(WebDriver webDriver) {
        if (!webDriver.findElements(By.tagName("pre")).isEmpty()) {
            return webDriver.findElement(By.tagName("pre")).getText();
        }
        return webDriver.findElement(By.tagName("body")).getText();
    }

    public static void run(WebDriver webDriver) throws Exception {
        webDriver.get("https://api.ipify.org/?format=json");

        String jsonStr = getJsonText(webDriver);

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(jsonStr);
        String ip = (String) obj.get("ip");

        System.out.println("IPv4-адрес клиента: " + ip);
        System.out.println();
    }
}
