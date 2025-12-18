package com.example.SpringSecurityPractice1.Controller;

import com.example.SpringSecurityPractice1.Dto.LoginResponse;
import com.example.SpringSecurityPractice1.Dto.UserLoginRequest;
import com.example.SpringSecurityPractice1.Dto.UserRegisterRequest;
import com.example.SpringSecurityPractice1.Dto.UserRegisterResponse;
import com.example.SpringSecurityPractice1.Service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRegisterRequest request){
        log.info("Inside the register() of AuthenticationController");
        return new ResponseEntity<>(service.register(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody UserLoginRequest request){
        log.info("Inside the login() of AuthenticationController");
        return new ResponseEntity<>(service.login(request), HttpStatus.OK);
    }

    @GetMapping("/activeToken")
    public ResponseEntity<String> getActiveToken(@RequestParam String refreshToken){
        return new ResponseEntity<>(service.getActiveToken(refreshToken), HttpStatus.OK);
    }

}
