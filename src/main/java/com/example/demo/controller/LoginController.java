package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	UserService userService;

	@GetMapping("/login")
    public String login() {
        return "common/login";
    }
}