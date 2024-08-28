package com.nik.UserAuthService.Controller;

import com.nik.UserAuthService.Entities.User;
import com.nik.UserAuthService.Services.UserAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
public class TestController {
    @Autowired
    UserAuthServices userAuthServices;

     @GetMapping("/users")
     public List<User> getUser(){
         System.out.println("I am Nikhil Prakash");
         return userAuthServices.customData();
     }
}

