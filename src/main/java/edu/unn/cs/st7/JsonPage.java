package edu.unn.cs.st7;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public final class JsonPage {

    private JsonPage() {
    }

    public static String getJsonText(WebDriver webDriver) {
        List<WebElement> preElements = webDriver.findElements(By.tagName("pre"));
        if (!preElements.isEmpty()) {
            return preElements.get(0).getText();
        }
        return webDriver.findElement(By.tagName("body")).getText().trim();
    }
}
