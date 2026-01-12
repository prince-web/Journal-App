package com.Kanha.Journal.App.controller;

import com.Kanha.Journal.App.entity.UserEntry;
import com.Kanha.Journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody UserEntry user){
        userService.saveNewUser(user);
    }
}
