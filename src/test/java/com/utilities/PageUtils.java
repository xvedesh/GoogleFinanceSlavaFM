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

    public static SearchPage getCurrentPage() {
        switch (System.getProperty("currEnv")) {
            case "env1":
                return new Google();
            case "env2":
                return new Bing();
            default:
                throw new IllegalStateException ("Environment not recognized");
        }
    }

    public static void gglFinanceSearch(String gglFinance) {
        SearchPage page = getCurrentPage();
        page.getSearchField().clear();
        page.getSearchField().click();
        BrowserUtils.waitFor(3);
        page.getSearchField().sendKeys(gglFinance + Keys.ENTER);
        BrowserUtils.waitFor(3);

    }

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
