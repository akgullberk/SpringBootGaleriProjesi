package com.akgulberk.gallerist.dto;

import com.akgulberk.gallerist.enums.CarStatusType;
import com.akgulberk.gallerist.enums.CurrencyType;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class DtoCarIU {

    @NonNull
    private String plaka;

    @NonNull
    private String brand;

    @NonNull
    private String model;

    @NonNull
    private Integer productionYear;

    @NonNull
    private BigDecimal price;

    @NonNull
    private CurrencyType currencyType;

    @NonNull
    private BigDecimal damagePrice;

    @NonNull
    private CarStatusType carStatusType;
}
