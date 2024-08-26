package com.nik.UserAuthService.Services.impl;

import com.nik.UserAuthService.Entities.User;
import com.nik.UserAuthService.Repositories.UserRepository;
import com.nik.UserAuthService.Services.UserAuthServices;
import org.springframework.beans.factory.annotation.Autowired;

public class userAuthServicesImpl implements UserAuthServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User findByID(String ID) {
        return null;
    }


    @Override
    public User checkPassword(User user) {
        return null;
    }
}
