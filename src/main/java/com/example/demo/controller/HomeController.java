package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Articles;
import com.example.demo.model.Likes;
import com.example.demo.model.Users;
import com.example.demo.service.ArticleService;
import com.example.demo.service.LikeService;
import com.example.demo.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	LikeService likeService;
	
	

	@GetMapping("/")
    public String home(Authentication loginUser, Model model) {
        Users user = userService.findOne(loginUser.getName());
        model.addAttribute("user", user);
        
        return "/blogs/home";
    }
	
	@GetMapping("/search")
	public String search(Authentication loginUser, Model model, @RequestParam String param) {
		Users user = userService.findOne(loginUser.getName());
		List<Users> users = userService.searchUsers(param);
		List<Articles> articles = articleService.searchArticles(param);
        model.addAttribute("user", user);
        model.addAttribute("users", users);
        model.addAttribute("articles", articles);
		
		return "/blogs/search";
	}
	
	@GetMapping("/past")
	public String pastArticles(Authentication loginUser, Model model) {
		Users user = userService.findOne(loginUser.getName());
		Sort sort = Sort.by("id").descending();
		List<Articles> articles = articleService.findAll(sort);
		model.addAttribute("user", user);
		model.addAttribute("articles", articles);
		
		return "/blogs/past";
	}
	
	@GetMapping("/follow")
	public String follow(Authentication loginUser, Model model) {
		Users user = userService.findOne(loginUser.getName());
		model.addAttribute("user", user);
		
		return "/blogs/follow";
	}
	
	@GetMapping("/like")
	public String like(Authentication loginUser, Model model) {
		Users user = userService.findOne(loginUser.getName());
		Sort sort = Sort.by("id").descending();
		List<Likes> likes = likeService.findAll(sort);
		model.addAttribute("user", user);
		model.addAttribute("likes", likes);
		
		return "/blogs/like";
	}
	
	@GetMapping("/bookmark")
	public String bookmark(Authentication loginUser, Model model) {
		Users user = userService.findOne(loginUser.getName());
		model.addAttribute("user", user);
		
		return "/blogs/bookmark";
	}
	
	@GetMapping("/show/icon")
	@ResponseBody
	public void showIcon(Authentication loginUser, HttpServletResponse res) {
		Users user = userService.findOne(loginUser.getName());
		
		try (
				// ResponseのOutputStreamを代入
				OutputStream os = res.getOutputStream();) {
			// OutputStreamにファイルデータを書き出す
			os.write(user.getIcon());
		} catch (IOException e) {
			// TODO 例外処理を実装
		}
	}
	
	@GetMapping("/show/headerImage")
	@ResponseBody
	public void showHeaderImage(Authentication loginUser, HttpServletResponse res) {
		Users user = userService.findOne(loginUser.getName());
		
		try (
				// ResponseのOutputStreamを代入
				OutputStream os = res.getOutputStream();) {
			// OutputStreamにファイルデータを書き出す
			os.write(user.getHeaderImage());
		} catch (IOException e) {
			// TODO 例外処理を実装
		}
	}
}
