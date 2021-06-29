package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Article;
import com.example.demo.model.Likes;
import com.example.demo.repository.LikeRepository;
import com.example.demo.service.LikeService;

/**
 * ArticleEntityクラスを操作するServiceクラス.
 */
@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeRepository repository;

	@Override
	public Likes save(Likes like) {
		return repository.save(like);
	}
	
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override
	public long likeCount(Article articleId) {
		return repository.countByArticleId(articleId);
	}
	
	@Override
	public long likeState(String articleId, String userId) {
		return repository.countByArticleIdAndUserId(articleId, userId);
	}

}
