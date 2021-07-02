package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Likes;


public interface LikeService {
	
	public Likes save(Likes like);
	
	public void delete(Integer id);

	public void deleteByArticleId(Article ArticleId);
	
	public long likeCount(Article articleId);
	
	public long likeState(Article articleId, Integer userId);
	
	public Likes findOne(Article articleId, Integer userId);
}
