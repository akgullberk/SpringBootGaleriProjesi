package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.*;
import com.akgulberk.gallerist.enums.CarStatusType;
import com.akgulberk.gallerist.exception.BaseException;
import com.akgulberk.gallerist.exception.ErrorMessage;
import com.akgulberk.gallerist.exception.MessageType;
import com.akgulberk.gallerist.model.Car;
import com.akgulberk.gallerist.model.Customer;
import com.akgulberk.gallerist.model.SaledCar;
import com.akgulberk.gallerist.repository.CarRepository;
import com.akgulberk.gallerist.repository.CustomerRepository;
import com.akgulberk.gallerist.repository.GalleristRepository;
import com.akgulberk.gallerist.repository.SaledCarRepository;
import com.akgulberk.gallerist.service.ICurrencyRatesService;
import com.akgulberk.gallerist.service.ISaledCarService;
import com.akgulberk.gallerist.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class SaledCarServiceImpl implements ISaledCarService {

    @Autowired
    private SaledCarRepository saledCarRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private GalleristRepository galleristRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ICurrencyRatesService currencyRatesService;

    /**
     * Geçerli bir döviz kuru bulana kadar geriye doğru gün arar (maksimum 7 gün)
     * Hafta sonları ve tatil günlerinde null dönen API için kullanılır
     */
    private BigDecimal getValidCurrencyRate() {
        Calendar calendar = Calendar.getInstance();
        int daysToGoBack = 0;
        int maxDaysToCheck = 7; // Maksimum 7 gün geriye gidebilir

        while (daysToGoBack < maxDaysToCheck) {
            Date currentDate = calendar.getTime();
            String dateStr = DateUtils.getCurrentDate(currentDate);

            try {
                CurrencyRatesResponse currencyRatesResponse = currencyRatesService.getCurrencyRates(dateStr, dateStr);
                
                if (currencyRatesResponse != null && 
                    currencyRatesResponse.getItems() != null && 
                    !currencyRatesResponse.getItems().isEmpty() &&
                    currencyRatesResponse.getItems().get(0).getUsd() != null) {
                    
                    String usdString = currencyRatesResponse.getItems().get(0).getUsd();
                    if (usdString != null && !usdString.trim().isEmpty()) {
                        return new BigDecimal(usdString);
                    }
                }
            } catch (Exception e) {
                // Bu tarih için veri yoksa, bir önceki güne geç
            }

            // Bir önceki güne geç
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            daysToGoBack++;
        }

        // Hiçbir geçerli kur bulunamadıysa hata fırlat
        throw new BaseException(new ErrorMessage("No valid currency rate found in the last " + maxDaysToCheck + " days", MessageType.CURRENCY_RATES_IS_OCCURED));
    }

    public BigDecimal convertCustomerAmountToUSD(Customer customer) {
        BigDecimal usd = getValidCurrencyRate();
        BigDecimal customerUSDAmount = customer.getAccount().getAmount().divide(usd, 2, RoundingMode.HALF_UP);
        return customerUSDAmount;
    }

    public boolean checkCarStatus(Long carId) {
        Optional<Car> optCar = carRepository.findById(carId);
        if (optCar.isPresent() && optCar.get().getCarStatusType().name().equals(CarStatusType.SALED.name())) {
            return false;
        }
        return true;
    }

    public BigDecimal remaningCustomerAmount(Customer customer, Car car) {
        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(customer);
        BigDecimal remaningCustomerUSDAmount = customerUSDAmount.subtract(car.getPrice());
        BigDecimal usd = getValidCurrencyRate();
        return remaningCustomerUSDAmount.multiply(usd);
    }

    public boolean checkAmount(DtoSaledCarIU dtoSaledCarIU) {

        Optional<Customer> optCustomer = customerRepository.findById(dtoSaledCarIU.getCustomerId());
        if (optCustomer.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCustomerId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Car> optCar = carRepository.findById(dtoSaledCarIU.getCarId());
        if (optCar.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCarId().toString(), MessageType.NO_RECORD_EXIST));

        }

        BigDecimal customerUSDAmount = convertCustomerAmountToUSD(optCustomer.get());

        if (customerUSDAmount.compareTo(optCar.get().getPrice()) == 0 || customerUSDAmount.compareTo(optCar.get().getPrice()) > 0) {
            return true;
        }
        return false;

    }

    private SaledCar createSaledCar(DtoSaledCarIU dtoSaledCarIU) {
        SaledCar saledCar = new SaledCar();
        saledCar.setCreateTime(new Date());

        saledCar.setCustomer(customerRepository.findById(dtoSaledCarIU.getCustomerId()).orElse(null));
        saledCar.setGallerist(galleristRepository.findById(dtoSaledCarIU.getGalleristId()).orElse(null));
        saledCar.setCar(carRepository.findById(dtoSaledCarIU.getCarId()).orElse(null));

        return saledCar;
    }

    @Override
    public DtoSaledCar buyCar(DtoSaledCarIU dtoSaledCarIU) {
        if (!checkAmount(dtoSaledCarIU)) {
            throw new BaseException(new ErrorMessage("", MessageType.CUSTOMER_AMOUNT_IS_NOT_ENOUGH));
        }

        if (!checkCarStatus(dtoSaledCarIU.getCarId())) {
            throw new BaseException(new ErrorMessage(dtoSaledCarIU.getCarId().toString(), MessageType.CAR_STATUS_ALREADY_SALED));

        }

        SaledCar savedSaledCar = saledCarRepository.save(createSaledCar(dtoSaledCarIU));

        Car car = savedSaledCar.getCar();
        car.setCarStatusType(CarStatusType.SALED);
        carRepository.save(car);

        Customer customer = savedSaledCar.getCustomer();
        customer.getAccount().setAmount(remaningCustomerAmount(customer, car));
        customerRepository.save(customer);

        return toDTO(savedSaledCar);
    }

    public DtoSaledCar toDTO(SaledCar saledCar) {
        DtoSaledCar dtoSaledCar = new DtoSaledCar();
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoGallerist dtoGallerist = new DtoGallerist();
        DtoCar dtoCar = new DtoCar();

        BeanUtils.copyProperties(saledCar, dtoSaledCar);
        BeanUtils.copyProperties(saledCar.getCustomer(), dtoCustomer);
        BeanUtils.copyProperties(saledCar.getGallerist(), dtoGallerist);
        BeanUtils.copyProperties(saledCar.getCar(), dtoCar);

        dtoSaledCar.setCustomer(dtoCustomer);
        dtoSaledCar.setGallerist(dtoGallerist);
        dtoSaledCar.setCar(dtoCar);
        return dtoSaledCar;
    }
}
