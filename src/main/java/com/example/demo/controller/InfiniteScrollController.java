package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Article;
import com.example.demo.model.User;
import com.example.demo.service.ArticleService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("search")
public class InfiniteScrollController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	UserService userService;

	@GetMapping("articles/pagination")
	public List<Article> searchArticlesPagination(@RequestParam String param,
			@PageableDefault(page = 0, size = 10, sort = {
			"updatedDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Article> articlesPage = articleService.searchArticle(param, pageable);
		
		return articlesPage.getContent();
	}
	
	@GetMapping("article/total-pages")
	public int articleTotalPages(@RequestParam String param,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<Article> articlesPage = articleService.searchArticle(param, pageable);
		PageWrapper<Article> page = new PageWrapper<Article>(articlesPage, "");
		
		return page.getTotalPages();
	}
	
	@GetMapping("users/pagination")
	public List<User> searchUsersPagination(@RequestParam String param,
			@PageableDefault(page = 0, size = 10, sort = {
			"createdDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
		Page<User> usersPage = userService.searchUser(param, pageable);
		
		return usersPage.getContent();
	}
	
	@GetMapping("user/total-pages")
	public int userTotalPages(@RequestParam String param,
			@PageableDefault(size = 10) Pageable pageable) {
		Page<User> usersPage = userService.searchUser(param, pageable);
		PageWrapper<User> page = new PageWrapper<User>(usersPage, "");
		
		return page.getTotalPages();
	}
}
