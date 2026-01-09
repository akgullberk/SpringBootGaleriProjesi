package com.akgulberk.gallerist.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyRatesResponse {

    private Integer totalCount;

    private List<CurrencyRatesItems> items;
}
