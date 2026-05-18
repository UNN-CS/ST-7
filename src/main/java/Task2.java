import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Task2 {
    public static void run(WebDriver webDriver) {
        try {
            webDriver.get("https://api.ipify.org/?format=json");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement pre = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("pre"))
            );

            String jsonStr = pre.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);

            String ip = (String) obj.get("ip");
            System.out.println("IP-адрес клиента: " + ip);

        } catch (Exception e) {
            System.out.println("Ошибка при выполнении Задания №2: " + e.toString());
        }
    }
}
