package com.example.ActionServiceApplicationWIthRetryLogic.Controller;

import com.example.ActionServiceApplicationWIthRetryLogic.Entity.User;
import com.example.ActionServiceApplicationWIthRetryLogic.Service.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/action/retry")
public class ActionController {

    private final ActionService service;

    public ActionController(ActionService service) {
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId) {
        System.out.println("ACTION-SERVICE: Getting user with Id: " + userId);
        return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }
}
