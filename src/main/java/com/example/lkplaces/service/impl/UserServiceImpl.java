package com.example.lkplaces.service.impl;

import com.example.lkplaces.converter.UserConverter;
import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.jpa.enums.EnumActionType;
import com.example.lkplaces.jpa.enums.EnumDomainType;
import com.example.lkplaces.jpa.enums.EnumRole;
import com.example.lkplaces.jpa.repository.UserRepository;
import com.example.lkplaces.security.JwtTokenProvider;
import com.example.lkplaces.service.AuditService;
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
    private final AuditService auditService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder,
                           AuditService auditService) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.auditService = auditService;
    }

    @Override
    public UserDto update(UserDto user) {
        User existingUser = userRepository
                .findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setRole(user.getRole());
        existingUser = userRepository.save(existingUser);
        auditService.audit(EnumActionType.UPDATE, EnumDomainType.USER);
        return UserConverter.toDto(existingUser);
    }

    @Override
    public List<UserDto> getAll() {
        return UserConverter.toDtos(userRepository.findAll());
    }

    @Override
    public UserWithTokenDto signUp(UserDto user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("Польтозавтель с такой почтой уже существует");
        }
        User newUser = UserConverter.toEntity(user);
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRole(EnumRole.USER);
        newUser = userRepository.save(newUser);
        auditService.audit(user.getEmail(), EnumActionType.CREATE, EnumDomainType.USER);
        return UserConverter.toDto(newUser, jwtTokenProvider.createToken(user.getEmail()));
    }

    @Override
    public UserWithTokenDto signIn(UserDto user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            throw new RuntimeException("Неправильный логин или пароль");
        }
        if (!passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Неправильный логин или пароль");
        }
        return UserConverter.toDto(existingUser, jwtTokenProvider.createToken(user.getEmail()));
    }
}
