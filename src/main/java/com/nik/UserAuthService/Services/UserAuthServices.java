package com.nik.UserAuthService.Services;

import com.nik.UserAuthService.Entities.User;

public interface UserAuthServices {
    User findByUsername(String username);

    User findByID(String ID);

    User checkPassword(User user);
}
