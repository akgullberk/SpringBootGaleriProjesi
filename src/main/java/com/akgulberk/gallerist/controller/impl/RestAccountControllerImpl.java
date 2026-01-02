package com.akgulberk.gallerist.controller.impl;

import com.akgulberk.gallerist.controller.IRestAccountController;
import com.akgulberk.gallerist.controller.RestBaseController;
import com.akgulberk.gallerist.controller.RootEntity;
import com.akgulberk.gallerist.dto.DtoAccount;
import com.akgulberk.gallerist.dto.DtoAccountIU;
import com.akgulberk.gallerist.service.IAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/api/account")
public class RestAccountControllerImpl extends RestBaseController implements IRestAccountController {

    @Autowired
    private IAccountService accountService;

    @PostMapping("/save")
    @Override
    public RootEntity<DtoAccount> saveAccount(@Valid @RequestBody  DtoAccountIU dtoAccountIU) {
        return ok(accountService.saveAccount(dtoAccountIU));
    }
}
