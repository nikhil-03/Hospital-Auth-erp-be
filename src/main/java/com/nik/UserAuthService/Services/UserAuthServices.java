package com.nik.UserAuthService.Services;

import com.nik.UserAuthService.Entities.User;

import java.util.List;

public interface UserAuthServices {
    User findByUsername(String username);

    User findByID(String ID);

    User checkPassword(User user);

    List<User> customData();

    List<User> findAllUsers();
}
