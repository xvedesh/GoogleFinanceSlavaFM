package com.pages;

import com.Interfaces.SearchPage;
import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DuckDuckGo implements SearchPage {

    public DuckDuckGo() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement gglSearchField;

    @FindBy(xpath = "//span[contains(@class,'EKtkFWMYpwzMKOYr0GYm') and contains(@class,'LQVY1Jpkk8nyJ6HBWKAk')]")
    public List<WebElement> gglSearchResults;

    @Override
    public WebElement getSearchField() {
        return gglSearchField;
    }
    @Override
    public List<WebElement> getSearchResults() {
        return gglSearchResults;
    }
}
