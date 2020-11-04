package com.example.lkplaces.security;

import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserProvider {
    private final UserRepository userRepository;

    @Autowired
    public CurrentUserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
