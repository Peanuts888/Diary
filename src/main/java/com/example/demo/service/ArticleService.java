package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.model.Article;


public interface ArticleService {
	
	public Article findOne(Integer id);
	
	public List<Article> findAll();
	
	public List<Article> findAll(Sort sort);
	
	public List<Article> searchArticle(String param);
	
	public Page<Article> findAll(Pageable pageable);
	
	public Page<Article> findArticleLiked(Integer userId, Pageable pageable);

	public Page<Article> findArticleBookmark(Integer userId, Pageable pageable);
	
	public void delete(Integer id);
}
