package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoAddress;
import com.akgulberk.gallerist.dto.DtoAddressIU;

public interface IRestAddressController {

    public RootEntity<DtoAddress> saveAddress(DtoAddressIU dtoAddressIU);
}
