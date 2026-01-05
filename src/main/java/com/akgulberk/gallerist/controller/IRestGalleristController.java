package com.akgulberk.gallerist.controller;

import com.akgulberk.gallerist.dto.DtoGallerist;
import com.akgulberk.gallerist.dto.DtoGalleristIU;

public interface IRestGalleristController {

    public RootEntity<DtoGallerist> saveGallerist(DtoGalleristIU  dtoGalleristIU);
}
