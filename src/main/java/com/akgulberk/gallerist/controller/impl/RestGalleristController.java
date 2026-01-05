package com.akgulberk.gallerist.controller.impl;

import com.akgulberk.gallerist.controller.IRestGalleristController;
import com.akgulberk.gallerist.controller.RestBaseController;
import com.akgulberk.gallerist.controller.RootEntity;
import com.akgulberk.gallerist.dto.DtoGallerist;
import com.akgulberk.gallerist.dto.DtoGalleristIU;
import com.akgulberk.gallerist.service.IGalleristService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/gallerist")
public class RestGalleristController extends RestBaseController implements IRestGalleristController {

    @Autowired
    private IGalleristService galleristService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoGallerist> saveGallerist(@Valid @RequestBody DtoGalleristIU dtoGalleristIU) {
        return ok(galleristService.saveGallerist(dtoGalleristIU));
    }
}
