package com.example.demo.service;

import com.example.demo.model.Article;
import com.example.demo.model.Bookmark;


public interface BookmarkService {
	
	public Bookmark save(Bookmark like);
	
	public void delete(Integer likeId);
	
	public void deleteByArticleId(Article ArticleId);

	public long countBookmark(Article articleId);
	
	public long getLikeState(Article articleId, Integer userId);
	
	public Bookmark findByArticleIdAndUserId(Article articleId, Integer userId);
}
