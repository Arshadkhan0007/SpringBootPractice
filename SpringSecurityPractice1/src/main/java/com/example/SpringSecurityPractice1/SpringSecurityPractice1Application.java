package com.example.SpringSecurityPractice1;

import com.example.SpringSecurityPractice1.Entity.Role;
import com.example.SpringSecurityPractice1.Repository.RoleRepository;
import com.example.SpringSecurityPractice1.SecurityConfiguration.JwtUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@SpringBootApplication
public class SpringSecurityPractice1Application implements CommandLineRunner {

    private final RoleRepository roleRepo;

    public SpringSecurityPractice1Application(RoleRepository roleRepo, JwtUtil jwtUtil) {
        this.roleRepo = roleRepo;
    }

    public static void main(String[] args) {
		SpringApplication.run(SpringSecurityPractice1Application.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        if(roleRepo.findAll().isEmpty()) {
            roleRepo.saveAll(Set.of(
                    new Role(null, "ROLE_USER"),
                    new Role(null, "ROLE_ADMIN")
            ));
        }

    }
}
