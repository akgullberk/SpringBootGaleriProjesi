package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.DtoAddress;
import com.akgulberk.gallerist.dto.DtoAddressIU;
import com.akgulberk.gallerist.model.Address;
import com.akgulberk.gallerist.repository.AddressRepository;
import com.akgulberk.gallerist.service.IAddressService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    private Address createAddress(DtoAddressIU dtoAddressIU) {
        Address address = new Address();
        address.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoAddressIU, address);
        return address;
    }

    @Override
    public DtoAddress saveAddress(DtoAddressIU dtoAddressIU) {
        DtoAddress dtoAddress = new DtoAddress();

        Address savedAddress = addressRepository.save(createAddress(dtoAddressIU));
        BeanUtils.copyProperties(savedAddress, dtoAddress);
        return dtoAddress;
    }
}
