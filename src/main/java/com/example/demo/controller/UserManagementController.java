package com.example.demo.controller;

import org.jboss.logging.Logger;
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
	public void update(@Validated @ModelAttribute UserUpdateForm userUpdateForm,
			BindingResult result) {
		
		if (result.hasErrors()) {
//            return "/blogs/user_management";
        }
		
		Users user = userUpdateForm.toEntity();
//		user.setRole("user");
//		user.setEnabled(true);
		userService.update(user);
		
//		return "/blogs/user_management";
	}

}
