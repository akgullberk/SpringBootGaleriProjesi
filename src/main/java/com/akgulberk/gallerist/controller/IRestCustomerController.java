package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoCustomer;
import com.akgulberk.gallerist.dto.DtoCustomerIU;

public interface IRestCustomerController {

    public RootEntity<DtoCustomer> saveCustomer(DtoCustomerIU dtoCustomerIU);
}
