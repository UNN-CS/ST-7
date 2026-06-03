import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    private static final String PASSWORD_PAGE =
            "https://www.calculator.net/password-generator.html";

    public static void main(String[] args) {
        System.out.println("Task 1. Password from calculator.net:");
        System.out.println(loadGeneratedPassword());
        System.out.println();

        System.out.println("Task 2. Client IPv4 address:");
        System.out.println(Task2.loadIpAddress());
        System.out.println();

        System.out.println("Task 3. Weather forecast for Nizhny Novgorod:");
        Task3.printForecast();
    }

    private static String loadGeneratedPassword() {
        WebDriver driver = createChromeDriver();
        try {
            driver.get(PASSWORD_PAGE);

            By passwordValue = By.cssSelector("#resultid .verybigtext b");
            return new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.visibilityOfElementLocated(passwordValue))
                    .getText();
        } catch (Exception exception) {
            return "Error: " + exception;
        } finally {
            driver.quit();
        }
    }

    static WebDriver createChromeDriver() {
        String configuredPath = System.getProperty("chrome.driver.path");
        if (configuredPath == null || configuredPath.trim().isEmpty()) {
            configuredPath = System.getenv("CHROME_DRIVER_PATH");
        }

        if (configuredPath != null && !configuredPath.trim().isEmpty()) {
            System.setProperty("webdriver.chrome.driver", configuredPath);
        }

        return new ChromeDriver();
    }
}
