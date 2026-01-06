package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoGalleristCar;
import com.akgulberk.gallerist.dto.DtoGalleristCarIU;

public interface IGalleristCarService {

    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU);
}

