package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        Users user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }
        return createUserDetails(user);
    }

    public User createUserDetails(Users user) {
        Set<GrantedAuthority> grantedAuthories = new HashSet<>();
        grantedAuthories.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));

        return new User(user.getUsername(), user.getPassword(), grantedAuthories);
    }
}
