package com.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Driver {


    static String browser;

    private Driver() {
    }

    private static final InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    /**
     * Retrieves the WebDriver from the pool, initializing it if necessary based on the system or configuration property.
     * This method supports multiple browsers including Chrome, Firefox, and their headless modes. If no browser is specified
     * via system properties, it defaults to the browser specified in the 'configuration.properties' file.
     * The method ensures that only one instance of WebDriver is created per thread to avoid unwanted interactions between tests.
     *
     * Upon initialization, the browser window is maximized. For headless modes, the browser does not display a UI,
     * allowing tests to run in a headless environment like CI/CD pipelines.
     *
     * @return The WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        if (driverPool.get() == null) {
            if (System.getProperty("BROWSER") == null) {
                browser = ConfigurationReader.getProperty("browser");
            }
            System.out.println("Browser: " + browser);
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    WebDriverManager.chromedriver().clearDriverCache().setup();
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().clearDriverCache().setup();
                    driverPool.set((new FirefoxDriver()));
                    break;
                case "firefox-headless":
                    WebDriverManager.firefoxdriver().clearDriverCache().setup();
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
            }
            driverPool.get().manage().window().maximize();
        }
        return driverPool.get();
    }

    /**
     * Closes the current WebDriver instance and removes it from the driver pool.
     * This method ensures that the browser is properly shut down and the associated
     * WebDriver instance is cleaned up after tests are completed, preventing resource leaks.
     * It checks if there is an existing WebDriver instance in the current thread before attempting to quit and remove it,
     * ensuring safe and effective teardown of the test environment.
     */
    public static void closeDriver(){
        if(driverPool.get()!=null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}