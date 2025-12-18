package com.example.ActionServiceWithRestTemplate.Controller;

import com.example.ActionServiceWithRestTemplate.Entity.User;
import com.example.ActionServiceWithRestTemplate.Service.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action/rest-template")
public class ActionController {


    // Implementing RestTemplate
    private final ActionService service;

    public ActionController(ActionService service) {
        this.service = service;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        System.out.println("ACTION-SERVICE: Getting the user with id: " + id);
        return new ResponseEntity<>(service.getUserById(id), HttpStatus.OK);
    }
}
