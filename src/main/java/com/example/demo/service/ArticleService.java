package com.example.demo.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.model.Article;


public interface ArticleService {
	
	public Article findOne(Integer id);
	
	public Page<Article> findByUserId(Integer userId, Pageable pageable);
	
	public List<Article> findAll(Sort sort);
	
	public Page<Article> searchArticle(String param, Pageable pageable);
	
	public Page<Article> findAll(Pageable pageable);
	
	public Page<Article> findArticleLiked(Integer userId, Pageable pageable);

	public Page<Article> findArticleBookmark(Integer userId, Pageable pageable);
	
	public Article save(Article article);
	
	public Integer update(String title, String content, Timestamp updatedDate, Integer id);
	
	public void delete(Integer id);
}
