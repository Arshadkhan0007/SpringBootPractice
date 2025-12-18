package com.example.SpringSecurityPractice1.Service;

import com.example.SpringSecurityPractice1.Dto.LoginResponse;
import com.example.SpringSecurityPractice1.Dto.UserLoginRequest;
import com.example.SpringSecurityPractice1.Dto.UserRegisterRequest;
import com.example.SpringSecurityPractice1.Dto.UserRegisterResponse;
import com.example.SpringSecurityPractice1.Entity.Role;
import com.example.SpringSecurityPractice1.Entity.User;
import com.example.SpringSecurityPractice1.Repository.RoleRepository;
import com.example.SpringSecurityPractice1.Repository.UserRepository;
import com.example.SpringSecurityPractice1.SecurityConfiguration.CustomUserDetailService;
import com.example.SpringSecurityPractice1.SecurityConfiguration.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AuthenticationService {

    private final RoleRepository roleRepo;
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailService customUserDetailService;

    public AuthenticationService(RoleRepository roleRepo, UserRepository userRepo, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtil jwtUtil, CustomUserDetailService customUserDetailService) {
        this.roleRepo = roleRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.customUserDetailService = customUserDetailService;
    }

    public UserRegisterResponse register(UserRegisterRequest request){

        log.info("Inside register() of AuthenticationService");

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Set<Role> roles = request.getRoles().stream()
                .map(role -> roleRepo.findByRoleName(role)
                        .orElseThrow(() -> new UsernameNotFoundException("Role: " + role + " doesn't exist")))
                .collect(Collectors.toSet());
        user.setRoles(roles);

        userRepo.save(user);

        Set<String> rolesResponse = user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toSet());
        return new UserRegisterResponse(user.getUsername(), rolesResponse);
    }

    public LoginResponse login(UserLoginRequest request) {

        log.info("Inside login() of AuthenticationService");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return new LoginResponse(
                jwtUtil.generateAccessToken(userDetails),
                jwtUtil.generateRefreshToken(userDetails)
                );
    }

    public String getActiveToken(String refreshToken){
        return jwtUtil.generateAccessToken(
                customUserDetailService.loadUserByUsername(
                        jwtUtil.extractUsername(refreshToken)));
    }

}
