package com.mapping;

import com.utilities.ExcelUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StocksMapping {

    public static Set<String> getExpectedStocksToSet() {
        Set<String> expectedStocksSet = new HashSet<>();
        List<Map<String, String>> stocksMapList = getExcelData();
        for (Map<String, String> stocksMap : stocksMapList) {
            if(!stocksMap.get("Symbol").equalsIgnoreCase("Symbol"))
                expectedStocksSet.add(stocksMap.get("Symbol"));
        }
        return expectedStocksSet;
    }

    public static List<Map<String, String>> getExcelData() {
        ExcelUtil stocks = new ExcelUtil
                ("src/test/resources/Stocks.xlsx", "Suggested");
        return stocks.getDataList();
    }
}