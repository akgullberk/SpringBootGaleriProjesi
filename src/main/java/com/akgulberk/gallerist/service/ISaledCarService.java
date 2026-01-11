package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoSaledCar;
import com.akgulberk.gallerist.dto.DtoSaledCarIU;

public interface ISaledCarService {

    public DtoSaledCar buyCar(DtoSaledCarIU  dtoSaledCarIU);
}
