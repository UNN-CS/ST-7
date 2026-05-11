package com.mycompany.app;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public final class Task2 {
    private static final String DRIVER_PATH_ENV = "CHROMEDRIVER_PATH";
    private static final String DEFAULT_DRIVER_PATH = "..\\drivers\\chromedriver.exe";

    private Task2() {
    }

    public static void printClientIp() {
        String driverPath = System.getenv(DRIVER_PATH_ENV);
        if (driverPath == null || driverPath.isBlank()) {
            driverPath = DEFAULT_DRIVER_PATH;
        }

        SeleniumHelper.withDriver(driverPath, webDriver -> {
            webDriver.get("https://api.ipify.org/?format=json");

            try {
                String jsonText = SeleniumHelper.readJsonPayload(webDriver);
                JSONObject jsonObject = (JSONObject) new JSONParser().parse(jsonText);
                System.out.println("Task #2 client IPv4: " + jsonObject.get("ip"));
            } catch (Exception e) {
                System.out.println("Task #2 parse error: " + e);
            }
        });
    }
}
