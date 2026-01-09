package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRates(String startDate, String endDate);
}
