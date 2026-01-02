package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.DtoAccount;
import com.akgulberk.gallerist.dto.DtoAddress;
import com.akgulberk.gallerist.dto.DtoCustomer;
import com.akgulberk.gallerist.dto.DtoCustomerIU;
import com.akgulberk.gallerist.exception.BaseException;
import com.akgulberk.gallerist.exception.ErrorMessage;
import com.akgulberk.gallerist.exception.MessageType;
import com.akgulberk.gallerist.model.Account;
import com.akgulberk.gallerist.model.Address;
import com.akgulberk.gallerist.model.Customer;
import com.akgulberk.gallerist.repository.AccountRepository;
import com.akgulberk.gallerist.repository.AddressRepository;
import com.akgulberk.gallerist.repository.CustomerRepository;
import com.akgulberk.gallerist.service.ICustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AccountRepository accountRepository;


    private Customer createCustomer(DtoCustomerIU dtoCustomerIU) {
        Optional<Address> optAddress = addressRepository.findById(dtoCustomerIU.getAddressId());
        if (optAddress.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoCustomerIU.getAddressId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Optional<Account> optAccount = accountRepository.findById(dtoCustomerIU.getAccountId());
        if (optAccount.isEmpty()) {
            throw new BaseException(new ErrorMessage(dtoCustomerIU.getAccountId().toString(), MessageType.NO_RECORD_EXIST));
        }

        Customer customer = new Customer();
        customer.setCreateTime(new Date());


        BeanUtils.copyProperties(dtoCustomerIU, customer);

        customer.setAddress(optAddress.get());
        customer.setAccount(optAccount.get());

        return customer;
    }

    @Override
    public DtoCustomer saveCustomer(DtoCustomerIU dtoCustomerIU) {
        DtoCustomer dtoCustomer = new DtoCustomer();
        DtoAddress dtoAddress = new DtoAddress();
        DtoAccount  dtoAccount = new DtoAccount();

        Customer savedCustomer = customerRepository.save(createCustomer(dtoCustomerIU));

        BeanUtils.copyProperties(savedCustomer, dtoCustomer);
        BeanUtils.copyProperties(savedCustomer.getAddress(), dtoAddress);
        BeanUtils.copyProperties(savedCustomer.getAccount(), dtoAccount);

        dtoCustomer.setAddress(dtoAddress);
        dtoCustomer.setAccount(dtoAccount);

        return dtoCustomer;
    }
}
