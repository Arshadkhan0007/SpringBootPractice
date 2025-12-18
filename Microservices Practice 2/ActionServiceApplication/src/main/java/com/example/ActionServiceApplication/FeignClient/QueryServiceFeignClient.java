package com.example.ActionServiceApplication.FeignClient;

import com.example.ActionServiceApplication.Entity.User;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "QUERY-SERVICE",
        configuration = FeignClientConfiguration.class,
        fallbackFactory = QueryServiceFallbackFactory.class
            )
@Retry(name = "QUERY-SERVICE")
public interface QueryServiceFeignClient {

    @GetMapping("query/users/{userId}")
    User getUser(@PathVariable int userId);

    @GetMapping("query/users/all")
    List<User> getAllUsers();

}
