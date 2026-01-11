package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoSaledCar;
import com.akgulberk.gallerist.dto.DtoSaledCarIU;

public interface IRestSaledCarController {

    public RootEntity<DtoSaledCar> buyCar(DtoSaledCarIU dtoSaledCarIU);
}
