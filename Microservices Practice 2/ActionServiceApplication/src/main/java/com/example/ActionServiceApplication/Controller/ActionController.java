package com.example.ActionServiceApplication.Controller;

import com.example.ActionServiceApplication.Entity.User;
import com.example.ActionServiceApplication.FeignClient.QueryServiceFeignClient;
import com.example.ActionServiceApplication.Service.ActionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action/users")
public class ActionController {

    private final ActionService service;

    public ActionController(ActionService service) {
        this.service = service;
    }

    @PostMapping("/user")
    public String addUser(@RequestBody User user){
        System.out.println("ACTION-SERVICE: Creating new user " + user);
        return "ACTION-SERVICE: User has been created successfully";
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody User user){
        System.out.println("ACTION-SERVICE: Updating existing user to " + user);
        return "ACTION-SERVICE: User has been updated successfully";
    }

    @DeleteMapping("/user/{userId}")
    public String deleteUser(@PathVariable int userId){
        System.out.println("ACTION-SERVICE: Deleting user with Id:  " + userId);
        return "ACTION-SERVICE: User has been deleted successfully";
    }

    // Testing Feign Client
    @GetMapping("/test-feign-1")
    public ResponseEntity<List<User>> getAllUsers(){
        System.out.println("ACTION-SERVICE: Getting list of all users");
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/test-feign-2/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable int userId){
        System.out.println("ACTION-SERVICE: Getting user with Id: " + userId);
        return new ResponseEntity<>(service.getUserById(userId), HttpStatus.OK);
    }

}
