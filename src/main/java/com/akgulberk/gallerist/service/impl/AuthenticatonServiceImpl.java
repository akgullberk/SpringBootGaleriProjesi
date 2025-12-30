package com.akgulberk.gallerist.service.impl;

import com.akgulberk.gallerist.dto.AuthRequest;
import com.akgulberk.gallerist.dto.DtoUser;
import com.akgulberk.gallerist.model.User;
import com.akgulberk.gallerist.repository.UserRepository;
import com.akgulberk.gallerist.service.IAuthenticationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AuthenticatonServiceImpl implements IAuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private User createUser(AuthRequest input) {
        User user = new User();
        user.setCreateTime(new Date());
        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return user;
    }

    @Override
    public DtoUser register(AuthRequest input) {
        DtoUser dtoUser = new DtoUser();

        User savedUser = userRepository.save(createUser(input));

        BeanUtils.copyProperties(savedUser, dtoUser);
        return dtoUser;
    }
}
