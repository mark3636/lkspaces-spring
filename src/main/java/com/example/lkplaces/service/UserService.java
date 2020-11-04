package com.example.lkplaces.service;

import com.example.lkplaces.web.dto.UserDto;
import com.example.lkplaces.web.dto.UserWithTokenDto;
import com.example.lkplaces.jpa.entity.User;

import java.util.List;

public interface UserService {
    UserDto update(UserDto user);

    List<User> getAll();

    UserWithTokenDto signUp(UserDto user);

    UserWithTokenDto signIn(UserDto user);
}
