package com.nik.UserAuthService.Services;

import com.nik.UserAuthService.Entities.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserAuthServices {
    UserDetails loadUserByUsername(String username);

    User findByID(String ID);

    User addUser(User user);

    User checkPassword(User user);

    List<User> customData();

    List<User> findAllUsers();
}
