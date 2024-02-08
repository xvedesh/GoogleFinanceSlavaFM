package com.step_defs;

import com.mapping.StocksMapping;
import com.utilities.BrowserUtils;
import com.utilities.Driver;
import com.utilities.PageUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class StocksStepDefs {

    @Given("the user is on the {string} page")
    public void the_user_is_on_the_page(String searchEngine) {
        BrowserUtils.getSearchEngine(searchEngine);
    }

    @When("the user enters {string} into the search field and clicks the search button")
    public void the_user_enters_into_the_search_field_and_clicks_the_search_button(String gglFinance) {
        PageUtils.gglFinanceSearch(gglFinance);
    }

    @Then("the user should see the first result titled {string} and clicks on it")
    public void the_user_should_see_the_first_result_titled_and_clicks_on_it(String searchRes) {
        PageUtils.validateSearchResults(searchRes);
    }

    @Then("the user verifies the page title is {string}")
    public void the_user_verifies_the_page_title_is(String title) {
        BrowserUtils.verifyTitle(Driver.getDriver(), title);
    }

    @And("the user can identify which stocks from the expected list are present in the You may be interested section")
    public void theUserCanIdentifyWhichStocksFromTheExpectedListArePresentInTheYouMayBeInterestedSection() {
        PageUtils.identifySuggestedStocks();
    }

    @Then("the user can identify which stocks from presented have positive dynamic")
    public void the_user_can_identify_which_stocks_from_presented_have_positive_dynamic() {
        PageUtils.identifySuggestedStocksPositiveDynamic();
    }
}


