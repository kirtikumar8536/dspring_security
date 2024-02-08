package com.learn.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {
    //suppose i dont want to secure this end point so we'll use antmatcher in config
    @GetMapping("/home")
    public String home() {
        return "Home sweet home spring";
    }
    @GetMapping("/login")
    public String login() {
        return "login spring";
    }
    @GetMapping("/register")
    public String register() {
        return "register spring";
    }
}
