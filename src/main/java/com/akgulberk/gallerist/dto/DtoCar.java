package com.akgulberk.gallerist.dto;

import com.akgulberk.gallerist.enums.CarStatusType;
import com.akgulberk.gallerist.enums.CurrencyType;
import lombok.Data;
import lombok.NonNull;

import java.math.BigDecimal;

@Data
public class DtoCar extends DtoBase{


    private String plaka;

    private String brand;

    private String model;

    private Integer productionYear;

    private BigDecimal price;

    private CurrencyType currencyType;

    private BigDecimal damagePrice;

    private CarStatusType carStatusType;
}
