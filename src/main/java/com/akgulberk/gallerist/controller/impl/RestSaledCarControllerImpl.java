package com.akgulberk.gallerist.controller.impl;

import com.akgulberk.gallerist.controller.IRestSaledCarController;
import com.akgulberk.gallerist.controller.RestBaseController;
import com.akgulberk.gallerist.controller.RootEntity;
import com.akgulberk.gallerist.dto.DtoSaledCar;
import com.akgulberk.gallerist.dto.DtoSaledCarIU;
import com.akgulberk.gallerist.service.ISaledCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/saled-car")
public class RestSaledCarControllerImpl extends RestBaseController implements IRestSaledCarController {
    @Autowired
    private ISaledCarService saledCarService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoSaledCar> buyCar(@Valid @RequestBody DtoSaledCarIU dtoSaledCarIU) {
        return ok(saledCarService.buyCar(dtoSaledCarIU));
    }
}
