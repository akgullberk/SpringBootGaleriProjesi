package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.AuthRequest;
import com.akgulberk.gallerist.dto.DtoUser;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);
}
