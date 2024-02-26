package com.utilities;


import org.junit.Assert;
import org.openqa.selenium.*;
import java.util.*;


public class BrowserUtils {

    private static final Map<String, String> searchEngineEnv = new HashMap<>();

    // Initializes the search engine environment mapping at the start of the program.
    // This static block puts search engine parameters taken from Scenario Outline Examples as keys
    // and property parameters as values.
    static {
        searchEngineEnv.put("Google Search", "env1");
        searchEngineEnv.put("Bing Search", "env2");
    }

    /**
     * Sets the system property to the URL of a specified search engine and navigates to that URL.
     * The method retrieves the search engine URL from the {@code searchEngineEnv} map using the provided
     * {@code searchEngine} name as the key.
     * This URL is then fetched from the {@code config.properties} file.
     * @param searchEngine The name of the search engine retrieved from Scenario Outline Examples
     */
    public static void getSearchEngine(String searchEngine) {
        System.setProperty("currEnv", searchEngineEnv.getOrDefault(searchEngine, ""));
        Driver.getDriver().get(ConfigurationReader.
                getProperty(searchEngineEnv.getOrDefault(searchEngine, "")));
    }

    /**
     * Asserts the current page title equals the expected title.
     * @param driver The WebDriver for page access.
     * @param expectedTitle The title expected to match the current page's title.
     */
    public static void verifyTitle(WebDriver driver, String expectedTitle){
        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
    }

    /**
     * Pauses execution for a specified duration.
     * @param seconds Duration in seconds for the pause. Converts to milliseconds for Thread.sleep.
     */
    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}