package com.akgulberk.gallerist.dto;

import com.akgulberk.gallerist.model.Account;
import com.akgulberk.gallerist.model.Address;
import jakarta.persistence.Column;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DtoCustomerIU {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String tckn;

    @NotNull
    private Date birthOfDate;

    @NotNull
    private Long addressId;

    @NotNull
    private Long accountId;
}
