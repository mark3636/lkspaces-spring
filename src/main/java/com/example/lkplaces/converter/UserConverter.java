package com.example.lkplaces.converter;

import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.web.dto.UserDto;
import com.example.lkplaces.web.dto.UserWithTokenDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserConverter {
    public static UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .image(user.getImage())
                .build();
    }

    public static UserWithTokenDto toDto(User user, String token) {
        return UserWithTokenDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .image(user.getImage())
                .token(token)
                .build();
    }

    public static User toEntity(UserDto user) {
        return User.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    public static List<UserDto> toDtos(List<User> users) {
        return users.parallelStream().map(UserConverter::toDto).collect(Collectors.toList());
    }
}
