import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Если Selenium Manager не определяет драйвер автоматически,
        // раскомментируйте строку ниже и укажите путь к chromedriver.exe:
        // System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        WebDriver webDriver = new ChromeDriver(options);

        try {
            // Задание №1 — генератор паролей
            System.out.println("=== Задание №1: Генератор паролей ===");
            webDriver.get("https://www.calculator.net/password-generator.html");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

            // Ищем текстовые поля с непустым значением — одно из них содержит пароль
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            String password = (String) js.executeScript(
                "var inputs = document.querySelectorAll('input[type=\"text\"], textarea');" +
                "for (var i = 0; i < inputs.length; i++) {" +
                "  var val = inputs[i].value;" +
                "  if (val && val.length >= 6) return val;" +
                "}" +
                "return null;"
            );

            if (password != null && !password.isEmpty()) {
                System.out.println("Сгенерированный пароль: " + password);
            } else {
                // Попытка найти пароль в тексте страницы через видимые элементы
                List<WebElement> elements = webDriver.findElements(
                    By.cssSelector("input[type='text'], textarea")
                );
                boolean found = false;
                for (WebElement el : elements) {
                    String val = el.getAttribute("value");
                    if (val != null && val.length() >= 6) {
                        System.out.println("Сгенерированный пароль: " + val);
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Пароль не найден на странице (элемент мог измениться)");
                }
            }

            // Задание №2
            System.out.println("\n=== Задание №2: IP-адрес клиента ===");
            Task2.run(webDriver);

            // Задание №3
            System.out.println("\n=== Задание №3: Прогноз погоды ===");
            Task3.run(webDriver);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.toString());
        } finally {
            webDriver.quit();
        }
    }
}
