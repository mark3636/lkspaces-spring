package com.example.lkplaces.controller;

import com.example.lkplaces.service.UserService;
import com.example.lkplaces.web.dto.UserDto;
import com.example.lkplaces.web.dto.UserWithTokenDto;
import com.example.lkplaces.web.validation.NewG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll() {
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

    @PutMapping("/{id}")
    public UserDto update(@RequestBody UserDto user) {
        return userService.update(user);
    }

    @PostMapping("/{id}/update-image")
    public UserDto updateImage(@PathVariable Integer id, @RequestParam MultipartFile image) {
        return userService.updateImage(id, image);
    }
}
