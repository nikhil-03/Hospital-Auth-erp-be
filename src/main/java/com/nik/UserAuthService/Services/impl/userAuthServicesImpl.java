package com.nik.UserAuthService.Services.impl;

import com.nik.UserAuthService.Entities.User;
import com.nik.UserAuthService.Repositories.UserRepository;
import com.nik.UserAuthService.Services.UserAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class userAuthServicesImpl implements UserAuthServices {

    @Autowired
    UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
//
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

    private List<User> users=new ArrayList<>();
    public userAuthServicesImpl() {
        users.add(new User(UUID.randomUUID().toString(),"nikhil","nikhil","admin",true, LocalDate.now()));
        users.add(new User(UUID.randomUUID().toString(),"prakash","nikhil","admin",true, LocalDate.now()));
        users.add(new User(UUID.randomUUID().toString(),"dji","nikhil","admin",true, LocalDate.now()));
    }
    @Override
    public List<User> customData() {
        return this.users;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
