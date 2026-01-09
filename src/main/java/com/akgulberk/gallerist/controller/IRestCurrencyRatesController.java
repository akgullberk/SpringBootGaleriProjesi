package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.CurrencyRatesResponse;

public interface IRestCurrencyRatesController {

    public RootEntity<CurrencyRatesResponse> getCurrencyRates(String startDate, String endDate);
}
