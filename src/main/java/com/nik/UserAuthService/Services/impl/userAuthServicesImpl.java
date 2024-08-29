package com.nik.UserAuthService.Services.impl;

import com.nik.UserAuthService.Entities.CustomUserDetails;
import com.nik.UserAuthService.Entities.User;
import com.nik.UserAuthService.Repositories.UserRepository;
import com.nik.UserAuthService.Services.UserAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class userAuthServicesImpl implements UserAuthServices, UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> CustomUserDetails.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .role(user.getRole())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Override
    public User findByID(String ID) {
        return null;
    }

    @Override
    public User addUser(User user) {
        user.setJoiningDate(LocalDate.now());
        user.setUserId(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
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
