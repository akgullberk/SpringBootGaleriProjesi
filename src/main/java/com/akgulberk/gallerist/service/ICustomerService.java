package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoCustomer;
import com.akgulberk.gallerist.dto.DtoCustomerIU;

public interface ICustomerService {

    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU);
}
