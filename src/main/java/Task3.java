import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;

public class Task3 {
    private static final String API_URL =
        "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44" +
        "&hourly=temperature_2m,rain&current=cloud_cover" +
        "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    public static void run(WebDriver webDriver) {
        try {
            webDriver.get(API_URL);

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement pre = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("pre"))
            );

            String jsonStr = pre.getText();
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject) parser.parse(jsonStr);

            JSONObject hourly = (JSONObject) obj.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            String headerFmt = "%-4s | %-20s | %-13s | %-12s";
            String rowFmt    = "%-4d | %-20s | %-13s | %-12s";
            String header    = String.format(headerFmt, "№", "Дата/время", "Температура", "Осадки (мм)");
            StringBuilder sepBuilder = new StringBuilder();
            for (int s = 0; s < header.length(); s++) sepBuilder.append("-");
            String separator = sepBuilder.toString();

            StringBuilder sb = new StringBuilder();
            sb.append(header).append("\n");
            sb.append(separator).append("\n");

            for (int i = 0; i < times.size(); i++) {
                String row = String.format(rowFmt,
                    i + 1,
                    times.get(i),
                    temps.get(i) + " °C",
                    rains.get(i) + " мм"
                );
                sb.append(row).append("\n");
            }

            System.out.println(sb);

            try (PrintWriter pw = new PrintWriter(new FileWriter("result/forecast.txt"))) {
                pw.print(sb);
            }
            System.out.println("Таблица сохранена в result/forecast.txt");

        } catch (Exception e) {
            System.out.println("Ошибка при выполнении Задания №3: " + e.toString());
        }
    }
}
