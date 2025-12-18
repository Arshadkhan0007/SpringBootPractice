package com.example.ActionServiceApplicationWIthRetryLogic.FeignClient;

import com.example.ActionServiceApplicationWIthRetryLogic.Entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "QUERY-SERVICE", configuration = FeignClientConfig.class)
public interface QueryServiceFeignClient {

    @GetMapping("/query/users/all")
    public List<User> getAllUsers();

    @GetMapping("/query/users/{userId}")
    public User getUser(@PathVariable int userId);

}
