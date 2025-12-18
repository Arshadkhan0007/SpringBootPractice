package com.example.UserService.Controller;

import com.example.UserService.Entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable int id){
        System.out.println("getUserById()...");
        // Simulating database and service operations
        if(id == 101) {
            return new User(101, "Alex");
        } else{
            return null;
        }

    }

}
