package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Like;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;

/**
 * ArticleEntityクラスを操作するServiceクラス.
 */
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeRepository repository;

	public Like save(Like like) {
		return repository.save(like);
	}
	
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public long likeState(int articleId, int userId) {
		return repository.countByArticleIdAndUserId(articleId, userId);
	}

}
