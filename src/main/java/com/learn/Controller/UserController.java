package com.learn.Controller;

import com.learn.Entity.User;
import com.learn.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    //all users
    //http://localhost:8080/api/users/getAllUser
    @GetMapping("/getAllUser")
    public List<User>getAllUsers(){

        return this.userServices.getAllUser();
    }
    //retrun single user
    //http://localhost:8080/api/users/kirt123

    @GetMapping("/{username}")
    public User getUser(@PathVariable("username") String username){
        return this.userServices.getUser(username);
    }

    //http://localhost:8080/api/users/add
    @PostMapping("/add")
    public User add(@RequestBody User user){

        return this.userServices.addUser(user);
    }

}
