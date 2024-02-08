package com.pages;

import com.Interfaces.GFPage;
import com.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class GoogleFinance implements GFPage {

    public GoogleFinance() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@aria-labelledby='smart-watchlist-title']//div[contains(@class, 'COaKTb')]")
    public List<WebElement> suggestedStocks;

    @FindBy(xpath = "//span[contains(text(), '+')]/ancestor::div[contains(@class, 'SxcTic')]/descendant::div[@class='COaKTb']")
    public List<WebElement> positiveDynamicStocks;

    @Override
    public List<WebElement> getSuggestedStocks() {
        return suggestedStocks;
    }

    @Override
    public List<WebElement> getPositiveDynamicStocks() {
        return positiveDynamicStocks;
    }


}
