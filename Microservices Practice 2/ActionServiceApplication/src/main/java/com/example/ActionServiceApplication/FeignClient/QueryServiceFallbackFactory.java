package com.example.ActionServiceApplication.FeignClient;

import com.example.ActionServiceApplication.Entity.User;
import com.example.ActionServiceApplication.Exception.ResourceNotFoundException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryServiceFallbackFactory implements FallbackFactory<QueryServiceFeignClient> {

    @Override
    public QueryServiceFeignClient create(Throwable cause) {

        return new QueryServiceFeignClient() {
            @Override
            public User getUser(int userId) {

                if(cause instanceof ResourceNotFoundException) {
                    throw (ResourceNotFoundException) cause;
                }

                return new User(0, "Fallback user", "Fallback email");
            }

            @Override
            public List<User> getAllUsers() {
                return List.of(
                        new User(0, "Fallback user", "Fallback email")
                );
            }
        };

    }
}
