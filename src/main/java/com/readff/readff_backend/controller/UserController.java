package com.readff.readff_backend.controller;

import com.readff.readff_backend.entity.User;
import com.readff.readff_backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private UserService userService;

    // TODO: change askerUsername to askerAccessToken
    @PostMapping
    public User getUser(@RequestBody String username, @RequestBody(required = false) String askerUsername) {
        return userService.getUserByUsername(username, askerUsername);
    }
}
