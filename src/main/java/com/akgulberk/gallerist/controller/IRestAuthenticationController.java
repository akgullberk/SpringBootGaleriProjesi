package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.AuthRequest;
import com.akgulberk.gallerist.dto.AuthResponse;
import com.akgulberk.gallerist.dto.DtoUser;
import com.akgulberk.gallerist.dto.RefreshTokenRequest;

public interface IRestAuthenticationController {

    public RootEntity<DtoUser> register(AuthRequest input);

    public  RootEntity<AuthResponse> authenticate(AuthRequest input);

    public  RootEntity<AuthResponse> refreshToken(RefreshTokenRequest input);


}
