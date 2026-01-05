package com.akgulberk.gallerist.service;

import com.akgulberk.gallerist.dto.DtoGallerist;
import com.akgulberk.gallerist.dto.DtoGalleristIU;

public interface IGalleristService {

    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU);
}
