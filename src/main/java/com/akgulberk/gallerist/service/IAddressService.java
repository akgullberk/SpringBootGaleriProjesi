package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoAddress;
import com.akgulberk.gallerist.dto.DtoAddressIU;

public interface IAddressService {

    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU);
}
