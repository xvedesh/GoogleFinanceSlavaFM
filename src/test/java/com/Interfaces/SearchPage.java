package com.Interfaces;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface SearchPage {

    WebElement getSearchField();
    List<WebElement> getSearchResults();
}
