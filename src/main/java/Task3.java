import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    private static final String URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
            + "&hourly=temperature_2m,rain&current=cloud_cover"
            + "&timezone=Europe%2FMoscow&forecast_days=1&wind_speed_unit=ms";

    private static final String SEP = "+----+----------------------+--------------+--------------+";
    private static final String ROW_FMT = "| %-2d | %-20s | %-12s | %-12s |";

    public static void run(WebDriver driver) throws Exception {
        driver.get(URL);
        WebElement el = driver.findElement(By.tagName("body"));
        JSONObject root = (JSONObject) new JSONParser().parse(el.getText());
        JSONObject hourly = (JSONObject) root.get("hourly");
        JSONArray times = (JSONArray) hourly.get("time");
        JSONArray temps = (JSONArray) hourly.get("temperature_2m");
        JSONArray rains = (JSONArray) hourly.get("rain");

        String header = String.format("| %-2s | %-20s | %-12s | %-12s |",
                "№", "Дата/время", "Темп. (°C)", "Осадки (мм)");

        List<String> lines = new ArrayList<>();
        lines.add(SEP);
        lines.add(header);
        lines.add(SEP);

        double minTemp = Double.MAX_VALUE, maxTemp = -Double.MAX_VALUE;

        for (int i = 0; i < times.size(); i++) {
            double t = ((Number) temps.get(i)).doubleValue();
            if (t < minTemp) minTemp = t;
            if (t > maxTemp) maxTemp = t;
            lines.add(String.format(ROW_FMT, i + 1, times.get(i), t, rains.get(i)));
        }
        lines.add(SEP);
        lines.add(String.format("  Мин. температура: %.1f °C   Макс. температура: %.1f °C", minTemp, maxTemp));

        System.out.println("\nЗадание 3. Прогноз погоды — Нижний Новгород (56°N, 44°E)");
        lines.forEach(System.out::println);

        Files.createDirectories(Paths.get("result"));
        try (PrintWriter pw = new PrintWriter(
                Files.newBufferedWriter(Paths.get("result", "forecast.txt"), StandardCharsets.UTF_8))) {
            lines.forEach(pw::println);
        }
    }
}
