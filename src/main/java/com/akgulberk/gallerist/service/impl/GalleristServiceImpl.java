package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.DtoAddress;
import com.akgulberk.gallerist.dto.DtoGallerist;
import com.akgulberk.gallerist.dto.DtoGalleristIU;
import com.akgulberk.gallerist.exception.BaseException;
import com.akgulberk.gallerist.exception.ErrorMessage;
import com.akgulberk.gallerist.exception.MessageType;
import com.akgulberk.gallerist.model.Address;
import com.akgulberk.gallerist.model.Gallerist;
import com.akgulberk.gallerist.repository.AddressRepository;
import com.akgulberk.gallerist.repository.GalleristRepository;
import com.akgulberk.gallerist.service.IGalleristService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristServiceImpl implements IGalleristService {

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private AddressRepository addressRepository;

    private Gallerist createGallerist(DtoGalleristIU dtoGalleristIU) {

        Optional<Address> optAddress = addressRepository.findById(dtoGalleristIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoGalleristIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Gallerist gallerist = new Gallerist();
        gallerist.setCreateTime(new Date());


        BeanUtils.copyProperties(dtoGalleristIU, gallerist);
        gallerist.setAddress(optAddress.get());

        return gallerist;
    }

    @Override
    public DtoGallerist saveGallerist(DtoGalleristIU dtoGalleristIU) {
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();

        Gallerist savedGallerist = galleristRepository.save(createGallerist(dtoGalleristIU));

        BeanUtils.copyProperties(savedGallerist, dtoGallerist);
        BeanUtils.copyProperties(savedGallerist.getAddress(), dtoAddress);

        dtoGallerist.setAddress(dtoAddress);
        return dtoGallerist;
    }
}
