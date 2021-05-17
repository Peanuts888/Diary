package com.example.demo.controller;

import java.sql.Timestamp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.Users;
import com.example.demo.repository.UserRepository;
import com.example.demo.util.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RegisterController {
	
	private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

	@GetMapping("/register")
    public String register(@ModelAttribute("user") Users user) {
        return "/common/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("user") Users user,
    		BindingResult result) {
    	
    	if (result.hasErrors()) {
            return "/common/register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.isAdmin()) {
            user.setRole(Role.ADMIN.name());
        } else {
            user.setRole(Role.USER.name());
        }
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        user.setCreateDate(currentTime);
        userRepository.save(user);

        return "redirect:/login?register";
    }
}
