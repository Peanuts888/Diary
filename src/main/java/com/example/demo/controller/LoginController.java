package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
    public String login() {
        return "/common/login";
    }

    @GetMapping("/")
    public String home(Authentication loginUser, Model model) {
        model.addAttribute("username", loginUser.getName());
        return "/blogs/home";
    }
}
