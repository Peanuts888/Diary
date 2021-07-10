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
public class OtherUserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	LikeService likeService;
	
	@GetMapping("/{userId}")
	public String otherUserHome(Authentication loginUser, @PathVariable Integer userId, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		User otherUser = userService.findOne(userId);
		Page<Article> articlesPage = articleService.findByUserId(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", otherUser);
		model.addAttribute("page", page);
		
		return "blogs/home";
	}
	
	@GetMapping("/search/articles/{userId}")
	public String searchArticles(Authentication loginUser, @PathVariable Integer userId, Model model, @RequestParam String param,
			@PageableDefault(page = 0, size = 10, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		User otherUser = userService.findOne(userId);
		Page<Article> articlesPage = articleService.searchArticle(param, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/search/articles");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", otherUser);
        model.addAttribute("articles", articlesPage.getContent());
        model.addAttribute("page", page);
        model.addAttribute("searchParam", param);
		
		return "blogs/search/articles";
	}
	
	@GetMapping("/search/users/{userId}")
	public String searchUsers(Authentication loginUser, @PathVariable Integer userId, Model model, @RequestParam String param,
			@PageableDefault(page = 0, size = 10, sort = {
			"createdDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		User otherUser = userService.findOne(userId);
		Page<User> usersPage = userService.searchUser(param, pageable);
		PageWrapper<User> page = new PageWrapper<User>(usersPage, "/search/users");
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", otherUser);
		model.addAttribute("users", usersPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("searchParam", param);
		
		return "blogs/search/users";
	}
	
	@GetMapping("/past/{userId}")
	public String pastArticles(Authentication loginUser, @PathVariable Integer userId, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		User otherUser = userService.findOne(userId);
		Page<Article> articlesPage = articleService.findByUserId(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/past/" + userId);
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", otherUser);
		model.addAttribute("articles", articlesPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("url", "/past/" + userId);
		
		return "blogs/past";
	}
	
	@GetMapping("/like/{userId}")
	public String like(Authentication loginUser, @PathVariable Integer userId, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable) {
		
		User user = userService.findOne(loginUser.getName());
		User otherUser = userService.findOne(userId);
		Page<Article> articlesPage = articleService.findArticleLiked(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/like/" + userId);
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", otherUser);
		model.addAttribute("articles", articlesPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("url", "/like/" + userId);
		
		return "blogs/like";
	}
	
	@GetMapping("/bookmark/{userId}")
	public String bookmark(Authentication loginUser, @PathVariable Integer userId, Model model,
			@PageableDefault(page = 0, size = 6, sort = {
			"updatedDate" }, direction = Sort.Direction.DESC) Pageable pageable){
		User user = userService.findOne(loginUser.getName());
		User otherUser = userService.findOne(userId);
		Page<Article> articlesPage = articleService.findArticleBookmark(userId, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "/bookmark/" + userId);
		
		model.addAttribute("loginUser", user);
		model.addAttribute("otherUser", otherUser);
		model.addAttribute("articles", articlesPage.getContent());
		model.addAttribute("page", page);
		model.addAttribute("url", "/bookmark/" + userId);
		
		return "blogs/bookmark";
	}

	@GetMapping("/show/icon/{id}")
	@ResponseBody
	public void showIcon(@PathVariable Integer id, HttpServletResponse res) {
		User user = userService.findOne(id);
		
		try (
				// ResponseのOutputStreamを代入
				OutputStream os = res.getOutputStream();) {
			// OutputStreamにファイルデータを書き出す
			os.write(user.getIcon());
		} catch (IOException e) {
			// TODO 例外処理を実装
		}
	}
	
	@GetMapping("/show/headerImage/{userId}")
	@ResponseBody
	public void showHeaderImage(@PathVariable Integer userId, HttpServletResponse res) {
		User user = userService.findOne(userId);
		
		try (
				// ResponseのOutputStreamを代入
				OutputStream os = res.getOutputStream();) {
			// OutputStreamにファイルデータを書き出す
			os.write(user.getHeaderImage());
		} catch (IOException e) {
			// TODO 例外処理を実装
		}
	}
	
	@GetMapping("/oneArticle/{id}/{userId}")
    public String article(Authentication loginUser, Model model, @PathVariable Integer id, @PathVariable Integer userId) {
        User user = userService.findOne(loginUser.getName());
        User otherUser = userService.findOne(userId);
        Article article = articleService.findOne(id);
        model.addAttribute("loginUser", user);
        model.addAttribute("otherUser", otherUser);
        model.addAttribute("article",article);
        
        return "blogs/article";
    }
}
