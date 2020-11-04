package com.example.lkplaces.controller;

import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.service.UserService;
import com.example.lkplaces.web.dto.UserDto;
import com.example.lkplaces.web.dto.UserWithTokenDto;
import com.example.lkplaces.web.validation.NewG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @PostMapping("/sign-up")
    public UserWithTokenDto signUp(@Validated(NewG.class) @RequestBody UserDto user) {
        return userService.signUp(user);
    }

    @PostMapping("/sign-in")
    public UserWithTokenDto signIn(@RequestBody UserDto user) {
        return userService.signIn(user);
    }
}
