import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chrome-win64\\chrome-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        try {
            driver.get("https://www.calculator.net/password-generator.html");

        WebElement artistField = driver.findElement(By.name("artist"));
        artistField.sendKeys("Konec solnechnih dney");

        WebElement titleField = driver.findElement(By.name("title"));
        titleField.sendKeys("Osen");

        WebElement tracksField = driver.findElement(By.name("tracks"));
        tracksField.sendKeys("Deti Nemoy Strany\nHoloda\nUyedu daleko\nOsen\nKuda katitca moya golova");

        WebElement formatA4 = driver.findElement(By.xpath("//input[@value='a4']"));
        formatA4.click();

        WebElement jewelCase = driver.findElement(By.xpath("//input[@value='jewel']"));
        jewelCase.click();

        WebElement btnSubmit = driver.findElement(By.name("submit"));
        btnSubmit.click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(@href, 'pdf')]")));

        WebElement pdfLink = driver.findElement(By.xpath("//a[contains(@href, 'pdf')]"));
        pdfLink.click();

        Thread.sleep(5000);

    } catch (Exception e) {
        System.out.println("Error");
        System.out.println(e.toString());
    } finally {
        driver.quit();
    }
    }
}