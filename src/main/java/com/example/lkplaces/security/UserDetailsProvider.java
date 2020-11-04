package com.example.lkplaces.security;

import com.example.lkplaces.jpa.entity.User;
import com.example.lkplaces.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsProvider implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(email);
        }

        return new org.springframework.security.core.userdetails.User(email,
                user.getPassword(),
                Collections.singleton((GrantedAuthority) () -> user.getRole().name()));
    }
}
