import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {

    public static void main(String[] args) {
        String chromeDriverPath = System.getenv("CHROMEDRIVER_PATH");
        if (chromeDriverPath != null && !chromeDriverPath.isEmpty()) {
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        }

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(System.getenv().getOrDefault("CI", "false"))) {
            options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage", "--window-size=1280,720");
        }

        WebDriver webDriver = new ChromeDriver(options);
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(45));
            By passwordLocator = By.cssSelector("#resultid div.verybigtext b");
            wait.until(ExpectedConditions.presenceOfElementLocated(passwordLocator));
            WebElement passwordEl = webDriver.findElement(passwordLocator);
            System.out.println("Пароль (задание 1): " + passwordEl.getText());

            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
