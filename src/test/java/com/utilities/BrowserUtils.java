package com.utilities;


import org.junit.Assert;
import org.openqa.selenium.*;
import java.util.*;


public class BrowserUtils {

    private static final Map<String, String> searchEngineEnv = new HashMap<>();

    static {
        searchEngineEnv.put("Google Search", "env1");
        searchEngineEnv.put("Bing Search", "env2");
    }

    public static void getSearchEngine(String searchEngine) {
        System.setProperty("currEnv", searchEngineEnv.getOrDefault(searchEngine, ""));
        Driver.getDriver().get(ConfigurationReader.
                getProperty(searchEngineEnv.getOrDefault(searchEngine, "")));
    }

    public static void verifyTitle(WebDriver driver, String expectedTitle){

        String actualTitle = driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);

    }

    public static void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}