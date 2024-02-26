package com.utilities;

import com.Interfaces.GFPage;
import com.Interfaces.SearchPage;
import com.mapping.StocksMapping;
import com.pages.Bing;
import com.pages.Google;
import com.pages.GoogleFinance;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PageUtils {

    /**
     * Determines the current search page based on the system environment property.
     * Supports switching between different environments, such as Google and Bing search pages.
     *
     * @return The SearchPage instance corresponding to the current environment setting.
     * @throws IllegalStateException If the environment property is not set to a recognized value.
     */
    public static SearchPage getCurrentPage() {
        switch (System.getProperty("currEnv")) {
            case "env1":
                return new Google();
            case "env2":
                return new Bing();
            default:
                throw new IllegalStateException("Environment not recognized");
        }
    }

    /**
     * Performs a search on the current search page using the provided query.
     * Clears the search field, enters the query, and submits the search form.
     *
     * @param gglFinance The search query to be submitted.
     */
    public static void gglFinanceSearch(String gglFinance) {
        SearchPage page = getCurrentPage();
        page.getSearchField().clear();
        page.getSearchField().click();
        BrowserUtils.waitFor(3);
        page.getSearchField().sendKeys(gglFinance + Keys.ENTER);
        BrowserUtils.waitFor(3);
    }

    /**
     * Validates that the first search result matches the expected result.
     * Assumes that the first result can be directly compared to the expected result.
     *
     * @param expectedFirstResult The expected text of the first search result.
     */
    public static void validateSearchResults(String expectedFirstResult) {
        SearchPage page = getCurrentPage();
        List<WebElement> allRes = page.getSearchResults();
        BrowserUtils.waitFor(3);
        WebElement firstRes = allRes.get(0);
        Assert.assertTrue(firstRes.getText().equalsIgnoreCase(expectedFirstResult));
        BrowserUtils.waitFor(3);
        firstRes.click();
        BrowserUtils.waitFor(5);
    }

    /**
     * Identifies suggested stocks on the Google Finance page and compares them with a predefined expected set.
     * Prints out stocks that are suggested on the webpage but not in the expected list and vice versa.
     */
    public static void identifySuggestedStocks() {

        GFPage page = new GoogleFinance();
        List<WebElement> suggestedStocks = page.getSuggestedStocks();

        Set<String> stockSet = new HashSet<>();
        for (WebElement element : suggestedStocks) {
            stockSet.add(element.getText());
        }
        Set<String> expectedStocksSet = StocksMapping.getExpectedStocksToSet();

        System.out.println("Symbols in webpage but not in expected list:");
        for (String symbol : stockSet) {
            if (!expectedStocksSet.contains(symbol)) {
                System.out.println(symbol);
            }
        }
        System.out.println("Symbols in expected list but not in webpage:");
        for (String symbol : expectedStocksSet) {
            if (!stockSet.contains(symbol)) {
                System.out.println(symbol);
            }
        }
    }

    /**
     * Identifies stocks with a positive dynamic on the Google Finance page and compares them with a predefined expected set.
     * Prints out symbols that have a positive dynamic both on the webpage and in the expected list, and symbols that are
     * in expected list but do not appear as such positive stocks on the webpage.
     */
    public static void identifySuggestedStocksPositiveDynamic() {

        GFPage page = new GoogleFinance();
        List<WebElement> suggestedStocks = page.getPositiveDynamicStocks();

        Set<String> positiveStockSet = new HashSet<>();
        for (WebElement element : suggestedStocks) {
            positiveStockSet.add(element.getText());
        }
        Set<String> expectedStocksSet = StocksMapping.getExpectedStocksToSet();

        System.out.println("Symbols with positive dynamic on the webpage and in the expected list:");
        for (String symbol : positiveStockSet) {
            if (expectedStocksSet.contains(symbol)) {
                System.out.println(symbol);
            }
        }
        System.out.println("Symbols that are in the expected list but not between positive stocks on webpage:");
        for (String symbol : expectedStocksSet) {
            if (!positiveStockSet.contains(symbol)) {
                System.out.println(symbol);
            }
        }
    }
}
