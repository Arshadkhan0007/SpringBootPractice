package com.example.OrderService.Client;

import com.example.OrderService.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "USER-SERVICE")
public interface UserServiceClient {

    @GetMapping("users/user/{id}")
    public User getUserById(@PathVariable int id);

}
