package com.example.lkplaces.service.impl;

import com.example.lkplaces.converter.UserConverter;
import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.jpa.enums.EnumRole;
import com.example.lkplaces.jpa.repository.UserRepository;
import com.example.lkplaces.security.JwtTokenProvider;
import com.example.lkplaces.service.UserService;
import com.example.lkplaces.web.dto.UserDto;
import com.example.lkplaces.web.dto.UserWithTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto update(UserDto user) {
        User existingUser = userRepository
                .findById(user.getId())
                .orElseThrow(() -> new RuntimeException(""));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        return UserConverter.toDto(userRepository.save(existingUser));
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public UserWithTokenDto signUp(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("");
        }
        User newUser = UserConverter.toEntity(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(EnumRole.USER);
        return UserConverter.toDto(userRepository.save(newUser), jwtTokenProvider.createToken(user.getEmail()));
    }

    @Override
    public UserWithTokenDto signIn(UserDto user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("");
        }
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("");
        }
        return UserConverter.toDto(existingUser, jwtTokenProvider.createToken(user.getEmail()));
    }
}
