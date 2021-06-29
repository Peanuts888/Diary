package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Likes;


public interface LikeService {
	
	public Likes save(Likes like);
	
	public void delete(Integer id);

	public long likeCount(Article articleId);
	
	public long likeState(String articleId, String userId);
}
