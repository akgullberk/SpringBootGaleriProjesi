package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.AuthRequest;
import com.akgulberk.gallerist.dto.AuthResponse;
import com.akgulberk.gallerist.dto.DtoUser;

public interface IAuthenticationService {

    public DtoUser register(AuthRequest input);

    public AuthResponse authenticate(AuthRequest input);
}
