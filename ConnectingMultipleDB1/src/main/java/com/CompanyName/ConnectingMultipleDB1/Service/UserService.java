package com.CompanyName.ConnectingMultipleDB1.Service;

import com.CompanyName.ConnectingMultipleDB1.UserDatabase.Model.User;
import com.CompanyName.ConnectingMultipleDB1.UserDatabase.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public void saveAllUsers(List<User> userList){
        repo.saveAll(userList);
        System.out.println(userList.size() + " users have been added");
    }

    public void displayAllUsers(){
        System.out.println(repo.findAll());
    }
}
