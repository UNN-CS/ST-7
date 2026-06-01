package unn.cs.st7;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    private static final String CHROME_DRIVER_PATH =
            "D:\\chrome\\chromedriver-win64\\chromedriver.exe";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        WebDriver webDriver = new ChromeDriver();
        try {
            runTask1(webDriver);
            Task2.run(webDriver);
            Task3.run(webDriver);
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }

    private static void runTask1(WebDriver webDriver) {
        webDriver.get("https://www.calculator.net/password-generator.html");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.cssSelector("#resultid div.verybigtext b")));
        WebElement passwordElement = webDriver.findElement(
                By.cssSelector("#resultid div.verybigtext b"));
        String password = passwordElement.getText().trim();
        System.out.println("Сгенерированный пароль: " + password);
    }
}
