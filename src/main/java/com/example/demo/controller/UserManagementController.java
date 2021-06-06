package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.form.UserUpdateForm;
import com.example.demo.model.Users;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/user_management")
public class UserManagementController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/edit")
	public String edit(Authentication loginUser, Model model) {
		Users user = userService.findOne(loginUser.getName());
		model.addAttribute("user", user);
		model.addAttribute("userUpdateForm", new UserUpdateForm(user));
		
		return "/blogs/user_management";
	}
	
	@PostMapping("/update")
	public String update(Authentication loginUser, 
			@Validated @ModelAttribute UserUpdateForm userUpdateForm,
			BindingResult result) {
		
		if (result.hasErrors()) {
            return "/blogs/user_management";
        }
		
		Users dbUser = userService.findOne(loginUser.getName());
		
		Users user = userUpdateForm.toEntity();
		user.setId(dbUser.getId());
		user.setUsername(dbUser.getUsername());
		user.setCreatedDate(dbUser.getCreatedDate());
		user.setRole(dbUser.getRole());
		user.setEnabled(dbUser.isEnabled());
		
		userService.save(user);
		
		return "redirect:/";
	}

}
