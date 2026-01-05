package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoCar;
import com.akgulberk.gallerist.dto.DtoCarIU;

public interface IRestCarController {

    public RootEntity<DtoCar> saveCar(DtoCarIU  dtoCarIU);
}
