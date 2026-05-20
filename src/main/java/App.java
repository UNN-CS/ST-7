import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    private static final String PASSWORD_GENERATOR_URL =
            "https://www.calculator.net/password-generator.html";

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\draivers\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        try {
            String password = getGeneratedPassword(webDriver);
            System.out.println("Сгенерированный пароль: " + password);
            System.out.println();

            String ipAddress = Task2.getIpAddress(webDriver);
            System.out.println("IPv4-адрес клиента: " + ipAddress);
            System.out.println();

            String forecastTable = Task3.getForecastTable(webDriver);
            Task3.writeForecastTable(forecastTable, Paths.get("result", "forecast.txt"));
            System.out.println(forecastTable);
            System.out.println("Прогноз сохранен в result/forecast.txt");
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        } finally {
            webDriver.quit();
        }
    }

    private static String getGeneratedPassword(WebDriver webDriver) {
        webDriver.get(PASSWORD_GENERATOR_URL);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("#resultid .verybigtext b")));

        return passwordElement.getText();
    }
}
