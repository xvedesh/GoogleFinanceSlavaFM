package com.utilities;


import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;


public class BrowserUtils {

    private static final Map<String, String> searchEngineEnv = new HashMap<>();

    static {
        searchEngineEnv.put("Google Search", "env1");
        searchEngineEnv.put("Bing Search", "env2");
        searchEngineEnv.put("Yahoo Search", "env3");
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