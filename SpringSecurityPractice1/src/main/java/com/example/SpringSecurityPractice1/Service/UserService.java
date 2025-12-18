package com.example.SpringSecurityPractice1.Service;

import com.example.SpringSecurityPractice1.Entity.User;
import com.example.SpringSecurityPractice1.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserDetails (String username){

        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " does not exist"));
    }

}
