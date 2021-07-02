package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Article;
import com.example.demo.model.Bookmark;
import com.example.demo.repository.BookmarkRepository;
import com.example.demo.service.BookmarkService;

/**
 * Bookmark（Entityクラス）を操作するServiceクラス.
 */
@Service
public class BookmarkServiceImpl implements BookmarkService {

	@Autowired
	private BookmarkRepository repository;

	@Override
	public Bookmark save(Bookmark bookmark) {
		return repository.save(bookmark);
	}
	
	@Override
	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	@Override
	public void deleteByArticleId(Article ArticleId) {
		repository.deleteByArticleId(ArticleId);
	}
	
	@Override
	public long countBookmark(Article articleId) {
		return repository.countByArticleId(articleId);
	}
	
	@Override
	public long getLikeState(Article articleId, Integer userId) {
		return repository.countByArticleIdAndUserId(articleId, userId);
	}

	@Override
	public Bookmark findByArticleIdAndUserId(Article articleId, Integer userId) {
		return repository.findByArticleIdAndUserId(articleId, userId);
	}

}
