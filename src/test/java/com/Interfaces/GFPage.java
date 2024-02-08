package com.Interfaces;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface GFPage {

    List<WebElement> getSuggestedStocks();
    List<WebElement> getPositiveDynamicStocks();

}
