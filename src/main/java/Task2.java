import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;

public class Task2 {
    private static final String URL = "https://api.ipify.org/?format=json";

    public static void run(WebDriver driver) throws Exception {
        driver.get(URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        JSONObject obj = (JSONObject) new JSONParser().parse(el.getText());
        String ip = (String) obj.get("ip");
        System.out.printf("%nЗадание 2. Запрос выполнен в %s%n", LocalTime.now().withNano(0));
        System.out.println("Внешний IPv4-адрес клиента: " + ip);
    }
}
