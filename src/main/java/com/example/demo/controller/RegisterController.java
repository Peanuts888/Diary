package com.example.demo.controller;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.demo.form.RegisterForm;
import com.example.demo.model.User;
import com.example.demo.service.UserService;

@Controller
public class RegisterController {
	
	/**
	 * UserEntityクラスを操作するServiceクラス.
	 */
	@Autowired
	private UserService userService;
	
	@GetMapping("/register")
    public String register(@ModelAttribute RegisterForm registerForm) {
        return "common/register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute RegisterForm registerForm,
    		BindingResult result) {
    	
    	if (result.hasErrors()) {
            return "common/register";
        }
    	
    	User user = registerForm.toEntity();
    	
    	Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		user.setCreatedDate(currentTime);
		user.setRole("user");
		user.setEnabled(true);

        userService.save(user);

        return "redirect:/login?register";
    }
}
