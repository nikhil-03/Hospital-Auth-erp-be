package com.nik.UserAuthService.Controller;

import com.nik.UserAuthService.Entities.User;
import com.nik.UserAuthService.Services.UserAuthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/register")
public class TestController {
    @Autowired
    UserAuthServices userAuthServices;
    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User user1=userAuthServices.addUser(user);

        return ResponseEntity.ok(user1);
    }
     @GetMapping("/users")
     public List<User> getUser(){
         System.out.println("I am Nikhil Prakash");
         return userAuthServices.customData();
     }


}

