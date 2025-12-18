package com.example.SpringSecurityPractice1.Controller;

import com.example.SpringSecurityPractice1.Entity.User;
import com.example.SpringSecurityPractice1.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/user_details/{username}")
    public ResponseEntity<User> getUserDetails(@PathVariable String username){
        log.info("Inside the getUserDetails() of UserController");
        return new ResponseEntity<>(service.getUserDetails(username), HttpStatus.OK);
    }

}
