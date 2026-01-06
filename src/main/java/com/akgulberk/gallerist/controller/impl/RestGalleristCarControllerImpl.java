package com.akgulberk.gallerist.controller.impl;

import com.akgulberk.gallerist.controller.IRestGalleristCarController;
import com.akgulberk.gallerist.controller.RestBaseController;
import com.akgulberk.gallerist.controller.RootEntity;
import com.akgulberk.gallerist.dto.DtoGalleristCar;
import com.akgulberk.gallerist.dto.DtoGalleristCarIU;
import com.akgulberk.gallerist.service.IGalleristCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/gallerist-car")
public class RestGalleristCarControllerImpl extends RestBaseController implements IRestGalleristCarController {

    @Autowired
    private IGalleristCarService galleristCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGalleristCar> saveGalleristCar(@Valid @RequestBody DtoGalleristCarIU dtoGalleristCarIU) {
        return ok(galleristCarService.saveGalleristCar(dtoGalleristCarIU));
    }
}

