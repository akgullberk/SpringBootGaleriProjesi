package com.akgulberk.gallerist.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class DtoCustomer extends DtoBase {

    private String firstName;

    private String lastName;

    private String tckn;

    private Date birthOfDate;

    private DtoAddress address;

    private DtoAccount account;
}
