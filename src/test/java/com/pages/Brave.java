package com.pages;

import com.Interfaces.SearchPage;
import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Brave implements SearchPage {

    public Brave() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement brvSearchField;

    @FindBy(xpath = "//div[contains(@class,'search-snippet-title')]")
    public List<WebElement> brvSearchResults;

    @Override
    public WebElement getSearchField() {
        return brvSearchField;
    }
    @Override
    public List<WebElement> getSearchResults() {
        return brvSearchResults;
    }

}
