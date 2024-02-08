package com.pages;

import com.Interfaces.SearchPage;
import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Google implements SearchPage {

    public Google() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(name = "q")
    public WebElement gglSearchField;

    @FindBy(xpath = "//h3[@class=\"LC20lb MBeuO DKV0Md\"]")
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
