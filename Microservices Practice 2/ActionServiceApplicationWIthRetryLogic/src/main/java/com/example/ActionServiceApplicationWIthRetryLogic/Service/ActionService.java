package com.example.ActionServiceApplicationWIthRetryLogic.Service;

import com.example.ActionServiceApplicationWIthRetryLogic.Entity.User;
import com.example.ActionServiceApplicationWIthRetryLogic.Exception.ResourceNotFoundException;
import com.example.ActionServiceApplicationWIthRetryLogic.FeignClient.QueryServiceFeignClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    private final QueryServiceFeignClient feignClient;

    public ActionService(QueryServiceFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    @CircuitBreaker(name = "QUERY-SERVICE", fallbackMethod = "getUserByIdFallback")
    @Retry(name = "QUERY-SERVICE")
    public User getUserById(int userId) {
        System.out.println("ACTION-SERVICE: Calling QUERY-SERVICE to get user with Id: " + userId);
        return feignClient.getUser(userId);
    }

    public List<User> getAllUsers() {
        return feignClient.getAllUsers();
    }

    // Fallback methods
    public User getUserByIdFallback(int userId, Throwable ex) {
        System.err.println("ACTION-SERVICE: Fallback has been invoked, reason: " + ex.getMessage());

        if(ex instanceof ResourceNotFoundException) {
            throw (ResourceNotFoundException) ex;
        }

        return new User(0, "Fallback name", "Fallback email");
    }
}
