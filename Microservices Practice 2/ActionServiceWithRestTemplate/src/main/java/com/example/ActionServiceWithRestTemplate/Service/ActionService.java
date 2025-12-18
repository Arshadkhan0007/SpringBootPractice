package com.example.ActionServiceWithRestTemplate.Service;

import com.example.ActionServiceWithRestTemplate.Entity.User;
import com.example.ActionServiceWithRestTemplate.Exception.ResourceNotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ActionService {

    private final RestTemplate restTemplate;

    public ActionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "QUERY-SERVICE", fallbackMethod = "getUserByIdFallback")
    @Retry(name = "QUERY-SERVICE")
    public User getUserById(int id) {
        try {
            System.out.println("ACTION-SERVICE: Calling QUERY-SERVICE to get user with id: " + id);
            String url = "http://QUERY-SERVICE/query/users/{userId}";
            return restTemplate.getForObject(url, User.class, id);
        } catch (HttpClientErrorException.NotFound ex) {
            System.err.println("ACTION-SERVICE: QUERY-SERVICE couldn't find user with id: " + id);
            throw new ResourceNotFoundException(ex.getMessage());
        }
    }

    public User getUserByIdFallback(int id, Throwable ex) {
        System.err.println("ACTION-SERVICE: Fallback logic is being executed because -- " + ex.getMessage());

        if(ex instanceof ResourceNotFoundException) {
            throw (ResourceNotFoundException) ex;
        }

        return new User(0, "Fallback name", "Fallback email");
    }
}
