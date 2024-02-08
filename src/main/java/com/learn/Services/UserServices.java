package com.learn.Services;

import com.learn.Entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices {
    List<User>list=new ArrayList<>();

    public UserServices() {
        list.add(new User("kirt123",
                "password",
                "kirti123@gmail.com"));
        list.add(new User("krishna123",
                "password2",
                "krishna@gmail.com"));
    }

    //get all users
    public List<User>getAllUser(){
        return this.list;
    }
    //get single user
    public User getUser(String username){
        return this.list.stream()
                .filter(user -> user.getUsername().equals(username))
                .findAny()
                .orElse(null);

    }
    //add new user
    public User addUser(User user){
        this.list.add(user);
        return user;
    }

}
