import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class App {
    private static final String CHROME_DRIVER_PATH =
            new File("drivers/chromedriver.exe").getAbsolutePath();

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

    private static void runTask1(WebDriver webDriver) throws Exception {
        webDriver.get("https://www.calculator.net/password-generator.html");

        webDriver.findElement(By.cssSelector("input[name='submit1']")).click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#resultid .verybigtext b")));
        Thread.sleep(1500);

        WebElement passwordElement = webDriver.findElement(By.cssSelector("#resultid .verybigtext b"));
        String password = passwordElement.getText().trim();

        System.out.println("Сгенерированный пароль: " + password);
        System.out.println();
    }
}
