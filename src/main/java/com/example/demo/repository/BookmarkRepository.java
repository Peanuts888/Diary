package com.example.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Article;
import com.example.demo.model.Bookmark;

@Repository
@Transactional
public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
	
	long countByArticleId(Article articleId);
	
	long countByArticleIdAndUserId(Article articleId, Integer userId);
	
	Bookmark findByArticleIdAndUserId(Article articleId, Integer userId);
	
	void deleteById(Integer likeId);
	
	void deleteByArticleId(Article articleId);
}