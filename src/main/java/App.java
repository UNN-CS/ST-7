import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class App {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver-win64/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        try {
            driver.get("https://www.calculator.net/password-generator.html");
            Thread.sleep(3000);
            WebElement el = driver.findElement(By.cssSelector("#resultid .verybigtext b"));
            System.out.println("Задание 1. Сгенерированный пароль: " + el.getText());

            Task2.run(driver);
            Task3.run(driver);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}
