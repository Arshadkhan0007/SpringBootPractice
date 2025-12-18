package com.example.QueryServiceApplication.Controller;

import com.example.QueryServiceApplication.Entity.User;
import com.example.QueryServiceApplication.Exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query/users")
public class QueryController {

    // Dummy Database
    List<User> userList = List.of(
            new User(101, "Alex", "Alex123@gmail.com"),
            new User(102, "Ben", "Ben123@gmail.com"),
            new User(103, "Charlie", "Charile123@gmail.com"),
            new User(104, "Danny", "Danny123@gmail.com"),
            new User(105, "Earl", "Earl123@gmail.com")
            );

    @GetMapping("/all")
    public List<User> getAllUsers(){
        System.out.println("QUERY-SERVICE: Getting all users");
        return userList;
    }

    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId){
        System.out.println("QUERY-SERVICE: Getting user with Id: " + userId);

        //Simulating DB operations
        for(User user : userList){
            if(user.getUserId() == userId){
                return user;
            }
        }

        System.err.println("QUERY-SERVICE: Couldn't find a user with Id: " + userId);
        throw new ResourceNotFoundException("User with Id: " + userId + " does not exist!");
    }

}
