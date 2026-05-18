package com.mycompany.app;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;

public class App 
{
    public static void main( String[] args )
    {
        // Задание №1: Получить пароль с генератора паролей
        getPassword();
        
        // Задание №2: Получить IP адрес
        Task2.getIpAddress();
        
        // Задание №3: Получить прогноз погоды
        Task3.getWeatherForecast();
    }
    
    public static void getPassword()
    {
        WebDriverManager.chromedriver().setup();
        
        WebDriver webDriver = new ChromeDriver();
        try {
            webDriver.get("https://www.calculator.net/password-generator.html");
            
            Thread.sleep(3000);
            
            // Нажимаем кнопку Generate
            WebElement generateButton = webDriver.findElement(By.name("submit1"));
            generateButton.click();
            
            Thread.sleep(2000);
            
            System.out.println("=== ЗАДАНИЕ №1 ===");
            System.out.println("Генерирование пароля с сайта https://www.calculator.net/password-generator.html\n");
            
            // Используем JavaScript для получения пароля напрямую
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            
            // Ищем элемент, который содержит пароль
            // Используем XPath для поиска элемента с текстом, похожим на пароль
            try {
                // Ищем все элементы и проверяем их текст
                List<WebElement> allDivs = webDriver.findElements(By.xpath("//*[contains(text(), '~') or contains(text(), '!')]"));
                
                String password = null;
                for (WebElement div : allDivs) {
                    String text = div.getText().trim();
                    // Проверяем, содержит ли текст пароль (8-20 символов, содержит буквы, цифры и спецсимволы)
                    if (text.length() >= 8 && text.length() <= 20 && 
                        text.matches("[a-zA-Z0-9~!@#$%^&*()_\\-+=\\[\\]{}|;:,.<>?/\\\\]+")) {
                        
                        password = text;
                        break;
                    }
                }
                
                if (password != null) {
                    System.out.println("СГЕНЕРИРОВАННЫЙ ПАРОЛЬ: " + password);
                } else {
                    // Альтернативный метод - используем innerText
                    String bodyText = (String) js.executeScript("return document.body.innerText");
                    
                    // Ищем строку между "Password" (без других слов) и "Password Strength"
                    String[] lines = bodyText.split("\n");
                    for (int i = 0; i < lines.length; i++) {
                        String line = lines[i].trim();
                        if (line.equals("Password") && i + 1 < lines.length) {
                            String nextLine = lines[i + 1].trim();
                            // Проверяем, содержит ли следующая строка пароль
                            if (nextLine.length() >= 8 && nextLine.length() <= 20 && 
                                nextLine.matches("[a-zA-Z0-9~!@#$%^&*()_\\-+=\\[\\]{}|;:,.<>?/\\\\]+")) {
                                System.out.println("СГЕНЕРИРОВАННЫЙ ПАРОЛЬ: " + nextLine);
                                break;
                            }
                        }
                    }
                }
                
                System.out.println();
                
            } catch (Exception e) {
                System.out.println("Ошибка при поиске пароля: " + e.getMessage());
            }
            
        } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
        } finally {
            webDriver.quit();
        }
    }
}
