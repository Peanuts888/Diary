package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Users;
import com.example.demo.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	@GetMapping("/login")
    public String login() {
        return "/common/login";
    }

    @GetMapping("/")
    public String home(Authentication loginUser, Model model) {
        Users user = userService.findOne(loginUser.getName());
        model.addAttribute("user", user);
        
        return "/blogs/home";
    }
}