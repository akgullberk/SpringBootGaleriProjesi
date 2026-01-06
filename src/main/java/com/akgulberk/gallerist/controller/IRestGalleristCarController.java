package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoGalleristCar;
import com.akgulberk.gallerist.dto.DtoGalleristCarIU;

public interface IRestGalleristCarController {

    public RootEntity<DtoGalleristCar> saveGalleristCar(DtoGalleristCarIU  dtoGalleristCarIU);
}

