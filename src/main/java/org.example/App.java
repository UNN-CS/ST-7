package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;
import java.time.Duration;

public class App {
    // Функция для инициализации WebDriver
    private static WebDriver инициализироватьWebDriver() {
        // Установка пути к ChromeDriver
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Администратор\\Desktop\\lab7.2\\chromedriver-win64\\chromedriver.exe");
        // Инициализация ChromeDriver
        return new ChromeDriver();
    }

    // Функция для заполнения формы на веб-сайте
    private static void заполнитьФорму(WebDriver driver) throws Exception {
        // Переход на веб-сайт
        driver.get("http://www.papercdcase.com/index.php");
        // Настройка WebDriverWait
        WebDriverWait webWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        // Локаторы для элементов формы
        By artistFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[1]/td[2]/input");
        By titleFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[2]/td[2]/input");
        By paperRadioButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[5]/td[2]/input[2]");
        By typeRadioButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[4]/td[2]/input[2]");
        By generateButtonLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/p/input");

        // Заполнение поля "Исполнитель"
        WebElement artistInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(artistFieldLocator));
        artistInput.sendKeys("Dua Lipa");

        // Заполнение поля "Название"
        WebElement titleInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(titleFieldLocator));
        titleInput.sendKeys("Future Nostalgia");

        // Заполнение списка композиций
        String[] trackList = {"Future Nostalgia", "Physical", "Hallucinate", "Don't start Now", "Levitating", "Love Again", "Cool", "Pretty Please", "Break my heart"};
        int index = 0;
        while (index < trackList.length) {
            By trackFieldLocator = By.xpath("/html/body/table[2]/tbody/tr/td[1]/div/form/table/tbody/tr[3]/td[2]/table/tbody/tr/td[1]/table/tbody/tr[" + (index + 1) + "]/td[2]/input");
            WebElement trackInput = webWait.until(ExpectedConditions.visibilityOfElementLocated(trackFieldLocator));
            trackInput.sendKeys(trackList[index]);
            index++;
        }
        WebElement paperOption = webWait.until(ExpectedConditions.elementToBeClickable(paperRadioButtonLocator));
        paperOption.click();
        WebElement typeOption = webWait.until(ExpectedConditions.elementToBeClickable(typeRadioButtonLocator));
        typeOption.click();
        WebElement createButton = webWait.until(ExpectedConditions.elementToBeClickable(generateButtonLocator));
        createButton.click();
    }

    // Функция для загрузки сгенерированного PDF-файла
    private static void загрузитьPDF(WebDriver driver) throws Exception {
        // Переключение на новое окно
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        // Получение ссылки для скачивания
        String downloadLink = driver.getCurrentUrl();
        // Загрузка PDF-файла
        File outputFile = new File("cd.pdf");
        FileUtils.copyURLToFile(new URL(downloadLink), outputFile);
    }

    public static void main(String[] args) {
        WebDriver driver = инициализироватьWebDriver();
        try {
            заполнитьФорму(driver);
            загрузитьPDF(driver);
        } catch (Exception ex) {
            // Вывод сообщения об ошибке
            System.out.println("Ошибка");
            // Вывод деталей исключения
            System.out.println(ex.toString());
        } finally {
            // Закрытие экземпляра WebDriver
            driver.quit();
        }
    }
}
