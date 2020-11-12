package com.example.lkplaces.service;

import com.example.lkplaces.web.dto.UserDto;
import com.example.lkplaces.web.dto.UserWithTokenDto;

import java.util.List;

public interface UserService {
    UserDto update(UserDto user);

    List<UserDto> getAll();

    UserWithTokenDto signUp(UserDto user);

    UserWithTokenDto signIn(UserDto user);
}
