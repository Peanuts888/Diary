package com.example.demo.service;

import com.example.demo.model.Like;


public interface LikeService {
	
	public Like save(Like like);
	
	public void delete(Integer id);

	public long likeState(int articleId, int userId);
}
