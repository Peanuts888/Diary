package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.example.demo.model.Article;


public interface ArticleService {
	
	public List<Article> findAll(Sort sort);
	
	public List<Article> searchArticle(String param);
	
}
