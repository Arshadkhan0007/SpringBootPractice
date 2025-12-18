package com.example.ActionServiceApplication.FeignClient;

import com.example.ActionServiceApplication.Entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryServiceFallback implements QueryServiceFeignClient {

    @Override
    public User getUser(int userId) {
        return new User(0, "Fallback user", "Fallback email");
    }

    @Override
    public List<User> getAllUsers() {
        return List.of(
                new User(0, "Fallback user", "Fallback email")
        );
    }
}
