package com.example.demo.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Article;
import com.example.demo.model.User;
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
    public String home(Authentication loginUser, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
        User user = userService.findOne(loginUser.getName());
        Integer userId = user.getId();
		Page<Article> articlesPage = articleService.findByUserId(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "");
		
        model.addAttribute("loginUser", user);
        model.addAttribute("otherUser", user);
        model.addAttribute("page", page);
        
        return "blogs/home";
    }
	
	@GetMapping("/search/articles")
	public String searchArticles(Authentication loginUser, Model model, @RequestParam String param,
			@PageableDefault(page = 0, size = 10, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		Page<Article> articlesPage = articleService.searchArticle(param, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/search/articles");
		
        model.addAttribute("loginUser", user);
        model.addAttribute("otherUser", user);
        model.addAttribute("articles", articlesPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("searchParam", param);
		
		return "blogs/search/articles";
	}
	
	@GetMapping("/search/users")
	public String searchUsers(Authentication loginUser, Model model, @RequestParam String param,
			@PageableDefault(page = 0, size = 10, sort = {
			"createdDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		Page<User> usersPage = userService.searchUser(param, pageable);
		PageWrapper<User> page = new PageWrapper<User>(usersPage, "/search/users");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", user);
		model.addAttribute("users", usersPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("searchParam", param);
		
		return "blogs/search/users";
	}
	
	@GetMapping("/past")
	public String pastArticles(Authentication loginUser, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		Integer userId = user.getId();
		Page<Article> articlesPage = articleService.findByUserId(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/past");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", user);
		model.addAttribute("articles", articlesPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("url", "/past");
		
		return "blogs/past";
	}
	
	@GetMapping("/like")
	public String like(Authentication loginUser, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		Integer userId = user.getId();
		Page<Article> articlesPage = articleService.findArticleLiked(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/like");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", user);
		model.addAttribute("articles", articlesPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("url", "/like");
		
		return "blogs/like";
	}
	
	@GetMapping("/bookmark")
	public String bookmark(Authentication loginUser, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable){
		User user = userService.findOne(loginUser.getName());
		Integer userId = user.getId();
		Page<Article> articlesPage = articleService.findArticleBookmark(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/bookmark");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", user);
		model.addAttribute("articles", articlesPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("url", "/bookmark");
		
		return "blogs/bookmark";
	}
	
	@GetMapping("/show/icon")
	@ResponseBody
	public void showIcon(Authentication loginUser, HttpServletResponse res) {
		User user = userService.findOne(loginUser.getName());
		
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
		User user = userService.findOne(loginUser.getName());
		
		try (
				// ResponseのOutputStreamを代入
				OutputStream os = res.getOutputStream();) {
			// OutputStreamにファイルデータを書き出す
			os.write(user.getHeaderImage());
		} catch (IOException e) {
			// TODO 例外処理を実装
		}
	}
	
	@GetMapping("/oneArticle/{id}")
    public String article(Authentication loginUser, Model model, @PathVariable Integer id) {
        User user = userService.findOne(loginUser.getName());
        Article article = articleService.findOne(id);
        model.addAttribute("loginUser", user);
        model.addAttribute("otherUser", user);
        model.addAttribute("article",article);
        
        return "blogs/article";
    }
}
