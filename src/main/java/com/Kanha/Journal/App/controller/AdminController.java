package com.Kanha.Journal.App.controller;


import com.Kanha.Journal.App.entity.UserEntry;
import com.Kanha.Journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<UserEntry> all  = userService.getAll();

        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping("/create-admin-user")
    public  void createUser(@RequestBody UserEntry user){
        userService.saveAdmin(user);
    }

}
