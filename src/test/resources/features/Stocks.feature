Feature: Viewing stocks on Google Finance

  @SuggestedStocks
  Scenario Outline: Accessing stock suggestions from Google Search
    Given the user is on the "<Search Engine>" page
    When the user enters "google finance" into the search field and clicks the search button
    Then the user should see the first result titled "<Search Result>" and clicks on it
    And the user verifies the page title is "Google Finance - Stock Market Prices, Real-time Quotes & Business News"
    And the user can identify which stocks from the expected list are present in the You may be interested section
    Then the user can identify which stocks from presented have positive dynamic

    Examples:
      | Search Engine | Search Result                                              |
      | Google Search | Google Finance - Stock Market Prices, Real-time Quotes ... |
      | Bing Search   | Google Finance - Stock Market Prices, Real-time Quotes     |