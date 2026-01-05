package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.DtoCar;
import com.akgulberk.gallerist.dto.DtoCarIU;
import com.akgulberk.gallerist.model.Car;
import com.akgulberk.gallerist.repository.CarRepository;
import com.akgulberk.gallerist.service.ICarService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CarServiceImpl implements ICarService {

    @Autowired
    private CarRepository carRepository;

    private Car createCar(DtoCarIU dtoCarIU) {
        Car car = new Car();
        car.setCreateTime(new Date());

        BeanUtils.copyProperties(dtoCarIU, car);

        return car;
    }

    @Override
    public DtoCar saveCar(DtoCarIU dtoCarIU) {
        DtoCar dtoCar = new DtoCar();
        Car savedCar = carRepository.save(createCar(dtoCarIU));

        BeanUtils.copyProperties(savedCar, dtoCar);

        return dtoCar;
    }
}
