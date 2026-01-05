package com.akgulberk.gallerist.controller.impl;

import com.akgulberk.gallerist.controller.IRestCarController;
import com.akgulberk.gallerist.controller.RestBaseController;
import com.akgulberk.gallerist.controller.RootEntity;
import com.akgulberk.gallerist.dto.DtoCar;
import com.akgulberk.gallerist.dto.DtoCarIU;
import com.akgulberk.gallerist.service.ICarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/car")
public class RestControllerImpl extends RestBaseController implements IRestCarController {

    @Autowired
    private ICarService carService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoCar> saveCar(@Valid @RequestBody DtoCarIU dtoCarIU) {
        return ok(carService.saveCar(dtoCarIU));
    }
}
