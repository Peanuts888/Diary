package com.example.demo.service;

import com.example.demo.model.Likes;


public interface LikeService {
	
	public Likes save(Likes like);
	
	public void delete(Integer id);

	public long likeState(long articleId, long userId);
}
