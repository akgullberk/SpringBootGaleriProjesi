package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoAccount;
import com.akgulberk.gallerist.dto.DtoAccountIU;

public interface IAccountService {

    public DtoAccount saveAccount(DtoAccountIU dtoAccountIU);
}
