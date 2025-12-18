package com.example.ActionServiceApplication.Service;

import com.example.ActionServiceApplication.Entity.User;
import com.example.ActionServiceApplication.FeignClient.QueryServiceFeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {

    private final QueryServiceFeignClient feignClient;

    public ActionService(QueryServiceFeignClient feignClient) {
        this.feignClient = feignClient;
    }

    public User getUserById(int id) {
        System.out.println("ACTION-SERVICE: Calling QUERY-SERVICE to get user with Id: " + id + " using FeignClient");
        return feignClient.getUser(id);
    }

    public List<User> getAllUsers() {
        System.out.println("ACTION-SERVICE: Calling QUERY-SERVICE to get all users using FeignClient");
        return feignClient.getAllUsers();
    }

}
