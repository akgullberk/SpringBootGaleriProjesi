package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoCar;
import com.akgulberk.gallerist.dto.DtoCarIU;

public interface ICarService {

    public DtoCar saveCar(DtoCarIU dtoCarIU);
}
