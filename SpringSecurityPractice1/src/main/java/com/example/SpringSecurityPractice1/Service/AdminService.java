package com.example.SpringSecurityPractice1.Service;

import com.example.SpringSecurityPractice1.Entity.User;
import com.example.SpringSecurityPractice1.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {

        log.info("Inside getAllUsers() of AdminService");

        return userRepository.findAll();
    }
}
