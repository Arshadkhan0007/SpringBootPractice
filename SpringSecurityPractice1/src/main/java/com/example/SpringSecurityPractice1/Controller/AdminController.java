package com.example.SpringSecurityPractice1.Controller;

import com.example.SpringSecurityPractice1.Entity.User;
import com.example.SpringSecurityPractice1.Service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }

    @GetMapping("/all_users")
    public ResponseEntity<List<User>> getAllUsers(){
        log.info("Inside the getAllUsers() of AdminController");
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

}
