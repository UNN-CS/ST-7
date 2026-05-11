package com.mycompany.app;

public class App {
    private static final String DRIVER_PROPERTY = "webdriver.chrome.driver";
    private static final String DRIVER_PATH_ENV = "CHROMEDRIVER_PATH";
    private static final String DEFAULT_DRIVER_PATH = "..\\drivers\\chromedriver.exe";

    public static void main(String[] args) {
        String driverPath = resolveDriverPath();
        System.setProperty(DRIVER_PROPERTY, driverPath);

        printTask1Password(driverPath);
        Task2.printClientIp();
        Task3.printAndSaveForecast();
    }

    private static String resolveDriverPath() {
        String configuredPath = System.getenv(DRIVER_PATH_ENV);
        if (configuredPath != null && !configuredPath.isBlank()) {
            return configuredPath;
        }
        return DEFAULT_DRIVER_PATH;
    }

    private static void printTask1Password(String driverPath) {
        SeleniumHelper.withDriver(driverPath, driver -> {
            driver.get("https://www.calculator.net/password-generator.html");
            String password = SeleniumHelper.extractGeneratedPassword(driver);
            if (password == null || password.isBlank()) {
                System.out.println("Task #1 password was not found on page.");
            } else {
                System.out.println("Task #1 generated password: " + password);
            }
        });
    }
}
