package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;

@RestController
public class ArticleController {
	
	@Autowired
	ArticleService service;
	
	@GetMapping("get/articles")
	public List<Article> findAll() {
		List<Article> articles = service.findAll();
		return articles;
	}

}
