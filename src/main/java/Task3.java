import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Task3 {

    private static final String FORECAST_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=56&longitude=44"
                    + "&hourly=temperature_2m,rain&current=cloud_cover&timezone=Europe%2FMoscow"
                    + "&forecast_days=1&wind_speed_unit=ms";

    public static void run(WebDriver webDriver) {
        try {
            webDriver.get(FORECAST_URL);
            WebElement elem = webDriver.findElement(By.tagName("pre"));
            String jsonStr = elem.getText();
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(jsonStr);
            JSONObject hourly = (JSONObject) root.get("hourly");
            JSONArray times = (JSONArray) hourly.get("time");
            JSONArray temps = (JSONArray) hourly.get("temperature_2m");
            JSONArray rains = (JSONArray) hourly.get("rain");

            StringBuilder table = new StringBuilder();
            table.append("№\tДата/время\tТемпература\tОсадки (мм)\n");
            System.out.println("Прогноз погоды (задание 3), Нижний Новгород:");
            System.out.println("№\tДата/время\tТемпература\tОсадки (мм)");

            for (int i = 0; i < times.size(); i++) {
                String line = (i + 1)
                        + "\t"
                        + times.get(i)
                        + "\t"
                        + temps.get(i)
                        + "\t"
                        + rains.get(i);
                table.append(line).append('\n');
                System.out.println(line);
            }

            Path out = Paths.get("result", "forecast.txt");
            Files.createDirectories(out.getParent());
            Files.writeString(out, table.toString(), StandardCharsets.UTF_8);
            System.out.println("Таблица сохранена: " + out.toAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.toString());
        }
    }
}
