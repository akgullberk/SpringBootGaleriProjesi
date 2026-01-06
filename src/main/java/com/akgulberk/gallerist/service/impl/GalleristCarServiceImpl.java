package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.DtoAddress;
import com.akgulberk.gallerist.dto.DtoCar;
import com.akgulberk.gallerist.dto.DtoGallerist;
import com.akgulberk.gallerist.dto.DtoGalleristCar;
import com.akgulberk.gallerist.dto.DtoGalleristCarIU;
import com.akgulberk.gallerist.exception.BaseException;
import com.akgulberk.gallerist.exception.ErrorMessage;
import com.akgulberk.gallerist.exception.MessageType;
import com.akgulberk.gallerist.model.Car;
import com.akgulberk.gallerist.model.Gallerist;
import com.akgulberk.gallerist.model.GalleristCar;
import com.akgulberk.gallerist.repository.CarRepository;
import com.akgulberk.gallerist.repository.GalleristCarRepository;
import com.akgulberk.gallerist.repository.GalleristRepository;
import com.akgulberk.gallerist.service.IGalleristCarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class GalleristCarServiceImpl implements IGalleristCarService {

    @Autowired
    private GalleristCarRepository galleristCarRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    private GalleristCar createGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {

        Optional<Gallerist> optGallerist = galleristRepository.findById(dtoGalleristCarIU.getGalleristId());
        if (optGallerist.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getGalleristId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Car> optCar = carRepository.findById(dtoGalleristCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoGalleristCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));
        }

        GalleristCar galleristCar = new GalleristCar();
        galleristCar.setCreateTime(new Date());

        galleristCar.setGallerist(optGallerist.get());
        galleristCar.setCar(optCar.get());

        return galleristCar;
    }

    @Override
    public DtoGalleristCar saveGalleristCar(DtoGalleristCarIU dtoGalleristCarIU) {
        DtoGalleristCar dtoGalleristCar = new DtoGalleristCar();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoAddress dtoAddress = new DtoAddress();
        DtoCar dtoCar = new DtoCar();

        GalleristCar savedGalleristCar = galleristCarRepository.save(createGalleristCar(dtoGalleristCarIU));

        BeanUtils.copyProperties(savedGalleristCar, dtoGalleristCar);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(savedGalleristCar.getGallerist().getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedGalleristCar.getCar(), dtoCar);

        dtoGallerist.setAddress(dtoAddress);
        dtoGalleristCar.setGallerist(dtoGallerist);
        dtoGalleristCar.setCar(dtoCar);
        return dtoGalleristCar;
    }
}

