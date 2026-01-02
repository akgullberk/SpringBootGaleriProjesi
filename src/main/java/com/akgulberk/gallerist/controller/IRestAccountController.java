package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoAccount;
import com.akgulberk.gallerist.dto.DtoAccountIU;

public interface IRestAccountController {

    public RootEntity<DtoAccount> saveAccount(DtoAccountIU dtoAccountIU);
}
