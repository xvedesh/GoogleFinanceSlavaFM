package com.pages;

import com.Interfaces.SearchPage;
import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Bing implements SearchPage {

    public Bing() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement bngSearchField;

    @FindBy(xpath = "//h2/a[contains(@h, 'ID=SERP,5')]")
    public List<WebElement> bngSearchResults;

    @Override
    public WebElement getSearchField() {
        return bngSearchField;
    }
    @Override
    public List<WebElement> getSearchResults() {
        return bngSearchResults;
    }

}
