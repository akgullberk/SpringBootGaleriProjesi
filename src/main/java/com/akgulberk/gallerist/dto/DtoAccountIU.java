package com.akgulberk.gallerist.dto;

import com.akgulberk.gallerist.enums.CurrencyType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DtoAccountIU {

    @NotNull
    private String accountNo;
    @NotNull
    private String iban;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private CurrencyType currencyType;
}
